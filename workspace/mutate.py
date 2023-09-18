import re
import os
import pickle
import argparse
from env import EvoD4jEnv
ASSERT_PATTERNS={
    "assertTrue":r"assertTrue",
    "assertFalse":r"assertFalse",
    "assertNull": r"assertNull",
    "assertNotNull":r"assertNotNull",
    "assertEquals":r"assertEquals",
    "assertNotEquals":r"assertNotEquals"
}

def mut_prompt(conversation):
    prompt_str = conversation[1]['content']
    prompt_list = prompt_str.split('\n')
    mutated = False

    count = 0
    for idx, l in enumerate(prompt_list):
        if l.strip() =='```':
            count += 1 
            if count == 2 :
                break
        for old_pattern, new_pattern in [("assertTrue", "assertFalse"),
                                         ("assertFalse", "assertTrue"),
                                         ("assertNull", "assertNotNull"),
                                         ("assertNotNull", "assertNull"),
                                         ("assertEquals", "assertNotEquals"),
                                         ("assertNotEquals", "assertEquals")]:
            if re.search(old_pattern, l.strip()):
                prompt_list[idx] = re.sub(old_pattern, new_pattern, l)
                mutated = True
                break

    prompt_str = "\n".join(prompt_list)
    conversation[1]['content'] = prompt_str

    return conversation, mutated

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

    for prompt in prompt_list:
        with open(os.path.join(prompt_dir, prompt),'rb') as fr:
            conversation = pickle.load(fr)
        
        converstation_mut, mutated = mut_prompt(conversation)
        if mutated:
            with open(os.path.join(env.evosuit_prompt_mut_dir, prompt.replace('query.pkl', 'query_mut.pkl')), 'wb') as fwp:
                pickle.dump(converstation_mut,fwp)
            with open(os.path.join(env.evosuit_prompt_mut_dir, prompt.replace('query.pkl', 'query_mut.txt')), 'w') as fwt:
                fwt.write(converstation_mut[1]['content'])  