import subprocess

def convert_local_path_docker(path):
    path  = path.replace("/home/coinse/Workspace/hslee_workspace/autooracle/workspace","/root/workspace")
    return path

if __name__ == "__main__":

    with open('to_delete.txt','r') as f:
        delete_list = f.readlines()

    for file_path in delete_list:
        path = convert_local_path_docker(file_path.strip())
        subprocess.run(["rm", f'{path}'])
    