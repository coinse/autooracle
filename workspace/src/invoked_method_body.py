import pandas as pd
import pickle
import argparse
from env import EvoD4jEnv
import os 
import subprocess 
import re
import json
import shlex

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def test_path_to_class_name(test_path):
    return test_path[:-5].replace('/', '.')


def scrap_body(invoked_df, parse_output, invoked_class_abs_path, method_name):
    lsd = []
    for node in parse_output["nodes"]:
            if (node["type"] == "method" and node["signature"] == method_name) or (node["type"] == "constructor" and method_name == 'constructor'):
                begin_line = node["begin_line"]
                end_line = node["end_line"]
                with open(invoked_class_abs_path, 'r') as g:
                    line = 0
                    source = ""
                    for l in g:
                        line += 1 
                        if line >= begin_line and line <= end_line:
                            source += l
                    lsd.append(source)
    return lsd


# shoudl make a data frame that the coulms are including the evo_target_method | tarege_method_src 
# org.apache.commons.lang3.StringUtils.isBlank(Ljava/lang/CharSequence;)Z
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    args = parser.parse_args()

    project = args.project
    version = args.version
    ts_id = args.id
    
    env = EvoD4jEnv(project, version, ts_id)    
    
    src_root_relpath = open(env.dev_src_relpath, 'r').read()
    src_root_abs_path = os.path.join(env.buggy_tmp_dir,src_root_relpath)

    invoked_class_rel_path_list = list(filter(lambda x: not x.endswith('.budget'), os.listdir(env.relevant_methods)))
    # invoked_class_rel_path_list = [org.apache.commons.lang3.math.NumberUtils, org.apache.commons.lang3.StringUtils]


    invoked_df = pd.DataFrame(columns=['evo_target_method', 'target_method_src'])
    for class_name in invoked_class_rel_path_list:
        invoked_class_abs_path = os.path.join(src_root_abs_path, class_name_to_test_path(class_name))
        
        parse_output = os.path.join(env.dev_written_src_analyze, "{}.json".format(class_name))

        subprocess.run(
            shlex.split("java -jar {} {} {}".format(env.java_analyzer, invoked_class_abs_path, parse_output))
        )

        with open(os.path.join(env.relevant_methods, class_name),'r') as f:
            wanna_find_method_list = f.read().split()

        with open(parse_output,'r') as f:
            parse_output = json.load(f)

        for method_name in wanna_find_method_list:
            method_name_cut = re.findall(r'^([^(\s]+)', method_name)[0]
            print(method_name_cut)
            for node in parse_output["nodes"]:
                if (node["type"] == "method" and node["signature"].find(method_name_cut) != -1) or (node["type"] == "constructor" and (method_name_cut=='<clinit>' or method_name_cut=='<init>')):
                    begin_line = node["begin_line"]
                    end_line = node["end_line"]
                    with open(invoked_class_abs_path, 'r') as g:
                        line = 0
                        source = ""
                        for l in g:
                            line += 1 
                            if line >= begin_line and line <= end_line:
                                source += l
                        if method_name.find('<clinit>') != -1:
                            method_name = method_name.replace('<clinit>', '<init>')
                        tmp_df = pd.DataFrame({'evo_target_method':[class_name+'.'+method_name], 'target_method_src': [source]})
                        invoked_df = pd.concat([invoked_df, tmp_df])

    with open( env.metadata_dir + '/invoked_method_df.pkl','wb') as f:
        pickle.dump(invoked_df,f)

    invoked_df.to_csv('./a.csv')
