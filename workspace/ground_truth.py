import os 
import re 
import shlex
import argparse
import subprocess
####################
####ground truth####
####################



if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=int)
    parser.add_argument('--id','-i', type=str, default='1')
    args = parser.parse_args()

    project = args.project
    version = args.version
    ts_id = args.id

    
    test_suite_dir="/root/workspace/result/{}-{}b/{}/".format(project, version, ts_id)
    test_suite_src_dir=test_suite_dir + "evosuite_test"
    test_suite_zip=test_suite_dir + "{}-{}.tar.bz2".format(project, version)

    fixed_tmp_dir="/tmp/{}-{}f".format(project, version)
    #fixed_tmp_dir="/tmp/{}-{}b".format(project, version)

    os.system("cd {} && tar -cjf {} *".format(
        test_suite_src_dir, test_suite_zip
    ))
    
    gt = subprocess.run(
        shlex.split("defects4j test -w {} -s {}".format(fixed_tmp_dir, test_suite_zip)),
        universal_newlines=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    failing_tests = re.findall(r'-\s+(.+)',gt.stdout)

    if len(failing_tests) > 0 :
        with open(test_suite_dir+"failng_test.txt",'w') as f:
            for failing_test in failing_tests:
                f.write(failing_test+"\n")
    else :
        print("no failing test")