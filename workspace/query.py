import os
import openai
from dotenv import load_dotenv
import time

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
    # print(f"ChatGPT: {chat_reply}")
    # messages.append({"role": "assistant", "content":chat_reply})
    return chat_reply

def query_single_llm(query):
    message=[]
    response=openai.Completion.create(
        model="text-davinci-003",
        prompt=query,
        temperature=0.7
    )
    print(response)
