import sys
sys.path.append('../')
import re
import os 
import argparse
import json
import pickle
from utils.env import EvoD4jEnv
# from utils.relatedTest import get_related, cal_evo_embedding, cal_dev_embedding
import pandas as pd
import tiktoken 

assetion_patterns = {
        'assertTrue': 'assertFalse',
        'assertFalse': 'assertTrue',
        'assertNull':'assertNotNull',
        'assertNotNull': 'assertNull',
        'assertEquals': 'assertNotEquals',
        'assertNotEquals': 'assertEquals',
        'assertSame': 'assertNotSame',
        'assertNotSame': 'assertSame'
}

TOKEN_LIMIT = 4096

def print_separator():
    print('*'*30)

def num_tokens_from_messages(messages):
    encoding= tiktoken.get_encoding("cl100k_base") 
    num_tokens = 0
    for message in messages:
        num_tokens += 4  # every message follows <im_start>{role/name}\n{content}<im_end>\n
        for key, value in message.items():
            num_tokens += len(encoding.encode(value))
            if key == "name":  # if there's a name, the role is omitted
                num_tokens += -1  # role is always required and always 1 token
    num_tokens += 3  # every reply is primed with <im_start>assistant
    return num_tokens

def save_prompt_file(conversation, evo_test, transformed = False):
    prompt_dir = os.path.join(env.evosuite_prompt_transform_dir if transformed else env.evosuite_prompt_dir,
                              f'prompt{prompt_no}/example{example}')
    if not os.path.exists(prompt_dir):
        os.makedirs(prompt_dir)
    prompt_pkl = os.path.join(prompt_dir, f'{evo_test["dir"].replace("/", ".")}_{evo_test["evo_test_no"]}_{evo_test["transformed"]}_query.pkl')
    prompt_txt = prompt_pkl.replace('pkl','txt')
    with open(prompt_pkl,'wb') as f:
        pickle.dump(conversation,f)
    if is_conversational:
        with open(prompt_txt, 'w') as f:
            f.write(f"*Initial prompt\n{conversation[0]['content']}\n{conversation[1]['content']}\n\n*Second prompt\n {conversation[2]['content']}")
    else:
        with open(prompt_txt, 'w') as f:
            f.write(f"{conversation[0]['content']}\n{conversation[1]['content']}")

def get_prompt_format():
    with open(os.path.join(env.src_dir, 'prompt', 'format', f"prompt{prompt_no}.json"),'rb') as f:
        prompt_format = json.load(f)
    return prompt_format

def get_prompt_foramt_1():
    with open(os.path.join(env.src_dir, 'prompt', 'format', f'prompt{prompt_no}', f"prompt{prompt_no}_1.json"),'rb') as f:
        prompt_format = json.load(f)
    return prompt_format

def get_prompt_foramt_2():
    with open(os.path.join(env.src_dir, 'prompt', 'format', f'prompt{prompt_no}',f"prompt{prompt_no}_2.json"),'rb') as f:
        prompt_format = json.load(f)
    return prompt_format

# def return_related_dev_tests(evo_test):
#     evo_dev_join = pd.merge(evo_tests_df, dev_tests_df, on = 'dir', how = 'left')
#     one_evo_dev_df = evo_dev_join.loc[(evo_dev_join['evo_relpath'] == evo_test['evo_relpath'])
#                                        & (evo_dev_join['evo_test_no'] == evo_test['evo_test_no']), :]
#     one_evo_dev_df = get_related(one_evo_dev_df)
#     related_tests = ''
#     if one_evo_dev_df['dev_test_src'].reset_index(drop=True)[0] == 'no related test':
#         print('no related test:',evo_test['evo_test_no'])
#         return related_tests
#     related_tests = ''.join(one_evo_dev_df.iloc[i]['dev_test_src'] for i in range(example))
#     return related_tests

def extract_method_name(input_str):
    parts = input_str.split('.')  
    for part in parts:
        if '(' in part:
            inside_parentheses = part.split('(')[0]
            return inside_parentheses
    return None

def get_main_line(evo_test):
    method_name = extract_method_name(evo_test['signature'])
    print(method_name)
    if method_name == None:
        return None, None
    main_input = ''
    is_valid = False
    for line in evo_test['evo_test_src'].split('\n'):
        if line.find(method_name) != -1:
            tmp_main_input = line.strip()
            is_valid = True
            break
    if is_valid:
        parts = tmp_main_input.split()  
        idx = 0
        for i, word in enumerate(parts):
            if word.find(method_name) != -1:
                idx = i
                break
        main_input = ' '.join(parts[idx:])
        main_method = ''
        if '(' in main_input:
            main_method = main_input.split('(')[0]
        return main_input, main_method
    else:
        return None, None

