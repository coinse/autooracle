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

def query_chat_llm(messages=[], system_message=None, max_response_tokens=500):   
    if system_message is not None:
        messages.append({"role":"system", "content": system_message})    
    chat = client.chat.completions.create(
        model="gpt-4",
        messages=messages,
        max_tokens=max_response_tokens
    )
    chat_reply = chat.choices[0].message.content
    print(chat_reply)
    print('************************************************************************************************************************************')
    # messages.append({"role": "assistant", "content":chat_reply})
    return chat_reply

if __name__ == "__main__":
    text= """
    Provide me the python code using re library that can do the below specification.
    ```
    You have to change all the java assert method to the java assertTrue statement.
    For example, 
    1. assertFalse(a) -> assertTrue( a != false)
    2. assertEquals("\",\"", string0); -> assertTrue(string0.equals(“\”,\””));
    3. assertEquals(5, a); -> assertTrue(5 == a)
    4. assertEquals(1.5, (float b), 0.001); -> assertTrue(Math.abs(1.5 -(b)) <= 0.001);
    5.  assertNull(a) -> assertTrue(a ==null)
    6. assertNotNull(a) -> assertFalse(a != null)
    """

    chat_reply = query_chat_llm([{"role":"user", "content": text}])
    with open('./a.txt', 'w') as f:
        f.write(chat_reply)
