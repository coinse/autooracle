import os 
import argparse
import json
import tiktoken 
import pickle
from utils.env import EvoD4jEnv
import pandas as pd
from utils.invoked_method_body import invoked_method
from utils.similarity import cal_cosin_sim
from utils.make_dataframe import preprocess


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
        print('*'*30)
        print('cal_cosin_sim')
        print('*'*30)
        #one_evo_dev_df = cal_cosin_sim(one_evo_dev_df)
        
        #FIXME
        #Give invoked method body to the function 'make_prompt_str_num'
        invoked_method_body_str = invoked_method(env, row["evo_target_method"])

        prompt = make_prompt_str_num(one_evo_dev_df, example_num, invoked_method_body_str, conversation, prompt_format)
        prompt_dir = os.path.join(env.evosuite_prompt_dir, 'prompt1/example{}'.format(example_num))
        if not os.path.exists(prompt_dir):
            os.makedirs(prompt_dir)
        with open( prompt_dir + '/{}_{}_query.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no']),'wb') as f:
            pickle.dump(prompt,f)
        with open( prompt_dir + '/{}_{}_query.txt'.format(row['dir'].replace('/','.'), row['evo_test_no']),'w') as f:
            f.write(prompt[0]['content'] + '\n' +  prompt[1]['content'])

def make_prompt_str_num(one_evo_dev_df, num, invoked_method_body_str, conversation, prompt_format):
    related_tests = ''
    conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens # 
    count = 0
    for idx, row in one_evo_dev_df.iterrows():   
        if count < num :
            related_tests += row['dev_test_src']
            to_be_appended = [{"role": "user", "content": prompt_format["user_message"].format(row['evo_test_src'], invoked_method_body_str, related_tests, row['evo_test_no'])}]
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
    print(project+'-'+version)
    print('*'*30)

    env = EvoD4jEnv(project, version, ts_id)
    
    # 1. Initialize 
    evo_tests_df = pd.DataFrame(columns=['dir', 'evo_relpath', 'evo_test_no', 'evo_test_src', 'evo_target_method', 'evo_test_embedding'])
    dev_tests_df = pd.DataFrame(columns=['dir', 'dev_relpath', 'dev_method_signature', 'dev_test_src', 'dev_test_embedding'])

    # 2. Make two dataframes that contain (evosuite test suite / developer test suite)
    evo_tests_df, dev_tests_df = preprocess(env, evo_tests_df, dev_tests_df)

    # 3. Merge to the two dataset on directroy
    evo_dev_join = pd.merge(evo_tests_df, dev_tests_df, on = 'dir')

    max_response_tokens = 250
    token_limit = 4096
    conversation = []
    with open(os.path.join(env.src_dir, 'prompt', "prompt1.json"),'rb') as f:
        prompt_format = json.load(f)
    conversation.append(prompt_format["system_message"])
    
    # 4. Make prompt
    make_prompt(env, evo_tests_df, evo_dev_join, conversation, prompt_format)




# def make_prompt_str_max(one_evo_dev_df):
#     related_tests = ''
#     conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens # 

#     for idx, row in one_evo_dev_df.iterrows():    
#         related_tests += row['dev_test_src']
#         to_be_appended = [{"role": "user", "content": prompt_new.format(row['evo_test_src'], row['target_method_src'], related_tests, row['evo_test_no'])}]
#         if num_tokens_from_messages(to_be_appended) + conv_history_tokens < token_limit:
#             if len(conversation) == 2 :
#                 del conversation[1]
#             conversation.append(to_be_appended[0])
#             conv_history_tokens = num_tokens_from_messages(conversation) + max_response_tokens
#         else :
#             break
#     return conversation