def get_user_message(prompt_format, evo_test, transformed):
    main_input, main_method= get_main_line(evo_test)
    if main_input == None and main_method == None:
        return None
    oracle_type = assetion_patterns[evo_test["transformed"]] if transformed and evo_test["transformed"] != 'try_catch' else  evo_test["transformed"]
    user_message = prompt_format["user_message"].format(evo_test['comment'], evo_test['evo_test_src_trs'] if transformed else evo_test['evo_test_src'],main_method, main_method, main_input, oracle_type)
    return user_message 

def get_user_message_conversation(prompt_1, prompt_2, evo_test, transformed):
    main_input, main_method= get_main_line(evo_test)
    if main_input == None and main_method == None:
        return None
    oracle_type = assetion_patterns[evo_test["transformed"]] if transformed and evo_test["transformed"] != 'try_catch' else  evo_test["transformed"]
    user_message_1= prompt_1["user_message"].format(evo_test['comment'], main_method, main_method)
    user_message_2= prompt_2["user_message"].format(evo_test['evo_test_src_trs'] if transformed else evo_test['evo_test_src'], main_input, oracle_type)
    return user_message_1, user_message_2 

def make_prompt():
    MAX_RESPONSE_TOKENS = 1000
    prompt_format = get_prompt_format()
    for _, evo_test in evo_tests_df.iterrows():
        if evo_test['transformed'] == 'No':
            continue
        # Bad comment was filtered at "dataframe.py"
        conversation = [prompt_format["system_message"]]
        for transformed in [False, True]:
            user_message = get_user_message(prompt_format, evo_test, transformed)
            if user_message == None:
                break
            to_be_appended = [{"role": "user", "content" :user_message}]
            conversation.append(to_be_appended[0])
            total_tokens = num_tokens_from_messages(conversation) + MAX_RESPONSE_TOKENS
            print(total_tokens)
            if total_tokens <= TOKEN_LIMIT:
                save_prompt_file(conversation, evo_test, transformed)
                pass
            del conversation[-1]

def make_prompt_conversation():
    MAX_RESPONSE_TOKENS = 700
    prompt_1 = get_prompt_foramt_1()
    prompt_2 = get_prompt_foramt_2()
    for _, evo_test in evo_tests_df.iterrows():
        if evo_test['transformed'] == 'No':
            continue
        # Bad comment was filtered at "dataframe.py"
        conversation = [prompt_1["system_message"]]
        for transformed in [False, True]:
            user_message_1, user_message_2 = get_user_message_conversation(prompt_1, prompt_2, evo_test, transformed)
            if user_message_1 == None or user_message_2 == None:
                break
            to_be_appended = [{"role": "user", "content":user_message_1}, {"role": "user", "content":user_message_2}]
            conversation.append(to_be_appended[0])
            conversation.append(to_be_appended[1])
            total_tokens = num_tokens_from_messages(conversation) + 2 * MAX_RESPONSE_TOKENS
            print(total_tokens)
            if total_tokens <= TOKEN_LIMIT:
                save_prompt_file(conversation, evo_test, transformed)
                pass
            del conversation[-1]
            del conversation[-1]

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--index', '-idx', type=str, default= '1')
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--prompt_no', '-pr', type=int, default=1)
    parser.add_argument('--example','-ex', type=int, default= 0)
    parser.add_argument('--conversational','-conv', action='store_true')
    args = parser.parse_args()
    
    project = args.project
    version = args.version
    idx = args.index
    ts_id = args.id
    prompt_no=args.prompt_no
    example = args.example
    is_conversational = args.conversational

    print_separator()
    print("MAKING PROMPT")
    print(project+'-'+version, idx)
    print_separator()

    env = EvoD4jEnv(project, version, idx, ts_id)
    evo_tests_df_path = os.path.join(env.evosuite_test_dir,'evo_tests_df_new.pkl')
    with open(evo_tests_df_path,'rb') as f:
        evo_tests_df = pickle.load(f)
    dev_test_df_path = env.dev_tests_df_path
    with open(dev_test_df_path,'rb') as f:
       dev_tests_df = pickle.load(f)

    # 1. Related test preprocessing
    # evo_tests_df = cal_evo_embedding(env, evo_tests_df)
    # dev_tests_df = cal_dev_embedding(env, dev_tests_df)
    if is_conversational:
        make_prompt_conversation()
    else:
        make_prompt()