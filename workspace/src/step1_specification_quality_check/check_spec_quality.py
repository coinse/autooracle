import sys
sys.path.append('../')
import os
import argparse
from utils.env import EvoD4jEnv
import random
import pickle
from math import floor

def parse_diff_file(diff_path):
    """Parse a diff file to extract starting line numbers of changes."""
    with open(diff_path) as file:
        for line in file:
            if line.startswith('@@'):
                parts = line.split(' ')
                line_number = int(parts[2].split(',')[0][1:])  # Extract start line number
    return line_number

def randomly_select_one_value_per_key(dic):
    selected = []
    for key, value in dic.items():
        selected.append(random.sample(value,1)[0])
    return selected

def randomly_select_multiple_value_per_key(dic):
    selected = []
    select_num = floor(10/len(dic.keys()))
    for key, value in dic.items():
        if len(value) < select_num:
            selected.extend(value)  # Add all values if the length is smaller than select_num
        else:
            selected.append(random.sample(value, select_num)[0])
    return selected

def select_random_diffs_per_line_number(diff_dir, infos):
    cand = {} # key: line_number , value: idxs
    for info in infos:
        diff = info[:info.find('_')] + ".diff"
        diff_path = os.path.join(diff_dir, diff)
        line_number = parse_diff_file(diff_path)
        if line_number not in cand:
            cand[line_number] = [info]
        else:
            cand[line_number].append(info)
    selected_elements = []
    # Randomly select 10 elements in set if the length of set is over than 10
    if len(cand) > 10:
        selected_line_number = random.sample(list(cand.keys()), 10)
        filtered_dict = {line_nubmer: cand[line_nubmer] for line_nubmer in selected_line_number}
        selected_elements = randomly_select_one_value_per_key(filtered_dict)
    else:
        selected_elements = randomly_select_multiple_value_per_key(cand)
    return selected_elements

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()

    project = args.project
    version = args.version
    
    env = EvoD4jEnv(project, version)

    diff_dir = os.path.join(env.WORK_DIR, f"result/{project}-{version}f_mutated/git_diff")

    diff_infos = filter(lambda x: x.endswith("info"), os.listdir(diff_dir))

    
    info_dic = {} # key: class, value: dictionary (key: method, value: infos)

    for info in diff_infos:
        contents = open(os.path.join(diff_dir,info)).read()
        class_name = contents.split(":")[0].strip()
        method_name = contents.split(":")[1].strip()
        if class_name not in info_dic:
            info_dic[class_name] = {method_name: [info]}
        elif class_name in info_dic and method_name not in info_dic[class_name]:
            info_dic[class_name][method_name] = [info]
        elif class_name in info_dic and method_name in info_dic[class_name]:
            info_dic[class_name][method_name].append(info)
    
    target_diffs = []
    for class_name, method_dic in info_dic.items():
        for method_name, infos in method_dic.items():
            if len(infos) <= 10: 
                target_diffs.extend(infos)
            else: 
                target_diffs.extend(select_random_diffs_per_line_number(diff_dir,infos))
    
    print(len(target_diffs))
    with open(f'./selected_methods/{project}_mutants.pkl','wb') as f:
        pickle.dump(target_diffs, f)