import subprocess
import os
import pickle
from tqdm import tqdm 

# Define the parameters
param0 = "Chart"
param1 = "1"
param2 = "newTS_300"
param3 = "300"
param4 = "0"

file_path = "/root/workspace/src/select_dataset/Chart_mutants.pkl"
with open(file_path, 'rb') as file:
    diffs = pickle.load(file)
target = [diff.split('_')[0] for diff in diffs]

for i in tqdm(target[2363:]):  
    command = f"sh gen_test_mut.sh {param0} {param1} {i} {param2} {param3} {param4}"
    subprocess.run(command, shell=True)  

##Deletion
# mut_num = os.listdir("/root/workspace/result/Closure-1f_mutated")    
# for mut in mut_num:
#     if mut not in target and mut != 'dev_written_info' and mut != 'git_diff':
#         print(mut)
#         command = f"rm -rf  /root/workspace/result/Closure-1f_mutated/{mut}"
#         subprocess.run(command, shell=True)  
