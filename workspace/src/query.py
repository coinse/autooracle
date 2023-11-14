import os
from openai import OpenAI
import argparse
import pickle
from dotenv import load_dotenv
import time  
from utils.env import EvoD4jEnv

load_dotenv()

client = OpenAI(
    organization="org-mSMx268bkMcTa5gXwsDGN8Af", api_key= os.environ["OPEN_API_KEY"])

def query_chat_llm(messages=[], system_message=None, max_response_tokens=250):   
    if system_message is not None:
        messages.append({"role":"system", "content": system_message})    
    chat = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=messages,
        max_tokens=max_response_tokens
    )
    chat_reply = chat.choices[0].message.content
    print(chat_reply)
    print('************************************************************************************************************************************')
    # messages.append({"role": "assistant", "content":chat_reply})
    return chat_reply

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--promptno', '-pn', type=int, default='1')
    parser.add_argument('--examplenum','-en', type=int, default= 1)
    parser.add_argument('--try_no','-t', type=int, default= 1)
    parser.add_argument('--mut','-m', action='store_true', default=False)
    parser.add_argument('--individual','-in', action='store_true', default=False)
    parser.add_argument('--individual_id','-in_id', type=str, default='')
    parser.add_argument('--dir','-d', type=str, default='')
    parser.add_argument('--test_no','-tn', type=str, default='')
    parser.add_argument('--oracle_type','-ot', type=str, default='')
    args = parser.parse_args()

    project=args.project
    version=args.version
    ts_id=args.id
    prompt_no=args.promptno
    example_num=args.examplenum
    try_no=args.try_no
    mut=args.mut
    individual=args.individual
    individual_id=args.individual_id
    dir=args.dir
    test_no=args.test_no
    oracle_type=args.oracle_type

    print('*'*30)
    print("Query")
    print(project+'-'+version," ",mut)
    print('*'*30)

    env = EvoD4jEnv(project, version, ts_id)
    if individual:
        if not mut:
            prompt_dir = os.path.join(env.evosuite_prompt_dir,'prompt{}/example{}'.format(prompt_no,example_num))
            prompt= os.path.join(prompt_dir, dir+'_'+test_no+'_query_'+oracle_type+'.pkl')
            chat_reply_dir = os.path.join('/root/workspace/result','individual',individual_id,'orginal',f'try{try_no}')
            if not os.path.exists(chat_reply_dir):
                os.makedirs(chat_reply_dir)
            with open(os.path.join(prompt_dir, prompt),'rb') as fr:
                data = pickle.load(fr)
                reply_file = os.path.join(chat_reply_dir, f'{project}_{version}_{dir}_{test_no}_reply_{oracle_type}.txt')
                if not os.path.exists(reply_file):              
                    chat_reply = query_chat_llm(data)
                    with open(reply_file, 'w') as fw:
                        fw.write(chat_reply)
        else :
            prompt_dir = os.path.join(env.evosuite_prompt_mut_dir,'prompt{}/example{}'.format(prompt_no,example_num))
            prompt= os.path.join(prompt_dir, dir+'_'+test_no+'_query_mut_'+oracle_type+'.pkl')
            chat_reply_dir = os.path.join('/root/workspace/result','individual',individual_id,'mut',f'try{try_no}')
            if not os.path.exists(chat_reply_dir):
                os.makedirs(chat_reply_dir)
            with open(os.path.join(prompt_dir, prompt),'rb') as fr:
                data = pickle.load(fr)
                reply_file = os.path.join(chat_reply_dir, f'{project}_{version}_{dir}_{test_no}_reply_mut_{oracle_type}.txt')
                if not os.path.exists(reply_file):              
                    chat_reply = query_chat_llm(data)
                    with open(reply_file, 'w') as fw:
                        fw.write(chat_reply)

    else:
        # Original
        if not mut:
            prompt_dir = os.path.join(env.evosuite_prompt_dir, 'prompt{}/example{}'.format(prompt_no,example_num))
            prompt_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_dir)))
            
            chat_reply_dir = os.path.join(env.evosuite_chat_reply_dir, 'prompt{}/example{}'.format(prompt_no, example_num), 'try{}'.format(try_no))
            if not os.path.exists(chat_reply_dir):
                os.makedirs(chat_reply_dir)

            for prompt in prompt_list:
                if prompt.endswith('assert.pkl') or prompt.endswith('trycatch.pkl'):
                    with open(os.path.join(prompt_dir, prompt),'rb') as fr:
                        data = pickle.load(fr)
                        reply_file = os.path.join(chat_reply_dir, prompt.replace('query', 'reply').replace('.pkl', '.txt'))
                    if not os.path.exists(reply_file):              
                        chat_reply = query_chat_llm(data)
                        with open(reply_file, 'w') as fw:
                            fw.write(chat_reply)
        # Mut 
        else:
            prompt_mut_dir = os.path.join(env.evosuite_prompt_mut_dir, 'prompt{}/example{}'.format(prompt_no,example_num))
            prompt_mut_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_mut_dir)))

            chat_reply_mut_dir = os.path.join(env.evosuite_chat_reply_mut_dir, 'prompt{}/example{}'.format(prompt_no,example_num), 'try{}'.format(try_no))
            if not os.path.exists(chat_reply_mut_dir):
                os.makedirs(chat_reply_mut_dir)

            for prompt_mut in prompt_mut_list:
                if prompt_mut.endswith('assert.pkl') or prompt_mut.endswith('trycatch.pkl'):
                    with open(os.path.join(prompt_mut_dir, prompt_mut),'rb') as fr:
                        data = pickle.load(fr)
                        reply_file = os.path.join(chat_reply_mut_dir, prompt_mut.replace('query', 'reply').replace('.pkl', '.txt'))
                    if not os.path.exists(reply_file):              
                        chat_reply_mut = query_chat_llm(data)
                        with open(reply_file, 'w') as fw:
                            fw.write(chat_reply_mut)

