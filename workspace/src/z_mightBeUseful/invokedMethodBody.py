import pandas as pd
import pickle
import argparse
from utils.env import EvoD4jEnv
import os 
import subprocess 
import re
import json
import shlex

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def invoked_method(env, target_methods):

    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.buggy_tmp_dir, src_root_relpath)

    invoked_method_str = ''

    for target in target_methods:
        pattern = r'(\S+)\.(\S+)\(([^)]*)\)[\S]*'
        match = re.match(pattern, target)
        if match:
            class_name = match.group(1)
            print(target, class_name)
            invoked_class_abs_path = os.path.join(src_root_abs_path, class_name_to_test_path(class_name))
            parse_output = os.path.join(env.dev_written_src_analyze, "{}.json".format(class_name))
            print('*'*30)
            print('get_invoked_method_body')
            print('*'*30)
            print(invoked_class_abs_path)
        
            p = subprocess.run(
                    shlex.split("java -jar {} {} {}".format(env.java_analyzer, invoked_class_abs_path, parse_output))
                )

            if p.returncode != 0:
                continue
            with open(parse_output,'r') as f:
                parse_output = json.load(f)
            method_name_cut = re.findall(r'^([^(\s]+)', target)[0]
            source = ""
            for node in parse_output["nodes"]:
                if (node["type"] == "method" and node["signature"].find(method_name_cut) != -1) or (node["type"] == "constructor" and (method_name_cut=='<clinit>' or method_name_cut=='<init>')):
                    begin_line = node["begin_line"]
                    end_line = node["end_line"]
                    with open(invoked_class_abs_path, 'r', encoding='cp1252') as g:
                        line = 0
                        for l in g:
                            line += 1 
                            if line >= begin_line and line <= end_line:
                                source += l
            invoked_method_str += source
    return invoked_method_str        