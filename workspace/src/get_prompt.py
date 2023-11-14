import os 
import argparse
import json
import tiktoken 
import pickle
from utils.env import EvoD4jEnv
import pandas as pd
from utils.get_javadoc import get_javadoc
from utils.get_invoked_method_body import invoked_method
from utils.get_related_test import get_related
from utils.get_dataframe import get_evo_df, get_dev_tests_df

def make_prompt_file(prompt_dir, prompt, row):
    if not os.path.exists(prompt_dir):
        os.makedirs(prompt_dir)
    if not os.path.exists(prompt_dir + '/{}_{}_query_assert.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no'])):
        if not os.path.exists(prompt_dir + '/{}_{}_query_trycatch.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no'])):
            with open( prompt_dir + '/{}_{}_query.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no']),'wb') as f:
                pickle.dump(prompt,f)
            with open( prompt_dir + '/{}_{}_query.txt'.format(row['dir'].replace('/','.'), row['evo_test_no']),'w') as f:
                f.write(prompt[0]['content'] + '\n' +  prompt[1]['content'])

def num_tokens_from_messages(messages):
    encoding= tiktoken.get_encoding("cl100k_base") 
    num_tokens = 0
    for message in messages:
        num_tokens += 4  # every message follows <im_start>{role/name}\n{content}<im_end>\n
        for key, value in message.items():
            num_tokens += len(encoding.encode(value))
            if key == "name":  # if there's a name, the role is omitted
                num_tokens += -1  # role is always required and always 1 token
    num_tokens += 2  # every reply is primed with <im_start>assistant
    return num_tokens

def make_prompt(env, evo_tests_df, evo_dev_join, conversation, prompt_format):
    
    for _, row in evo_tests_df.iterrows():
        one_evo_dev_df = evo_dev_join.loc[(evo_dev_join['evo_relpath'] == row['evo_relpath']) & (evo_dev_join['evo_test_no'] == row['evo_test_no']), :]
        
        #1. related test 
        one_evo_dev_df = get_related(one_evo_dev_df)
        #2. invoked method
        #invoked_method_body_str = invoked_method(env, row["evo_target_method"])
        #3. java doc
        comment = get_javadoc(env, row["evo_target_method"], row["line"])
        if comment == '':
            continue
        prompt = count_str_num(one_evo_dev_df, example_num, comment, conversation, prompt_format)
        prompt_dir = os.path.join(env.evosuite_prompt_dir, 'prompt5/example{}'.format(example_num))

        make_prompt_file(prompt_dir, prompt, row)  

def count_str_num(one_evo_dev_df, num, comment, conversation, prompt_format):
    related_tests = ''
    conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens #
    count = 0
    for idx, row in one_evo_dev_df.iterrows():   
        if count < num :
            related_tests += row['dev_test_src']
            to_be_appended = [{"role": "user", "content": prompt_format["user_message"].format(row['evo_test_src'], comment, related_tests, row['evo_test_no'])}]
            if num_tokens_from_messages(to_be_appended) + conv_history_tokens < token_limit:
                if len(conversation) == 2 :
                    del conversation[1]
                conversation.append(to_be_appended[0])
                conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens
                count += 1
            else :
                break
    return conversation

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--examplenum','-en', type=int, default= 1)
    args = parser.parse_args()
    
    project = args.project
    version = args.version
    ts_id = args.id
    example_num = args.examplenum

    print('*'*30)
    print("MAKING PROMPT")
    print(project+'-'+version)
    print('*'*30)

    env = EvoD4jEnv(project, version, ts_id)

    # 1. Make two dataframes that contain (evosuite test suite / developer test suite)
    evo_tests_df = get_evo_df(env)
    dev_tests_df = get_dev_tests_df(env)

    # #2. Calculate model embedding
    with open(os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl'),'wb') as f:
        pickle.dump(evo_tests_df,f)

    with open(os.path.join(env.evosuite_test_dir, 'dev_tests_df.pkl'),'wb') as f:
        pickle.dump(dev_tests_df,f)

    with open(os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl'),'rb') as f:
        evo_tests_df = pickle.load(f)

    with open(os.path.join(env.evosuite_test_dir,'dev_tests_df.pkl'),'rb') as f:
        dev_tests_df = pickle.load(f)
     
    #3. Merge to the two dataset on directroy
    evo_dev_join = pd.merge(evo_tests_df, dev_tests_df, on = 'dir', how = 'left')

    #4. Initialize conversation 
    max_response_tokens = 250
    token_limit = 4000
    conversation = []
    with open(os.path.join(env.src_dir, 'prompt', "prompt5.json"),'rb') as f:
        prompt_format = json.load(f)
    conversation.append(prompt_format["system_message"])
    
    # 4. Make prompt
    make_prompt(env, evo_tests_df, evo_dev_join, conversation, prompt_format)