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
    return chat_reply

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--index', '-idx', type=str, default= '1')
    parser.add_argument('--id', '-i', type=str, default='newTS')
    parser.add_argument('--prompt_no', '-pr', type=int, default='1')
    parser.add_argument('--example','-ex', type=int, default= 1)
    parser.add_argument('--try_no','-t', type=int, default= 1)
    parser.add_argument('--transform','-trs', action='store_true')
    args = parser.parse_args()

    project = args.project
    version = args.version
    idx = args.index
    ts_id = args.id
    prompt_no=args.prompt_no
    example = args.example
    try_no=args.try_no
    transform=args.transform
  
    print('*'*30)
    print("Query")
    print(project+'-'+version," ",idx,transform)
    print('*'*30)

    env = EvoD4jEnv(project, version, idx, ts_id)
    
    # Original
    if not transform:
        prompt_dir = os.path.join(env.evosuite_prompt_dir, 'prompt{}/example{}'.format(prompt_no,example))
        prompt_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_dir)))
        
        chat_reply_dir = os.path.join(env.evosuite_chat_reply_dir, 'prompt{}/example{}'.format(prompt_no, example), 'try{}'.format(try_no))
        if not os.path.exists(chat_reply_dir):
            os.makedirs(chat_reply_dir)

        for prompt in prompt_list:
            if prompt.endswith('assert_query.pkl') or prompt.endswith('trycatch_query.pkl'):
                with open(os.path.join(prompt_dir, prompt),'rb') as fr:
                    data = pickle.load(fr)
                    reply_file = os.path.join(chat_reply_dir, prompt.replace('query', 'reply').replace('.pkl', '.txt'))
                if not os.path.exists(reply_file): 
                    chat_reply = query_chat_llm(data)
                    with open(reply_file, 'w') as fw:
                        fw.write(chat_reply)
    # Transform
    else:
        prompt_transform_dir = os.path.join(env.evosuite_prompt_transform_dir, 'prompt{}/example{}'.format(prompt_no,example))
        prompt_transform_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_transform_dir)))

        chat_reply_transform_dir = os.path.join(env.evosuite_chat_reply_transform_dir, 'prompt{}/example{}'.format(prompt_no,example), 'try{}'.format(try_no))
        if not os.path.exists(chat_reply_transform_dir):
            os.makedirs(chat_reply_transform_dir)

        for prompt_tranform in prompt_transform_list:
            if prompt_tranform.endswith('assert_query.pkl') or prompt_tranform.endswith('trycatch_query.pkl'):
                with open(os.path.join(prompt_transform_dir, prompt_tranform),'rb') as fr:
                    data = pickle.load(fr)
                    reply_file = os.path.join(chat_reply_transform_dir, prompt_tranform.replace('query', 'reply').replace('.pkl', '.txt'))
                if not os.path.exists(reply_file):              
                    chat_reply_transform = query_chat_llm(data)
                    with open(reply_file, 'w') as fw:
                        fw.write(chat_reply_transform)

