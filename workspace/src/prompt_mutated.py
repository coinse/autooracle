import os 
import re
import argparse
import json
import tiktoken 
import pickle
from utils.env import EvoD4jEnv
import pandas as pd
from utils.javadoc import get_javadoc
from utils.relatedTest import get_related

def make_prompt_file(conversation, row, mutated= False):
    if mutated:
        prompt_dir = os.path.join(env.evosuite_prompt_mut_dir, f'prompt{prompt_no}/example{example}')
    else:
        prompt_dir = os.path.join(env.evosuite_prompt_dir, f'prompt{prompt_no}/example{example}')

    if not os.path.exists(prompt_dir):
        os.makedirs(prompt_dir)
    
    with open( prompt_dir + '/{}_{}_{}_query.pkl'.format(row['dir'].replace('/','.'), row['evo_test_no'], row['mutated']),'wb') as f:
        pickle.dump(conversation,f)
    with open( prompt_dir + '/{}_{}_{}_query.txt'.format(row['dir'].replace('/','.'), row['evo_test_no'], row['mutated']),'w') as f:
        f.write(conversation[0]['content'] + '\n' +  conversation[1]['content'])

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

def make_prompt():
    with open(os.path.join(env.src_dir, 'prompt', f"prompt{prompt_no}.json"),'rb') as f:
        prompt_format = json.load(f)

    for _, row in evo_tests_df.iterrows():
        if row['mutated'] == 'No':
            continue

        conversation = []
        conversation.append(prompt_format["system_message"])

        one_evo_dev_df = evo_dev_join.loc[(evo_dev_join['evo_relpath'] == row['evo_relpath']) & (evo_dev_join['evo_test_no'] == row['evo_test_no']), :]
        #1. related test 
        one_evo_dev_df = get_related(one_evo_dev_df)
        if one_evo_dev_df['dev_test_src'].reset_index(drop=True)[0] == 'no related test':
            continue
        #2. java doc
        comment = get_javadoc(env, row["evo_target_method"], row["line"])
        if comment == '':
            continue
        elif re.search("non-Javadoc", comment):
            continue
        elif not(re.search("@param", comment) and re.search("@return", comment)):
            continue
        
        related_tests = ''
        for i in list(range(example)):   
            related_tests += one_evo_dev_df.iloc[i]['dev_test_src']

        # Original        
        to_be_appended = [{"role": "user", "content": prompt_format["user_message"].format(comment, related_tests, row['evo_test_src'], row['evo_test_no'])}]
        conversation.append(to_be_appended[0])
        
        total_tokens = num_tokens_from_messages(conversation) + max_response_tokens
        if total_tokens > token_limit:
            continue
        make_prompt_file(conversation, row)  

        # Trasnformed
        del conversation[-1]
        to_be_appended = [{"role": "user", "content": prompt_format["user_message"].format(comment, related_tests, row['evo_test_src_mut'], row['evo_test_no'])}]
        conversation.append(to_be_appended[0])
        
        total_tokens = num_tokens_from_messages(conversation) + max_response_tokens
        if total_tokens > token_limit:
            continue
        make_prompt_file(conversation, row, True) 


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('name', type=str)
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--prompt_no', '-pr', type=int, default='1')
    parser.add_argument('--example','-ex', type=int, default= 1)
    args = parser.parse_args()
    
    name = args.name
    project = args.project
    version = args.version
    ts_id = args.id
    prompt_no=args.prompt_no
    example = args.example

    print('*'*30)
    print("MAKING PROMPT")
    print(project+'-'+version)
    print('*'*30)

    env = EvoD4jEnv(name, project, version, ts_id)

    with open(os.path.join(env.evosuite_test_dir,'evo_tests_df.pkl'),'rb') as f:
        evo_tests_df = pickle.load(f)

    with open(os.path.join(env.evosuite_test_dir,'dev_tests_df.pkl'),'rb') as f:
        dev_tests_df = pickle.load(f)
     
    #1. Merge to the two dataset on directroy
    evo_dev_join = pd.merge(evo_tests_df, dev_tests_df, on = 'dir', how = 'left')

    #2. conversation Setting  
    max_response_tokens = 250
    token_limit = 4096

    #3. Make prompt
    make_prompt()