import os 
import re 
from env import EvoD4jEnv
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

    env = EvoD4jEnv(project, version, ts_id)
    
    test_suite_dir = env.evosuite_test_dir
    test_suite_src_dir = env.evosuite_test_src_dir
    test_suite_zip = test_suite_dir + "/{}-{}.tar.bz2".format(project, version)
    fixed_tmp_dir = env.fixed_tmp_dir

    os.system("cd {} && tar -cjf {} *".format(
        test_suite_src_dir, test_suite_zip
    ))
    
    subprocess.run(
        shlex.split("defects4j test -w {} -s {}".format(fixed_tmp_dir, test_suite_zip)),
        universal_newlines=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    
    os.system("cd {} && cp failing_tests {}".format(fixed_tmp_dir, test_suite_dir))