import sys
sys.path.append('../')

import re
import subprocess
import os
from utils.env import EvoD4jEnv

def get_failing_test_list(fail_log_path):
    failing_test_list = []
    with open(fail_log_path, 'r') as f:
        data = f.readlines()
    for l in data:
        if l.startswith('---'):
            pattern = r"--- (.*?)::(.*?)$"
            match = re.search(pattern, l)
            if match:
                split_fail_test_class = match.group(1).split('.')
                fail_test_class = '.'.join(split_fail_test_class[:-1])
                fail_test_no = match.group(2).strip()
                if fail_test_no[4]=='0' and len(fail_test_no)>5:
                    fail_test_no = fail_test_no[:4]+fail_test_no[5]
                failing_test_list.append((fail_test_no, fail_test_class))
    return failing_test_list

def exec_query_sh(idx, prompt_no, test_no):
    directory = '/root/workspace/src/query'
    subprocess.run(f"sh query_testno.sh {idx} {prompt_no} {test_no}", cwd=directory, shell=True)

if __name__ == "__main__":
    prompt_no = 8 
    for idx in range(0,4724):
        env = EvoD4jEnv("Lang", "1", str(idx), ts_id= "newTS_300")
        fail_log_path = os.path.join(env.evosuite_test_dir, "failing_tests_on_fixed")
        failing_test_list = []
        if os.path.exists(fail_log_path):
            failing_test_list = get_failing_test_list(fail_log_path)
        for test in failing_test_list:
            exec_query_sh(idx, prompt_no, test[0])
            

    