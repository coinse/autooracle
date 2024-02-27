import sys
sys.path.append('../')
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

def query_chat_llm(messages=[], max_response_tokens=1300):   
    chat = client.chat.completions.create(
        model="gpt-3.5-turbo-0613",
        messages=messages,
        max_tokens=max_response_tokens
    )
    return chat.choices[0].message.content

def process_prompt(prompt_dir, chat_reply_dir, test_no):
    if not os.path.exists(chat_reply_dir):
        os.makedirs(chat_reply_dir)
    for prompt_file in os.listdir(prompt_dir):
        if test_no != 'none' and prompt_file.split('_')[1] != test_no:
            continue 
        if prompt_file.endswith('.pkl'):
            with open(os.path.join(prompt_dir, prompt_file), 'rb') as fr:
                data = pickle.load(fr)
            reply_file = os.path.join(chat_reply_dir, prompt_file.replace('query', 'reply').replace('.pkl', '.txt'))
            if not os.path.exists(reply_file): 
                chat_reply = query_chat_llm(data)
                with open(reply_file, 'w') as fw:
                    fw.write(chat_reply)

def process_prompt_conversation(prompt_dir, chat_reply_dir, test_no):
    if not os.path.exists(chat_reply_dir):
        os.makedirs(chat_reply_dir)
    for prompt_file in os.listdir(prompt_dir):
        if test_no != 'none' and prompt_file.split('_')[1] != test_no:
            continue 
        if prompt_file.endswith('.pkl'):
            with open(os.path.join(prompt_dir, prompt_file),'rb') as fr:
                data = pickle.load(fr)
            reply_file_1 = os.path.join(chat_reply_dir, prompt_file.replace('query', 'reply').replace('.pkl', '.txt')+"_1")
            reply_file_2 = os.path.join(chat_reply_dir, prompt_file.replace('query', 'reply').replace('.pkl', '.txt')+"_2")
            initial_prompt = data[0:2]
            if not os.path.exists(reply_file_1):
                print("*******Initial Query*******")
                chat_reply = query_chat_llm(initial_prompt)
                with open(reply_file_1, 'w') as fw:
                    fw.write(chat_reply)
            if os.path.exists(reply_file_1):
                chat_reply = open(reply_file_1).read()
                initial_answer = [{"role": "assistant", "content": chat_reply}]
            second_prompt = initial_prompt + initial_answer + [data[2]]
            if not os.path.exists(reply_file_2):
                print("*******Second Query*******")
                chat_reply = query_chat_llm(second_prompt)
                with open(reply_file_2, 'w') as fw:
                    fw.write(chat_reply)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--index', '-idx', type=str, default='1')
    parser.add_argument('--id', '-i', type=str, default='newTS')
    parser.add_argument('--prompt_no', '-pr', type=int, default=1)
    parser.add_argument('--example', '-ex', type=int, default=1)
    parser.add_argument('--try_no', '-t', type=int, default=1)
    parser.add_argument('--transform', '-trs', action='store_true')
    parser.add_argument('--test_no', '-tno', type=str, default='none')
    parser.add_argument('--conversational', '-conv', action='store_true')
    args = parser.parse_args()

    project = args.project
    version = args.version
    idx = args.index
    ts_id = args.id
    prompt_no = args.prompt_no
    example = args.example
    try_no = args.try_no
    transform = args.transform
    test_no = args.test_no
    is_conversational = args.conversational

    print('*'*30)
    print("Query")
    print(f"{project}-{version} idx:{idx} try_no:{try_no} test_no:{test_no} {transform}")
    print('*'*30)

    env = EvoD4jEnv(project, version, idx, ts_id)
    
    if not transform:
        prompt_dir = os.path.join(env.evosuite_prompt_dir, f'prompt{prompt_no}/example{example}')
        chat_reply_dir = os.path.join(env.evosuite_chat_reply_dir, f'prompt{prompt_no}/example{example}/try{try_no}')
        if is_conversational:
            process_prompt_conversation(prompt_dir, chat_reply_dir, test_no)
        else:
            process_prompt(prompt_dir, chat_reply_dir, test_no)
    else:
        prompt_transform_dir = os.path.join(env.evosuite_prompt_transform_dir, f'prompt{prompt_no}/example{example}')
        chat_reply_transform_dir = os.path.join(env.evosuite_chat_reply_transform_dir, f'prompt{prompt_no}/example{example}/try{try_no}')
        if is_conversational:
            process_prompt_conversation(prompt_transform_dir, chat_reply_transform_dir, test_no)
        else:
            process_prompt(prompt_transform_dir, chat_reply_transform_dir, test_no)