import re
import pandas as pd 
import argparse
import os 

EVOSUITE_PATTERNS={
    "tc_no":r"//Test case number: (\d+)",
    "cov_end": "*/",
    "method":r'\[METHOD\] (\S+)'
}

def parse_tests(ts):
    tests_body={}
    tests_annotation = {}
    test_name = None
    cov_read = False
    is_test_intro = True
    test_intro = []

    for l in ts.split('\n'):
        stripped = l.strip()
        m = re.search(EVOSUITE_PATTERNS["tc_no"], stripped)
        if m:
            tc_no = m.group(1)
            test_name = "test" + tc_no
            tests_body[test_name] = []
            tests_annotation[test_name] = [l]
            cov_read = True
            is_test_intro = False
            continue

        if not is_test_intro and not cov_read:
            if test_name and l.rstrip() != "}":
                tests_body[test_name].append(l)
            continue
        
        if cov_read:
            tests_annotation[test_name].append(l)

        if not is_test_intro and stripped == EVOSUITE_PATTERNS["cov_end"]:
            cov_read = False
        
        if is_test_intro:
            test_intro.append(l)
            
    test_intro = "\n".join(test_intro)

    return test_intro, tests_body, tests_annotation

def oracle_parse(test_body):
    non_assert= []
    tests = {}
    idx = 0
    is_in_try = False
    try_block=''
    close_bracket_count = 0
    for line in test_body:
        if line.find('try {') != -1:
            is_in_try = True
            try_block += line
            continue
        
        if is_in_try and line.find('}') != -1:
            close_bracket_count += 1
            try_block += ('\n' + line)
            if close_bracket_count % 2 == 0:
                is_in_try = False 
                tests[idx] = '\n'.join(non_assert) + '\n'  + try_block + '\n' + '  }'
                try_block=''
                idx += 1
            continue

        if is_in_try:
            try_block += ('\n' + line)
            continue
        
        # non-assert
        if line.find('assert') == -1 and not is_in_try:
            non_assert.append(line)
            continue

        if line.find('assert') != -1:
            tests[idx] = '\n'.join(non_assert) + '\n'  + line + '\n' + '  }'
            idx += 1
            continue
    return tests

def change_annotation_number(tests_annotation, new_tc_no):
    pattern = r'(//Test case number: )(\d+)'
    replacement = r'\g<1>' + str(new_tc_no)
    tests_annotation[0] = re.sub(pattern, replacement, tests_annotation[0])
    comment = "\n".join(tests_annotation) 
    return comment

def change_tests_number(test_str, new_tc_no):
    pattern = r'(public void test)(\d+)(\(\))'
    replacement = r'\g<1>' + str(new_tc_no) + r'\g<3>'
    test_str = re.sub(pattern, replacement, test_str)
    return test_str

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('--evo_dir', type=str)
    args = parser.parse_args()

    evo_dir = args.evo_dir
    print('**********************')
    for dp, dn, fn in os.walk(evo_dir):
        for f in fn:
            if f.endswith("ESTest.java"):
                f_path = os.path.join(dp, f)
                with open(f_path, 'r') as f:
                    test_script = f.read()

                test_intro, tests_body, tests_annotation= parse_tests(test_script)
                new_file = test_intro + '\n' 
                new_tc_no = 0
                for test_name in tests_body:
                    splited_tests_str = oracle_parse(tests_body[test_name]) 
                    for idx in splited_tests_str:
                        new_file += change_annotation_number(tests_annotation[test_name], new_tc_no) + '\n' + change_tests_number(splited_tests_str[idx], new_tc_no) + '\n\n' 
                        new_tc_no += 1
                new_file += '}'
                print('**********************')
                with open(f_path, 'w') as f:
                    f.write(new_file)