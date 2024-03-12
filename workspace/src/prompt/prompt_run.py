import subprocess
from tqdm import tqdm
import pickle

param0 = "Chart"
param1 = "1"
param2 = "newTS_300"

file_path = "/root/workspace/src/select_dataset/Chart_mutants.pkl"
with open(file_path, 'rb') as file:
    diffs = pickle.load(file)
target = [diff.split('_')[0] for diff in diffs]

for i in tqdm(target[:2364]): 
    subprocess.run(["python", "prompt.py", param0, param1, "-idx", i, "-i", param2, "-pr", str(8), "-ex", str(0)])
