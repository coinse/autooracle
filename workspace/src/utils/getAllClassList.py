from env import EvoD4jEnv
import argparse
import subprocess
import shlex
import os 


def class_path_to_class_name(class_path):
    return class_path.replace('/', '.')

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    args = parser.parse_args()

    project = args.project
    version = args.version
    env = EvoD4jEnv(project, version)

    p = subprocess.run(
        shlex.split(f'defects4j export -p dir.bin.classes -w {env.fixed_tmp_dir}'), stdout = subprocess.PIPE,
        universal_newlines = True
    )

    class_list = []
    for dp, dn, fn in os.walk(os.path.join(env.fixed_tmp_dir, p.stdout)):
        for f in fn:
            if f.endswith('.class'):
                relpath = os.path.relpath(os.path.join(dp,f), start=os.path.join(env.fixed_tmp_dir, p.stdout))
                class_list.append(class_path_to_class_name(relpath)[:-6])
    
    with open(os.path.join(env.metadata_dir, 'all.classes'),'w') as f:
        f.write('\n'.join(class_list))

