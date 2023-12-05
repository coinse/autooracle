from utils.env_mut import EvoD4jEnvMut
import argparse
import subprocess 
import shlex
import json
import re
import os

def class_name_to_java_path(class_name):
    return class_name.replace('.', '/')+'.java'

def class_name_to_class_path(class_name):
    return class_name.replace('.', '/')+'.class'

def get_descriptor(target):
    isSucceed = False
    target_class = target.split('@')[0]
    target_method = target.split('@')[1]

    with open(os.path.join(env.metadata_dir, "dir.bin.classes")) as f:
        target_path = f.read()

    if not os.path.exists(os.path.join(env.metadata_dir,f'{target_class}_{target_method}')):
        p = subprocess.run(
            shlex.split(f"javap -s {os.path.join(env.original_dir, target_path, class_name_to_class_path(target_class))}"), stdout = subprocess.PIPE,
            universal_newlines = True
        )
        with open(os.path.join(env.metadata_dir,f'{target_class}_{target_method}'), 'w') as f:
            f.write(p.stdout)
            
    with open(os.path.join(env.metadata_dir,f'{target_class}_{target_method}'),'r') as f:
        javap_result = f.readlines()

    for idx, line in enumerate(javap_result):
        if line.find(target_method) != -1:
            descriptor = target_method.split('(')[0] + javap_result[idx + 1].split(':')[1].strip()
            isSucceed = True
            break
    if isSucceed:
        return isSucceed, descriptor
    else:
        return isSucceed, 'no descriptor'
    

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
   
    # Source directory of classes
    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.original_dir, src_root_relpath)
    
    # Mutated class list
    mutated_classes = os.listdir(env.mutants_files) ##.log 로 끝남
    # Parse the mutated classes code.
    parse_output_dir = os.path.join(env.metadata_dir, "parse_output")
    if not os.path.exists(parse_output_dir):
        os.makedirs(parse_output_dir)
    for mutated_class in mutated_classes:
        if mutated_class.find('$') != -1:
            mutated_class = mutated_class.split('$')[0]
        class_abs_path=os.path.join(src_root_abs_path, class_name_to_java_path(mutated_class.strip()))
        if os.path.exists(class_abs_path):
            parse_output=os.path.join(parse_output_dir,f'{mutated_class.strip()}.json')
            if not os.path.exists(parse_output):
                p = subprocess.run(
                    shlex.split(f'java -jar {env.java_analyzer} {class_abs_path} {parse_output}')
                )
    
    # Get the signature of good commented methods and read parsed result
    good_comment_methods_in_mutated_class =[]
    for mutated_class in mutated_classes:
        if mutated_class.find('$') != -1:
            mutated_class = mutated_class.split('$')[0]
        parse_output=os.path.join(parse_output_dir, f'{mutated_class.strip()}.json')
        with open(parse_output, 'r') as f:
            mutated_class_parsed_json = json.load(f)
        for node in mutated_class_parsed_json["nodes"]:
            if node["type"] == "method" and isGoodComment(node["comment"]):
                good_comment_methods_in_mutated_class.append(node["signature"])

    # # Filter mutants that mutated the methods with good comment and add descriptor
    mutants_dic = {}
    for mutated_class in mutated_classes:
        mutaed_class_log_path = os.path.join(env.mutants_logs, mutated_class+'.log')
        mutants = open(mutaed_class_log_path).readlines()
        for single_mutant in mutants:
            single_mutant_dic = parse_log(single_mutant)
            target = single_mutant_dic["target"]
            # 1. 함수를 뮤테이션한게 아니면 pass
            if '@' not in target:
                continue
            # 2. 좋은 커멘트가 아니면 pass
            target_signature = target.replace('@','.')
            if target_signature not in good_comment_methods_in_mutated_class:
                continue
            # 3. descriptor가 없으면 pass
            isSucceed, descriptor = get_descriptor(target)
            if not isSucceed:
                continue
            if mutated_class not in mutants_dic.keys() :
                mutants_dic[mutated_class] = []
            info = (single_mutant_dic["mutant_no"], descriptor)
            mutants_dic[mutated_class].append(info)

    if not os.path.exists(env.before_mutated_dir):
        p = subprocess.run(
            shlex.split(f'defects4j checkout -p {project} -v {version}f -w {env.before_mutated_dir}')
        )
    # # key : class , value : mutant_no (e.g., org.apache.commons.lang3.StringEscapeUtils$CscEscaper:[(2,descriptor), (3,descriptor), ...])
    idx = 0
    for key, values in mutants_dic.items():
        print(key)
        for value in values:
            p = subprocess.run(
                shlex.split(f'cp -r {env.before_mutated_dir}/. {env.after_mutated_dir}')
            )
            after_mutated_src_root_path = os.path.join(env.after_mutated_dir, src_root_relpath)
            for dp, dn, fn in os.walk(os.path.join(env.mutants_files, key, value[0])):
                for f in fn:
                    if f.endswith('.java'):
                        relpath = os.path.relpath(os.path.join(dp,f), start = os.path.join(env.mutants_files, key, value[0]))
                        run_mut_cp = subprocess.run(
                            shlex.split(f'cp {os.path.join(dp,f)} {os.path.join(after_mutated_src_root_path,relpath)}')
                        )
                        run_git_diff= subprocess.run(
                            shlex.split('git diff'), stdout = subprocess.PIPE,
                            universal_newlines = True, cwd= env.after_mutated_dir, encoding='utf-8', errors='ignore'
                        )
                        git_diff_dir = os.path.join(env.WORK_DIR,'result',f'{project}-{version}f_mutated','git_diff')
                        if not os.path.exists(git_diff_dir):
                            os.makedirs(git_diff_dir)
                        with open(os.path.join(git_diff_dir,f'{idx}.diff'),'w') as f:
                            f.write(run_git_diff.stdout)
                        with open(os.path.join(git_diff_dir,f'{idx}_info'),'w') as f:
                            f.write(f'{key} : {value[1]}')
                        run_mut_dir_rm = subprocess.run(
                            shlex.split(f'rm -r {env.after_mutated_dir}')
                        )
                        idx += 1
        