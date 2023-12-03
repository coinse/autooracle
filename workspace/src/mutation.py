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
   
    # source directory of classes
    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.original_dir, src_root_relpath)
    
    # Mutated class list
    mutated_classes = os.listdir(env.mutants_logs)
    #parse the mutated classes code.
    parse_output_dir = os.path.join(env.metadata_dir, "parse_output")
    # if not os.path.exists(parse_output_dir):
    #     os.makedirs(parse_output_dir)
    # for mutated_class in mutated_classes:
    #     mutated_class = mutated_class[:-4]
    #     if mutated_class.find('$') != -1:
    #         mutated_class = mutated_class.split('$')[0]
    #     class_abs_path=os.path.join(src_root_abs_path, class_name_to_java_path(mutated_class.strip()))
    #     if os.path.exists(class_abs_path):
    #         parse_output=os.path.join(parse_output_dir,f'{mutated_class.strip()}.json')
    #         p = subprocess.run(
    #             shlex.split(f'java -jar {env.java_analyzer} {class_abs_path} {parse_output}')
    #         )
    
    good_comment_methods_in_mutated_class =[]
    # read parsed result
    for mutated_class in mutated_classes:
        mutated_class = mutated_class[:-4]
        if mutated_class.find('$') != -1:
            mutated_class = mutated_class.split('$')[0]
        parse_output=os.path.join(parse_output_dir, f'{mutated_class.strip()}.json')
        with open(parse_output, 'r') as f:
            mutated_class_parsed_json = json.load(f)
        for node in mutated_class_parsed_json["nodes"]:
            if node["type"] == "method" and isGoodComment(node["comment"]):
                good_comment_methods_in_mutated_class.append(node["signature"])
    
    mutants_dic = {}
    for mutated_class in mutated_classes:
        mutaed_class_log_path = os.path.join(env.mutants_logs, mutated_class)
        mutants = open(mutaed_class_log_path).readlines()
        for single_mutant in mutants:
            single_mutant_dic = parse_log(single_mutant)
            target = single_mutant_dic["target"]
            if '@' not in target:
                continue
            target_tmp = target.replace('@','.')
            if target_tmp not in good_comment_methods_in_mutated_class:
                continue
            if target not in mutants_dic.keys() :
                mutants_dic[target] = []
            info = single_mutant_dic["line"] + ':' + single_mutant_dic["before_after"]        
            mutants_dic[target].append(info)
    
    idx = 0
    for key, values in mutants_dic.items():
        for value in values:
            if not os.path.exists(env.mutated_dir):
                os.makedirs(env.mutated_dir)
            p = subprocess.run(
                shlex.split(f'cp -r {env.original_dir}/. {env.mutated_dir}')
            )
            mut_src_abs_path=os.path.join(env.mutated_dir, src_root_relpath)
            target_class=class_name_to_class_path(key.split('@')[0])
            target_class_java=class_name_to_java_path(key.split('@')[0])
            isSucceed, target_method_descriptor= get_descriptor(target_class, key.split('@')[1])
            if not isSucceed:
                continue 
            split_value=value.split(':')
            mutated_line_number = int(split_value[0])
            before = split_value[1].split(' |==> ')[0].strip()
            after = split_value[1].split(' |==> ')[1].strip()
            
            with open(os.path.join(mut_src_abs_path, target_class_java),'r+') as f:
                mutated_lines = []
                line_number = 0
                target_src = f.readlines()
                target_src.insert(0,'<start>')

                while line_number < len(target_src):
                    if line_number == mutated_line_number :
                        before_stmt = target_src[line_number]
                        while(before_stmt.find(before) == -1):
                            line_number = line_number+1
                            print(before_stmt)
                            print('**********')
                            before_stmt = before_stmt.rstrip() + ' '+ target_src[line_number].strip()
                            print(before_stmt)   
                        after_stmt = before_stmt.replace(before, after)
                        print(after_stmt)
                        mutated_lines.append(after_stmt)
                    else:
                        mutated_lines.append(target_src[line_number])
                    line_number = line_number+1
                f.seek(0)
                f.writelines(mutated_lines)
                f.truncate()
            
            p= subprocess.run(
                shlex.split('git diff'), stdout = subprocess.PIPE,
                universal_newlines = True, cwd= mut_src_abs_path
            )

            git_diff_dir = os.path.join(env.WORK_DIR,"git_diff")
            if not os.path.exists(git_diff_dir):
                os.mkdir(git_diff_dir)
            idx +=1
            print('****************',idx,'****************')
            with open(os.path.join(git_diff_dir,f'{idx}.diff'),'w') as f:
                f.write(p.stdout)
            with open(os.path.join(git_diff_dir,f'{idx}_a.diff'),'w') as f:
                f.write(f'{mutated_line_number} {before} {after}')
            p = subprocess.run(
                shlex.split(f'rm -r {env.mutated_dir}')
            )