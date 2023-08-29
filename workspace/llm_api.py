### 
# step 1 (complete)
# openai query basic code 
# 1) chat
# 2) completion
# step 2 (don't need)
# openai query function call code
# step 3
# query with 
# 1) test by test 
# 2) invoked method
# 3) failing test infomation (erro message, etc)
import os
import openai
from dotenv import load_dotenv

load_dotenv()
openai.organization="org-mSMx268bkMcTa5gXwsDGN8Af"
openai.api_key= os.environ["OPEN_API_KEY"]

def query_chat_llm(user_query):
    messages=[]
    while True:
        if user_query == "quit":
            return
        else :
            messages.append({"role":"user", "content":user_query})
            chat = openai.ChatCompletion.create(
                model="gpt-4",
                messages=messages
            )
        chat_reply = chat["choices"][0]["message"]["content"]
        print(f"ChatGPT: {chat_reply}")
        messages.append({"role": "assistant", "content":chat_reply})

def query_single_llm(query):
    message=[]
    response=openai.Completion.create(
        model="text-davinci-003",
        prompt=query,
        temperature=0.7
    )
    print(response)
