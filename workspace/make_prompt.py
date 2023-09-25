import os 
import argparse
import subprocess 
import shlex
import tiktoken 
import pickle
import json
from env import EvoD4jEnv
from evosuite import parse as parse_evosuite
import torch
import pandas as pd
import numpy as np
from sentence_transformers import SentenceTransformer, util

system_message = {"role":"system", "content":"You are an assistant to help me assess the correctness of a new test. You will be given tests that are related to the new test."}
prompt_new = """
This is the new test you are going to evaluate:
```
{}
```
And these are the related tests:
```
{}
```

Does the test `{}` correctly test the behavior of the method? If yes, include <correct> in your answer, and <incorrect> otherwise. Explain why.
"""
max_response_tokens = 250
token_limit = 4096
conversation = []
conversation.append(system_message)
model = SentenceTransformer('all-MiniLM-L6-v2')

def test_path_to_class_name(test_path):
    return test_path[:-5].replace('/', '.')

def class_name_to_test_path(class_name):
    return class_name.replace('.', '/')+'.java'

def cal_cosin_sim(one_evo_dev_df): 
    evo_embedding = model.encode(one_evo_dev_df['evo_test_src'].iloc[0], convert_to_tensor=True)
    one_evo_dev_df['cosin_sim_score'] = one_evo_dev_df['dev_test_src'].apply(lambda x: util.cos_sim(evo_embedding, model.encode(x, convert_to_tensor=True))) 
    return one_evo_dev_df.sort_values(by='cosin_sim_score', ascending = False)

def num_tokens_from_messages(messages):
    encoding= tiktoken.get_encoding("cl100k_base")  #model to encoding mapping https://github.com/openai/tiktoken/blob/main/tiktoken/model.py
    num_tokens = 0
    for message in messages:
        num_tokens += 4  # every message follows <im_start>{role/name}\n{content}<im_end>\n
        for key, value in message.items():
            num_tokens += len(encoding.encode(value))
            if key == "name":  # if there's a name, the role is omitted
                num_tokens += -1  # role is always required and always 1 token
    num_tokens += 2  # every reply is primed with <im_start>assistant
    return num_tokens

def make_prompt_max(one_evo_dev_df):
    related_tests = ''
    conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens # 

    for idx, row in one_evo_dev_df.iterrows():    
        related_tests += row['dev_test_src']
        to_be_appended = [{"role": "user", "content": prompt_new.format(row['evo_test_src'], related_tests, row['evo_test_no'])}]
        if num_tokens_from_messages(to_be_appended) + conv_history_tokens < token_limit:
            if len(conversation) == 2 :
                del conversation[1]
            conversation.append(to_be_appended[0])
            conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens
        else :
            break
    return conversation

def make_prompt_num(one_evo_dev_df, num):
    related_tests = ''
    conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens # 
    count = 0

    for idx, row in one_evo_dev_df.iterrows():   
        if count < num :
            related_tests += row['dev_test_src']
            to_be_appended = [{"role": "user", "content": prompt_new.format(row['evo_test_src'], related_tests, row['evo_test_no'])}]
            if num_tokens_from_messages(to_be_appended) + conv_history_tokens < token_limit:
                if len(conversation) == 2 :
                    del conversation[1]
                conversation.append(to_be_appended[0])
                conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens
                count += 1
            else :
                break
    return conversation

