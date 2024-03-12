
import os 
import argparse
import subprocess
import pickle
from tqdm import tqdm

def exec_query_sh(idx, test_no):
    directory = '/root/workspace/src/query'
    for try_no in range(1,6):
        subprocess.run(f"python query.py {project} {version} -idx {idx} -i {test_id} -pr {prompt_no} -ex {example} -t {try_no} -tno {test_no}" 
                    , cwd=(directory), shell=True)
        subprocess.run(f"python query.py {project} {version} -idx {idx} -i {test_id} -pr {prompt_no} -ex {example} -t {try_no} -trs -tno {test_no}" 
                    , cwd=(directory), shell=True)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()

    project = args.project
    version = args.version
    prompt_no = '8'
    example = '0'
    test_id = "newTS_300"
    
    with open(f'../select_dataset/{project}_tests_part1.pkl','rb') as fw:
        target = pickle.load(fw)
    for idx, (fail_list, pass_list)  in tqdm(list(target.items())):
        for fail_no in fail_list:
            exec_query_sh(idx, fail_no)
        for pass_no in pass_list:
            exec_query_sh(idx, pass_no)
