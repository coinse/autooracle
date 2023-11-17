import os
import re
import shutil
from utils.env import EvoD4jEnv

def get_prompt_list(project, version, ts_id, prompt_no, example_no):
    env = EvoD4jEnv(project, version, ts_id)
    prompt_path = os.path.join(env.evosuite_prompt_dir,f'prompt{prompt_no}',f'example{example_no}')
    prompt_files = os.listdir(prompt_path)
    prompt_files = list(filter(lambda f: f.endswith("assert.txt") or f.endswith("trycatch.txt"), prompt_files))
    
    for prompt_file in prompt_files:
        prompt = os.path.join(prompt_path, prompt_file)
        with open(prompt,'r') as f:
            data = f.readlines()
        # 1. newtest 
        count = 0
        test_str = ''
        doc_str = ''
        relateTest_str =''
        for l in data:
            if l.strip() == "```":
                count +=1
                continue
            if count == 1:
                doc_str += l
            elif count == 3:
                relateTest_str += l
            elif count == 5:
                test_str+= l
        okey = True
        

        if not ( re.search("@param", doc_str) and re.search("@return", doc_str)):
            okey = False
            
        if re.search("non-Javadoc", doc_str):
            okey = False
        if re.search("no related test", relateTest_str):
            okey = False
        
        if okey:
            #copy promp
            print(okey)
            dst_prompt_path =os.path.join(env.selected_evosuite_prompt_dir, f'prompt{prompt_no}',f'example{example_no}')
            if not os.path.exists(dst_prompt_path):
                os.makedirs(dst_prompt_path)
            shutil.copyfile(prompt, dst_prompt_path + f'/{prompt_file}')

            #copy mut_prompt
            src_prompt_mut_path = os.path.join(env.evosuite_prompt_mut_dir, f'prompt{prompt_no}',f'example{example_no}')
            dst_prompt_mut_path = os.path.join(env.selected_evosuite_prompt_mut_dir, f'prompt{prompt_no}',f'example{example_no}')
            prompt_mut_file = prompt_file.replace("query_","query_mut_")
            if not os.path.exists(dst_prompt_mut_path):
                os.makedirs(dst_prompt_mut_path)
            shutil.copyfile(src_prompt_mut_path+f'/{prompt_mut_file}', dst_prompt_mut_path+f'/{prompt_mut_file}')
            
            #copy reply
            src_reply_path = os.path.join(env.evosuite_chat_reply_dir, f'prompt{prompt_no}',f'example{example_no}')
            dst_reply_path = os.path.join(env.selected_evosuite_chat_reply_dir, f'prompt{prompt_no}',f'example{example_no}')
            reply_file = prompt_file.replace("_query_","_reply_")
            for i in range(1,4):
                if not os.path.exists(dst_reply_path + f'/try{i}'):
                    os.makedirs(dst_reply_path + f'/try{i}')
                shutil.copyfile(src_reply_path+f'/try{i}'+f'/{reply_file}', dst_reply_path + f'/try{i}'+f'/{reply_file}')

            #copy mut_reply
            src_reply_path = os.path.join(env.evosuite_chat_reply_mut_dir, f'prompt{prompt_no}',f'example{example_no}')
            dst_reply_path = os.path.join(env.selected_evosuite_chat_reply_mut_dir, f'prompt{prompt_no}',f'example{example_no}')
            reply_file = prompt_file.replace("_query_","_reply_mut_")
            for i in range(1,4):
                if not os.path.exists(dst_reply_path + f'/try{i}'):
                    os.makedirs(dst_reply_path + f'/try{i}')
                shutil.copyfile(src_reply_path+f'/try{i}'+f'/{reply_file}', dst_reply_path + f'/try{i}'+f'/{reply_file}')




if __name__ == "__main__":


    #1. get prompt list
    # get_prompt_list("Lang","1","newTS_600", 5, 2)

    for project in ["Lang", "Time"]:
        if project == "Lang":
            versions = list(range(1,2)) + list(range(3,4)) + list(range(5,19)) + list(range(20,26)) + list(range(27,28)) + list(range(29,43)) + list(range(44,48)) + list(range(49,55))+list(range(56,66))
        elif project == "Chart":
            versions = list(range(1,27))
        elif project == "Time":
            versions = list(range(1,21)) + list(range(22,28))
        
        
        for version in versions:
            get_prompt_list(project, str(version), "newTS_600", 8, 2)


    

    