def preprocess(env, evo_tests_df, dev_tests_df):
    """
    Collect EVO generated test's metadata
    """
    for dp, dn, fn in os.walk(env.evosuite_test_src_dir):
        for f in fn:
            if f.endswith("ESTest.java"):
                relpath = os.path.relpath(os.path.join(dp, f), start = env.evosuite_test_src_dir)
                _, parsed_test_src, parsed_target_method = parse_evosuite(os.path.join(dp, f))
                for i in zip(parsed_test_src.items(), parsed_target_method.items()):
                    tmp_df = pd.DataFrame({'dir': [os.path.dirname(relpath)], 'evo_relpath': [relpath], 'evo_test_no':[i[0][0]], 'evo_test_src':[i[0][1]],'evo_target_method':i[1][1]})
                    evo_tests_df = pd.concat([evo_tests_df, tmp_df])
    # """
    # Load Developer written test
    # """
    #get test source directory: dev_test_src_relpath
    dev_test_src_relpath = subprocess.run(
        shlex.split("defects4j export -p dir.src.tests -w {}".format(env.buggy_tmp_dir)),
        universal_newlines=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    dev_test_src_relpath = dev_test_src_relpath.stdout
    # extract developer written test methods
    dev_test_classes = subprocess.run(
        shlex.split("defects4j export -p tests.all -w {}".format(env.buggy_tmp_dir)),
        universal_newlines=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    dev_test_classes_list = dev_test_classes.stdout.split('\n')
    dev_test_src_dir_abspaths = os.path.join(env.buggy_tmp_dir, dev_test_src_relpath)
    for dev_test_class in dev_test_classes_list:
        dev_test_relpath = class_name_to_test_path(dev_test_class)
        dev_test_class_path = os.path.join(dev_test_src_dir_abspaths, dev_test_relpath)
        dev_test_parse_output = env.dev_written_test_analyze + "{}.json".format(dev_test_class)
        if not os.path.exists(dev_test_parse_output):
            subprocess.run(
                shlex.split("java -jar {} {} {}".format(env.java_analyzer, dev_test_class_path, dev_test_parse_output))
            )
        with open(dev_test_parse_output, 'r') as f:
            data = json.load(f)
            for node in data["nodes"]:
                if node["type"] == "method":
                    begin_line = node["begin_line"]
                    end_line = node["end_line"]
                    with open(dev_test_class_path, 'r') as g:
                        line = 0
                        source = ""
                        for l in g:
                            line += 1 
                            if line >= begin_line and line <= end_line:
                                source += l
                        tmp_df = pd.DataFrame({'dir':[os.path.dirname(dev_test_relpath)], 'dev_relpath': [dev_test_relpath], 'dev_method_signature':[node["signature"]], 'dev_test_src':[source]})
                        dev_tests_df = pd.concat([dev_tests_df, tmp_df])
        
    return evo_tests_df, dev_tests_df

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--num','-n', type=int, default= 1)
    args = parser.parse_args()

    project = args.project
    version = args.version
    ts_id = args.id
    example_num = args.num

    env = EvoD4jEnv(project, version, ts_id)
    
    evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_target_method'])
    dev_tests_df = pd.DataFrame(columns=['dir', 'dev_relpath', 'dev_method_signature', 'dev_test_src'])

    evo_tests_df, dev_tests_df = preprocess(env, evo_tests_df, dev_tests_df)
    with open(os.path.join(env.evosuite_test_dir, "evo_test_df.pkl"), 'wb') as fevo:
        pickle.dump(evo_tests_df,fevo)

    evo_dev_join = pd.merge(evo_tests_df, dev_tests_df, on = 'dir')
    for idx, row in evo_tests_df.iterrows():
        one_evo_dev_df = evo_dev_join.loc[(evo_dev_join['evo_relpath'] == row['evo_relpath']) & (evo_dev_join['evo_test_no'] == row['evo_test_no']), :]
        one_evo_dev_df = cal_cosin_sim(one_evo_dev_df)
        prompt = make_prompt_num(one_evo_dev_df, example_num)
        prompt_dir = os.path.join(env.evosuite_prompt_dir, 'example{}'.format(example_num))
        if not os.path.exists(prompt_dir):
            os.mkdir(prompt_dir)
        with open( prompt_dir + '/{}_{}_query.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no']),'wb') as f:
            pickle.dump(prompt,f)
        with open( prompt_dir + '/{}_{}_query.txt'.format(row['dir'].replace('/','.'), row['evo_test_no']),'w') as f:
            f.write(prompt[1]['content'])
 