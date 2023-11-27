from utils.env_mut import EvoD4jEnvMut
import argparse
import subprocess 
import shlex
import json
import re
import os

def get_descriptor(target_class, signature):
    isSucceed = False
    with open(os.path.join(env.metadata_dir, "dir.bin.classes")) as f:
        target_path = f.read()
    p = subprocess.run(
        shlex.split(f"javap -s {os.path.join(env.original_dir,target_path, target_class)}"), stdout = subprocess.PIPE,
        universal_newlines = True
    )
    javap_result = p.stdout.split('\n')
    print(javap_result)
    print(signature)
    method_name = signature.split('(')[0]
    for idx, line in enumerate(javap_result):
        if line.find(signature)!= -1:
            input_output = javap_result[idx + 1].split(':')[1].strip()
            isSucceed = True
            break
    if isSucceed:
        descriptor = method_name + input_output
    else:
        descriptor = method_name
    return isSucceed, descriptor



def parse_log(mutant):
    spliteed_line = mutant.split(":")
    mutants_log = {}
    mutants_log["mutant_no"] = spliteed_line[0]
    mutants_log["mutant_operator"] = spliteed_line[1]
    mutants_log["before_syntax"] = spliteed_line[2]
    mutants_log["after_syntax"] = spliteed_line[3]
    mutants_log["target"] = spliteed_line[4]
    mutants_log["line"] = spliteed_line[5]
    mutants_log["before_after"] = spliteed_line[6]
    return mutants_log

def class_name_to_java_path(class_name):
    return class_name.replace('.', '/')+'.java'

def class_name_to_class_path(class_name):
    return class_name.replace('.', '/')+'.class'

def isGoodComment(str):
    # if len(str) < 500:
    #     return False
    if re.search("@param", str) and re.search("@return", str):
        return True
    elif re.search("non-Javadoc", str):
        return False
    else:
        return False
    
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()
    
    project = args.project
    version = args.version

    env = EvoD4jEnvMut(project, version)
    # classes modified by the bug fix
    buggy_classes=open(env.classes_modified).readlines()
    print(buggy_classes)
    # source directory of classes
    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.original_dir, src_root_relpath)

    # parse the buggy classes code.
    for buggy_class in buggy_classes:

        class_abs_path=os.path.join(src_root_abs_path, class_name_to_java_path(buggy_class.strip()))
        parse_output=os.path.join(env.metadata_dir,f'{buggy_class.strip()}.json')
        p = subprocess.run(
            shlex.split(f'java -jar {env.java_analyzer} {class_abs_path} {parse_output}')
        )
   
    
    good_comment_methods_in_buggy_class =[]
    # read parsed result
    for buggy_class in buggy_classes:
        parse_output=os.path.join(env.metadata_dir, f'{buggy_class.strip()}.json')
        with open(parse_output, 'r') as f:
            buggy_class_parsed_json = json.load(f)
        for node in buggy_class_parsed_json["nodes"]:
            if node["type"] == "method" and isGoodComment(node["comment"]):
                good_comment_methods_in_buggy_class.append(node["signature"])
    
    mutants = open(env.mutant_log).readlines()
    mutants_dic = {} 
    for single_mutant in mutants:
        single_mutant_dic = parse_log(single_mutant)
        target = single_mutant_dic["target"]
        if '@' not in target:
            continue
        target_tmp = target.replace('@','.')
        if target_tmp not in good_comment_methods_in_buggy_class:
            continue
        if target not in mutants_dic.keys() :
            mutants_dic[target] = []
        info = single_mutant_dic["line"] + ':' + single_mutant_dic["before_after"]        
        mutants_dic[target].append(info)
    idx = 0
    for key, values in mutants_dic.items():
        for value in values:
            idx += 1
            mut_dir=f'{env.mutated_dir}/{idx}'
            if not os.path.exists(mut_dir):
                os.makedirs(mut_dir)
                
            p = subprocess.run(
                shlex.split(f'cp -r {env.original_dir}/. {mut_dir}')
            )

            mut_src_abs_path=os.path.join(mut_dir, src_root_relpath)
            target_class=class_name_to_class_path(key.split('@')[0])
            target_class_java=class_name_to_java_path(key.split('@')[0])
            isSucceed, target_method_descriptor= get_descriptor(target_class, key.split('@')[1])
            if not isSucceed:
                idx -= 1
                continue 
            if not os.path.exists(os.path.join(mut_dir,"mut_metadata/methods.relevant")):
                os.makedirs(os.path.join(mut_dir,"mut_metadata/methods.relevant"))

            with open(os.path.join(mut_dir, "mut_metadata/classes.relevant"),'w') as f:
                f.write(key.split('@')[0])

            with open(os.path.join(mut_dir, "mut_metadata/methods.relevant", key.split('@')[0]),'w') as f:
                f.write(target_method_descriptor)
            
            with open(os.path.join(mut_dir, "mut_metadata/methods.relevant", key.split('@')[0])+'.budget','w') as f:
                f.write("1")

            split_value=value.split(':')
            modify_line = int(split_value[0])
            before = split_value[1].split(' |==> ')[0].strip()
            after = split_value[1].split(' |==> ')[1].strip()

            with open(os.path.join(mut_src_abs_path, target_class_java),'r+') as f:
                lines = []
                count = 0
                for line in f:
                    count += 1
                    if count == modify_line:
                        lines += line.replace(before, after)
                    else:
                        lines += line
                f.seek(0)
                f.writelines(lines)
                f.truncate()
            p= subprocess.run(
                shlex.split(f'defects4j export -p cp.compile -w {mut_dir}'), stdout = subprocess.PIPE,
                universal_newlines = True
            )
            
            compiled = p.stdout.split(':')[0]

            q = subprocess.run(
                shlex.split(f'rm -r {compiled}')
            )
            
            r = subprocess.run(
                shlex.split(f'defects4j compile -w {mut_dir}')
            )
            print('****************',idx,'****************')
            print(target_method_descriptor)
