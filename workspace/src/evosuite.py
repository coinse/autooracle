import re
import pandas as pd 
EVOSUITE_PATTERNS={
    "tc_no":r"//Test case number: (\d+)",
    "line_goal":r"\* Goal \d+\. ([^:]+): Line (\d+)",
    "cov_end": "*/",
    "method":r'\[METHOD\] (\S+)'
}

def parse(path):
    coverages={}
    tests_body={}
    target_method={}
    with open(path,"r") as test_file:
        test_name = None
        cov_read = False
        for l in test_file:
            stripped = l.strip()
            m = re.search(EVOSUITE_PATTERNS["tc_no"], stripped)
            if m:
                tc_no = m.group(1)
                test_name = "test" + tc_no
                coverages[test_name] = []
                tests_body[test_name] = []
                target_method[test_name] = []
                cov_read = True
                continue
            if not cov_read:
                if test_name and l.rstrip() != "}":
                    tests_body[test_name].append(l)
                continue
            if stripped == EVOSUITE_PATTERNS["cov_end"]:
                cov_read = False
                continue
            m = re.search(EVOSUITE_PATTERNS["line_goal"], stripped)
            if m:
                coverages[test_name].append((m.group(1), m.group(2)))
            m = re.search(EVOSUITE_PATTERNS["method"], stripped)
            if m:
                target_method[test_name].append(m.group(1))
    for test_name in tests_body:
        tests_body[test_name] = "".join(tests_body[test_name]).strip()
    return coverages, tests_body, target_method
