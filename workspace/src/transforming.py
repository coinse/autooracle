import re
import os
import pickle
import argparse
from utils.env import EvoD4jEnv

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
                                         ("assertNotEquals", "assertEquals"),
                                         ("assertNotSame", "assertSame"),
                                         ("assertSame", "assertNotSame")]:
            if re.search(old_pattern, l.strip()):
                prompt_list[idx] = re.sub(old_pattern, new_pattern, l)
                mutated = True
                break

    prompt_str = "\n".join(prompt_list)
    '''
    This is for dealing with the unit test that has a tyr-catch type 
    '''
    # Regular expression pattern to find the fail statement outside the catch block
    pattern2 = r'try {([\s\S]*?)fail\("[^"]+"\);([\s\S]*?)} catch\(([^)]+)\) {([\s\S]*?)}'

    # Find and replace the fail statement
    if re.search(pattern2, prompt_str):
        prompt_str = re.sub(pattern2, r'try {\1} catch(\3) {\2\tfail("Expecting No exception");}', prompt_str, count=1)
        mutated = True
    conversation[1]['content'] = prompt_str

    return conversation, mutated

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--promptno', '-pn', type=int, default='1')
    parser.add_argument('--examplenum','-en', type=int, default= 1)
    args = parser.parse_args()

    project = args.project
    version = args.version
    ts_id = args.id
    prompt_no = args.promptno
    example_num = args.examplenum

    env = EvoD4jEnv(project, version, ts_id)
    prompt_dir = os.path.join(env.evosuite_prompt_dir, 'prompt{}/example{}'.format(prompt_no,example_num))
    prompt_list = list(filter(lambda x: x.endswith('.pkl'), os.listdir(prompt_dir)))

    prompt_mut_dir = os.path.join(env.evosuite_prompt_mut_dir, 'prompt{}/example{}'.format(prompt_no, example_num))
    if not os.path.exists(prompt_mut_dir):
            os.makedirs(prompt_mut_dir)

    for prompt in prompt_list:
        with open(os.path.join(prompt_dir, prompt),'rb') as fr:
            conversation = pickle.load(fr)
        
        converstation_mut, mutated = mut_prompt(conversation)
        if mutated:
            with open(os.path.join(prompt_mut_dir, prompt.replace('query.pkl', 'query_mut.pkl')), 'wb') as fwp:
                pickle.dump(converstation_mut,fwp)
            with open(os.path.join(prompt_mut_dir, prompt.replace('query.pkl', 'query_mut.txt')), 'w') as fwt:
                fwt.write(converstation_mut[0]['content'] + converstation_mut[1]['content'])