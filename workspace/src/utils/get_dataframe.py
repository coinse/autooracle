import os
from utils.evosuite import parse as parse_evosuite
import pandas as pd 
import subprocess 
import shlex
import pickle
import json
import pandas as pd 
import numpy

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def get_evo_df(env):
    print('*'*30)
    print('make evo_tests_df')
    print('*'*30+'\n')
    evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_target_method','line'])
    for dp, dn, fn in os.walk(env.evosuite_test_src_dir):
        for f in fn:
            if f.endswith("ESTest.java"):
                relpath = os.path.relpath(os.path.join(dp, f), start = env.evosuite_test_src_dir)
                coverages, parsed_test_src, parsed_target_method = parse_evosuite(os.path.join(dp, f))
                for i in zip(coverages.items(), parsed_test_src.items(), parsed_target_method.items()):
                    tmp_df = pd.DataFrame({'dir':[os.path.dirname(relpath)], 'evo_relpath': [relpath], 'evo_test_no':[i[0][0]], 'evo_test_src':[i[1][1]], 'evo_target_method':[i[2][1]], 'line':[i[0][1]]})
                    evo_tests_df = pd.concat([evo_tests_df, tmp_df])
    return evo_tests_df

def get_dev_tests_df(env):
    print('*'*30)
    print('make dev_tests_df')
    print('*'*30+'\n')
   
    dev_tests_df = pd.DataFrame(columns=['dir', 'dev_relpath', 'dev_method_signature', 'dev_test_src'])

    #get test source directory: dev_test_src_relpath
    dev_test_src_relpath = open(env.dev_test_relpath,'r').read()
    dev_test_src_dir_abspaths = os.path.join(env.buggy_tmp_dir, dev_test_src_relpath)
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
    return dev_tests_df



# def get_evo_df(env):
#     print('*'*30)
#     print('make evo_tests_df')
#     print('*'*30+'\n')
#     evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_target_method', 'evo_test_embedding'])
#     for dp, dn, fn in os.walk(env.evosuite_test_src_dir):
#         for f in fn:
#             if f.endswith("ESTest.java"):
#                 print(f)
#                 relpath = os.path.relpath(os.path.join(dp, f), start = env.evosuite_test_src_dir)
#                 _, parsed_test_src, parsed_target_method = parse_evosuite(os.path.join(dp, f))
#                 for i in zip(parsed_test_src.items(), parsed_target_method.items()):
#                     evo_embedding = model.encode(i[0][1], convert_to_tensor=True)
#                     tmp_df = pd.DataFrame({'dir':os.path.dirname(relpath), 'evo_relpath': [relpath], 'evo_test_no':[i[0][0]], 'evo_test_src':[i[0][1]], 'evo_target_method':[i[1][1]], 'evo_test_embedding':[evo_embedding]})
#                     evo_tests_df = pd.concat([evo_tests_df, tmp_df])
#     evo_tests_df.to_csv(os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl'))
#     with open( os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl'),'wb') as f:
#             pickle.dump(evo_tests_df,f)
#     return evo_tests_df
