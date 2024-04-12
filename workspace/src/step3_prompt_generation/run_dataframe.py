import subprocess
from tqdm import tqdm
import pickle

param0 = "Closure"
param1 = "1"
param2 = "newTS_300"

file_path = "/root/workspace/src/select_dataset/Chart_mutants.pkl"
with open(file_path, 'rb') as file:
    diffs = pickle.load(file)
target = [diff.split('_')[0] for diff in diffs]

for i in tqdm(range(0,343)): 
    subprocess.run(["python", "dataframe.py", param0, param1, "-idx", str(i), "-i", param2])