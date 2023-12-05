import argparse, os

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('info_file', type=str)
    parser.add_argument('metadata_dir', type=str)
    args = parser.parse_args()

    info = open(args.info_file,'r').read()

    relevant_class=info.split(':')[0].strip() 
    relevant_method=info.split(':')[1].strip()

    with open(os.path.join(args.metadata_dir, 'classes.relevant'), 'w') as f:
        f.write(relevant_class)
    
    if not os.path.exists(os.path.join(args.metadata_dir,'methods.relevant')):
        os.mkdir(os.path.join(args.metadata_dir,'methods.relevant'))
    
    with open(os.path.join(args.metadata_dir,'methods.relevant',relevant_class),'w') as f:
        f.write(relevant_method)
    
    with open(os.path.join(args.metadata_dir,'methods.relevant',relevant_class+'.budget'),'w') as f:
        f.write(str(1))



