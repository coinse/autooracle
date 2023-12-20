import os
import argparse
from utils.evosuite import parse as parse_evosuite
from utils.relatedTest import cal_evo_embedding, cal_dev_embedding
from utils.transforming import transform
from utils.env import EvoD4jEnv
import pandas as pd 
import subprocess 
import shlex
import pickle
import json
import pandas as pd 
from dotenv import load_dotenv

load_dotenv()

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def get_evo_df():
    print('Making evo_tests_df')
    evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_test_src_trs', 'transformed', 'evo_target_method','line'])
    for dp, dn, fn in os.walk(env.evosuite_test_src_dir):
        for f in fn:
            if f.endswith("ESTest.java"):
                relpath = os.path.relpath(os.path.join(dp, f), start = env.evosuite_test_src_dir)
                coverages, parsed_test_src, parsed_target_method = parse_evosuite(os.path.join(dp, f))
                for i in zip(coverages.items(), parsed_test_src.items(), parsed_target_method.items()):
                    transformed, evo_test_src_trs = transform(i[1][1])
                    tmp_df = pd.DataFrame({'dir':[os.path.dirname(relpath)], 'evo_relpath': [relpath], 'evo_test_no':[i[0][0]], 'evo_test_src':[i[1][1]], 'evo_test_src_trs':[evo_test_src_trs],'transformed':[transformed], 'evo_target_method':[i[2][1]], 'line':[i[0][1]]})
                    evo_tests_df = pd.concat([evo_tests_df, tmp_df])
    evo_tests_df = cal_evo_embedding(env, evo_tests_df)
    return evo_tests_df

def get_dev_tests_df():
    print('Making dev_tests_df')
    dev_tests_df = pd.DataFrame(columns=['dir', 'dev_relpath', 'dev_method_signature', 'dev_test_src'])
    #get test source directory: dev_test_src_relpath
    dev_test_src_relpath = open(env.dev_test_relpath,'r').read()
    dev_test_src_dir_abspaths = os.path.join(env.fixed_tmp_dir, dev_test_src_relpath)

    # extract developer written test classes
    dev_test_classes = open(env.dev_written_test_classes, 'r').read()
    dev_test_classes_list = dev_test_classes.split('\n')
    
    for dev_test_class in dev_test_classes_list:
        dev_test_relpath = class_name_to_test_path(dev_test_class)
        dev_test_class_path = os.path.join(dev_test_src_dir_abspaths, dev_test_relpath)
        dev_test_parse_output = env.dev_written_test_analyze + "{}.json".format(dev_test_class)
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
    dev_tests_df = cal_dev_embedding(env, dev_tests_df)
    return dev_tests_df


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--index', '-idx', type=str, default= '1')
    parser.add_argument('--id', '-i', type=str, default='newTS')
    parser.add_argument('--mutation', '-mut', action='store_true')
    args = parser.parse_args()
    
    project = args.project
    version = args.version
    idx = args.index
    ts_id = args.id
    mut = args.mutation

    print('*'*30)
    print("Dataframe")
    print(project+'-'+version, idx)
    print('*'*30)

    env = EvoD4jEnv(project, version, idx, ts_id, mut)

    # 1. Make two dataframes that contain (evosuite test suite / developer test suite)
    evo_tests_df_path = os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl')
    if not os.path.exists(evo_tests_df_path):
        evo_tests_df = get_evo_df()
        with open(evo_tests_df_path,'wb') as f:
            pickle.dump(evo_tests_df,f)

    if not mut:
        dev_tests_df_path = os.path.join(env.evosuite_test_dir, 'dev_tests_df.pkl')
    else:
        dev_tests_df_path = env.dev_tests_df_path
    if not os.path.exists(dev_tests_df_path):
        os.makedirs(env.dev_written_test_analyze)
        dev_tests_df = get_dev_tests_df()
        with open(dev_tests_df_path,'wb') as f:
            pickle.dump(dev_tests_df,f)