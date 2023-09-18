import os
import openai
import argparse
import pickle
from dotenv import load_dotenv
import time  
from env import EvoD4jEnv

load_dotenv()
openai.organization="org-mSMx268bkMcTa5gXwsDGN8Af"
openai.api_key= os.environ["OPEN_API_KEY"]

def query_chat_llm(messages=[], system_message=None, max_response_tokens=250):   
  
    if system_message is not None:
        messages.append({"role":"system", "content": system_message})    
    chat = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=messages,
        max_tokens=max_response_tokens
    )
    
    chat_reply = chat["choices"][0]["message"]["content"]
    # messages.append({"role": "assistant", "content":chat_reply})
    return chat_reply



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
    prompt_dir = env.evosuit_prompt_dir
    prompt_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_dir)))

    chat_reply_dir = env.evosuit_chat_reply_dir

    for prompt in prompt_list:
        with open(os.path.join(prompt_dir, prompt),'rb') as fr:
            data = pickle.load(fr)
        chat_reply = query_chat_llm(data)
        with open(os.path.join(chat_reply_dir, prompt.replace('query.pkl', 'reply')), 'w') as fw:
            fw.write(chat_reply)
        break
