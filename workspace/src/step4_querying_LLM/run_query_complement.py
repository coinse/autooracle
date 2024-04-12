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
    args = parser.parse_args()

    project = args.project
    version = 1
    prompt_no = '10'
    example = '0'
    test_id = "newTS_300"
    
    with open(f'{project}_complement.txt','r') as f:
        target = f.readlines()

    for t in target:
        idx, test_no = t.strip().split()

        directory = '/root/workspace/src/query'
        for try_no in range(1,6):
            subprocess.run(f"python query.py {project} {version} -idx {idx} -i {test_id} -pr {prompt_no} -ex {example} -t {try_no} -tno {test_no}" 
                        , cwd=(directory), shell=True)
            subprocess.run(f"python query.py {project} {version} -idx {idx} -i {test_id} -pr {prompt_no} -ex {example} -t {try_no} -trs -tno {test_no}" 
                        , cwd=(directory), shell=True)
