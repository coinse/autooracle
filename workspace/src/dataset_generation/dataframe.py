import sys
sys.path.append('../')
import os
import argparse
import subprocess 
import shlex
import pickle
import json
import re
from dotenv import load_dotenv
import pandas as pd 

from utils.evosuite import parse as parse_evosuite
from utils.transforming import transform
from utils.env import EvoD4jEnv
from utils.javadoc import get_javadoc
load_dotenv()

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def print_separator():
    print('*'*30)

def handle_skipped_comment(comment, test_name):
    if not comment:
        print('no_Comment:', test_name)
    elif re.search("non-Javadoc", comment):
        print('nonJavadoc:', test_name)
    elif not (re.search("@param", comment) and re.search("@return", comment)):
        print("@param", test_name)

def make_evo_tests_df():
    print_separator()
    print("Saving evo_tests_df")
    print(project+'-'+version, idx)
    print_separator()
    
    evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_test_src_trs', 'transformed', 'evo_target_method', 'line', 'comment'])
    for dp, dn, fn in os.walk(env.evosuite_test_src_dir):
        for f in fn:
            if f.endswith("ESTest.java"):
                relpath = os.path.relpath(os.path.join(dp, f), start = env.evosuite_test_src_dir)
                coverages, parsed_test_src, parsed_target_method = parse_evosuite(os.path.join(dp, f))

                for i in zip(coverages.items(), parsed_test_src.items(), parsed_target_method.items()):
                    evo_test_no = i[0][0]
                    target_line = i[0][1]
                    evo_test_src = i[1][1]
                    target_method = i[2][1]
                    comment, signature = get_javadoc(env, target_method, target_line) # target method, lines
                    if (comment == '') or re.search("non-Javadoc", comment) or not (re.search("@param", comment) and re.search("@return", comment)):
                        handle_skipped_comment(comment, evo_test_no)
                        continue
                    
                    transformed, evo_test_src_trs = transform(i[1][1])
                    tmp_df = pd.DataFrame({'dir':[os.path.dirname(relpath)], 'evo_relpath': [relpath], 'evo_test_no':[evo_test_no], 
                                           'evo_test_src':[evo_test_src], 'evo_test_src_trs':[evo_test_src_trs], 
                                           'transformed':[transformed], 'evo_target_method':[target_method], 
                                           'line':[target_line], 'comment':[comment], 'signature':[signature]})
                    evo_tests_df = pd.concat([evo_tests_df, tmp_df])
    return evo_tests_df

def make_dev_tests_df():
    print_separator()
    print('Saving dev_tests_df')
    print(project+'-'+version)
    print_separator()

    dev_tests_df = pd.DataFrame(columns=['dir', 'dev_relpath', 'dev_method_signature', 'dev_test_src'])

    dev_test_src_relpath = open(env.dev_test_relpath,'r').read()
    dev_test_src_dir_abspaths = os.path.join(env.fixed_tmp_dir, dev_test_src_relpath)

    dev_test_classes = open(env.dev_written_test_classes, 'r').read()
    dev_test_classes_list = dev_test_classes.split('\n')
    
    for dev_test_class in dev_test_classes_list:
        dev_test_relpath = class_name_to_test_path(dev_test_class)
        dev_test_class_path = os.path.join(dev_test_src_dir_abspaths, dev_test_relpath)
        dev_test_parse_output = env.dev_written_test_analyze + f"{dev_test_class}.json"
        
        with open(dev_test_class_path,'r+') as f:
            src = f.read()
            new_src = src.replace("package org.apache.commons.lang.enum;","//package org.apache.commons.lang.enum;")
            f.seek(0)
            f.write(new_src)
            f.close()

        if not os.path.exists(dev_test_parse_output):
            subprocess.run(
                shlex.split("java -jar {} {} {}".format(env.java_analyzer, dev_test_class_path, dev_test_parse_output))
            )
        if os.path.exists(dev_test_parse_output):
            with open(dev_test_parse_output, 'r') as f:
                data = json.load(f)
                for node in data["nodes"]:
                    if node["type"] == "method":
                        begin_line = node["begin_line"]
                        end_line = node["end_line"]
                        with open(dev_test_class_path, 'r',encoding='cp1252') as g:
                            line = 0
                            source = ""
                            for l in g:
                                line += 1 
                                if line >= begin_line and line <= end_line:
                                    source += l
                            tmp_df = pd.DataFrame({'dir':[os.path.dirname(dev_test_relpath)], 'dev_relpath': [dev_test_relpath], 'dev_method_signature':[node["signature"]], 'dev_test_src':[source]})
                            dev_tests_df = pd.concat([dev_tests_df, tmp_df])
    return dev_tests_df


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--index', '-idx', type=str, default= '1')
    parser.add_argument('--id', '-i', type=str, default='newTS')
    args = parser.parse_args()
    
    project = args.project
    version = args.version
    idx = args.index
    ts_id = args.id

    print_separator()
    print("Saving Dataframe")
    print(project+'-'+version, idx)
    print_separator()

    env = EvoD4jEnv(project, version, idx, ts_id)

    evo_tests_df_path = os.path.join(env.evosuite_test_dir,'evo_tests_df_new.pkl')
   
    if not os.path.exists(evo_tests_df_path):
        evo_tests_df = make_evo_tests_df()
        with open(evo_tests_df_path,'wb') as f:
            pickle.dump(evo_tests_df,f)
   
    dev_tests_df_path = env.dev_tests_df_path
    
    if not os.path.exists(dev_tests_df_path):
        os.makedirs(env.dev_written_test_analyze)
        dev_tests_df = make_dev_tests_df()
        with open(dev_tests_df_path,'wb') as f:
            pickle.dump(dev_tests_df,f)