import subprocess
import pickle
from tqdm import tqdm 

# Define the parameters
param0 = "Math"
param1 = "1"
param2 = "newTS_300"
param3 = "300"
param4 = "0"

file_path = "/root/workspace/src/select_dataset/Math_mutants.pkl"
with open(file_path, 'rb') as file:
    diffs = pickle.load(file)
target = [diff.split('_')[0] for diff in diffs]


for i in tqdm(target[594:900]):  
    command = f"sh gen_test_mut.sh {param0} {param1} {i} {param2} {param3} {param4}"
    subprocess.run(command, shell=True)  
    
