import sys
sys.path.append('../')
import re
import os
import argparse
import pickle
from utils.env import EvoD4jEnv
from tqdm import tqdm


def get_failing_test_list(j,fail_log_path):
    real_failing_test_list = []
    fake_failing_test_list = []
    with open(fail_log_path, 'r') as f:
        data = f.readlines()

    for i,l in enumerate(data):
        if l.startswith('---'):
            pattern = r"--- (.*?)::(.*?)$"
            match = re.search(pattern, l)
            if match:
                
                split_fail_test_class = match.group(1).split('.')
                fail_test_class = '.'.join(split_fail_test_class[:-1])
                fail_test_no = match.group(2).strip()
                if fail_test_no[4]=='0' and len(fail_test_no)>5:
                    fail_test_no = fail_test_no[:4]+fail_test_no[5]

                if data[i+1].find('AssertionFailedError') == -1:
                    fake_failing_test_list.append(fail_test_no)
                else:
                    real_failing_test_list.append(fail_test_no)
    return real_failing_test_list, fake_failing_test_list

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()

    project = args.project
    version = args.version
    test_id = "newTS_300"

    # Key: index -> int, value: [failing list, passing list]
    dataset={}
    total = 0
    if project != "Closure":
        file_path = f"/root/workspace/src/select_dataset/{project}_mutants.pkl"
        with open(file_path, 'rb') as file:
            diffs = pickle.load(file)
        target = [diff.split('_')[0] for diff in diffs]
    #for closure
    else:
        target = range(0,343)
    f_total, p_total = 0, 0
    for i in target:
        env = EvoD4jEnv(project, version, str(i), test_id)
        failing_log_path = os.path.join(env.evosuite_test_dir, "failing_tests_on_fixed")
        if not os.path.exists(failing_log_path):
            real_failing_test_list = []
            fake_failing_test_list = []
        else:
            real_failing_test_list = []
            fake_failing_test_list = []
            real_fail, fake_fail = get_failing_test_list(i,failing_log_path)
            real_failing_test_list.extend(real_fail)
            fake_failing_test_list.extend(fake_fail)
        prompt_path = os.path.join(env.evosuite_prompt_dir, 'prompt8/example0')
        if not os.listdir(env.evosuite_prompt_dir):
            continue
        all_test_in_prompt_dir = list(filter(lambda x: x.split('_')[2] != 'try', os.listdir(prompt_path)))
        all_test_in_prompt_dir = set(map(lambda x: x.split('_')[1], all_test_in_prompt_dir))
        failing_tests_in_prompt_dir = set(filter(lambda x: x in all_test_in_prompt_dir, real_failing_test_list))
        fake_failing_tests_in_prompt_dir = set(filter(lambda x: x in all_test_in_prompt_dir, fake_failing_test_list))
        passing_tests_in_prompt_dir = all_test_in_prompt_dir.difference(failing_tests_in_prompt_dir)
        passing_tests_in_prompt_dir = passing_tests_in_prompt_dir.difference(fake_failing_test_list)
        f_total += len(failing_tests_in_prompt_dir) 
        p_total += len(passing_tests_in_prompt_dir)

        difference_in_numbers = len(passing_tests_in_prompt_dir) - len(failing_tests_in_prompt_dir)
        if difference_in_numbers > 0 :
            for _ in range(difference_in_numbers):
                passing_tests_in_prompt_dir.pop()
        elif difference_in_numbers < 0 :
            for _ in range(abs(difference_in_numbers)):
                failing_tests_in_prompt_dir.pop()

        if len(failing_tests_in_prompt_dir) > 0 and len(passing_tests_in_prompt_dir) > 0 :
            dataset[i] = [list(failing_tests_in_prompt_dir), list(passing_tests_in_prompt_dir)]  


    f_selected = 0
    p_selected = 0
    for idx, (f_test_no, p_test_no) in dataset.items():
        f_selected += len(f_test_no)
        p_selected += len(p_test_no)
    print(f_total, p_total)         
    print(f_selected, p_selected)

    with open(f'{project}_tests_new.pkl','wb') as f:
       pickle.dump(dataset, f)