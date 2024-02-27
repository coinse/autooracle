import os 
import csv
import subprocess
import pickle

def convert_local_to_docker_path(path):
    path  = path.replace("/home/coinse/Workspace/hslee_workspace/autooracle/workspace", "/root/workspace")
    return path

def exec_datafame_sh(start_idx, end_idx):
    directory = '/home/coinse/Workspace/hslee_workspace/autooracle/workspace/src/dataset_generation'
    subprocess.run(f"sh dataframe.sh {start_idx} {end_idx}", cwd=convert_local_to_docker_path(directory), shell=True)

def exec_prompt_sh(start_idx, end_idx):
    directory = '/home/coinse/Workspace/hslee_workspace/autooracle/workspace/src/prompt'
    subprocess.run(f"sh prompt.sh {start_idx} {end_idx}", cwd=convert_local_to_docker_path(directory), shell=True)

def exec_query_sh(start_idx, end_idx):
    directory = '/home/coinse/Workspace/hslee_workspace/autooracle/workspace/src/query'
    subprocess.run(f"sh query.sh {start_idx} {end_idx}", cwd=convert_local_to_docker_path(directory), shell=True)

def get_list_of_target_dic(project,version):
    dir = convert_local_to_docker_path(f"/home/coinse/Workspace/hslee_workspace/autooracle/workspace/result/{project}-{version}f_mutated/git_diff")
    all_method = {}

    for file in os.listdir(dir):
        if file.endswith("_info"):
            data = open(os.path.join(dir,file),'r')
            method_name = data.read().split(':')[1]
            if method_name in all_method:
                all_method[method_name].append(int(file.split('_')[0]))
            else:
                all_method[method_name] = [int(file.split('_')[0])]

    targets = {}
    for new_k, new_v in all_method.items():
        if max(new_v) - min(new_v) >= 10:
            targets[new_k] = min(new_v)

    return targets


if __name__ == "__main__":

    targets = get_list_of_target_dic("Lang", "1")
    
    with open('user.pickle','wb') as fw:
        pickle.dump(targets, fw)

    for method_name, start_idx in targets.items():

        end_idx = start_idx +3 

        print(method_name)   
        #exec_datafame_sh(start_idx,end_idx)
        #exec_prompt_sh(start_idx,end_idx)
        exec_query_sh(start_idx,end_idx)