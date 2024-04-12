import subprocess
import os
import pickle
import argparse
from tqdm import tqdm 

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()

    project = args.project
    version = args.version  

    param2 = "newTS_300"
    param3 = "300"
    param4 = "0"

    file_path = "/root/workspace/src/select_dataset/Chart_mutants.pkl"
    with open(file_path, 'rb') as file:
        diffs = pickle.load(file)
    target = [diff.split('_')[0] for diff in diffs]

    for i in tqdm(target):  
        command = f"sh gen_test_mut.sh {project} {version} {i} {param2} {param3} {param4}"
        subprocess.run(command, shell=True)  
