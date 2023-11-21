from utils.env import EvoD4jEnv
import argparse
import os

str = "94:ROR:>(int,int):!=(int,int):org.apache.commons.lang3.math.NumberUtils@createNumber(java.lang.String):477:firstSigDigit > '7' |==> firstSigDigit != '7'"


def parse(str):
    spliteed_line = str.split(":")

    mutants_log = {}
    mutants_log["mutant_no"] = spliteed_line[0]
    mutants_log["mutant_operator"] = spliteed_line[1]
    mutants_log["before_syntax"] = spliteed_line[2]
    mutants_log["after_syntax"] = spliteed_line[3]
    mutants_log["target"] = spliteed_line[4]
    mutants_log["line"] = spliteed_line[5]
    mutants_log["before_after"] = spliteed_line[6]

    return mutants_log
    


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('name', type=str)
    parser.add_argument('project', type=str)
    parser.add_argument('version', type=str)
    parser.add_argument('--id', '-i', type=str, default='1')
    parser.add_argument('--prompt_no', '-pr', type=int, default='1')
    parser.add_argument('--example','-ex', type=int, default= 1)

    args = parser.parse_args()
    name = args.name
    project = args.project
    version = args.version
    ts_id = args.id
    prompt_no=args.prompt_no
    example = args.example
    mutants_log = parse(str)

    env = EvoD4jEnv(name, project, version, ts_id)

    relevant_classes = os.listdir(env.mutation_metadata_dir)

    print(relevant_classes)


