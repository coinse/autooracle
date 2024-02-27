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

def get_javadoc(env, target_methods, lines):

    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.fixed_tmp_dir, src_root_relpath)

    comment_str = ''
    signature = ''

    for target in target_methods:
        pattern = r'(\S+)\.(\S+)\(([^)]*)\)[\S]*'
        if len(lines) == 0:
            continue
        line = int(lines[0][1])
        match = re.match(pattern, target)
        if match:
            class_name = match.group(1)
            invoked_class_abs_path = os.path.join(src_root_abs_path, class_name_to_test_path(class_name))
            if not os.path.exists(env.dev_written_src_analyze):
                os.makedirs(env.dev_written_src_analyze)
            parse_output = os.path.join(env.dev_written_src_analyze, "{}.json".format(class_name))
            if not os.path.exists(parse_output):
                p = subprocess.run(
                        shlex.split("java -jar {} {} {}".format(env.java_analyzer, invoked_class_abs_path, parse_output))
                    )
                if p.returncode != 0:
                    continue
            with open(parse_output,'r') as f:
                parse_output = json.load(f)
            method_name_cut = re.findall(r'^([^(\s]+)', target)[0]
            for node in parse_output["nodes"]:
                if (node["type"] == "method" and node["signature"].find(method_name_cut) != -1):
                    if (node["begin_line"] <= line and node["end_line"] >= line):
                        comment_str += "signature: " + node["signature"] + "\n"
                        comment_str += node["comment"]
                        signature = node["signature"]
                        break
    return comment_str, signature        