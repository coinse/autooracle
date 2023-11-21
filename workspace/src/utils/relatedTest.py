from sentence_transformers import SentenceTransformer, util
import pandas as pd 

model = SentenceTransformer('all-MiniLM-L6-v2')

def cal_evo_embedding(env, evo_tests_df):
    evo_tests_df['evo_embedding'] = evo_tests_df['evo_test_src'].apply(lambda x: model.encode(x))
    return evo_tests_df

def cal_dev_embedding(env, dev_tests_df):
    dev_tests_df['dev_embedding'] = dev_tests_df['dev_test_src'].apply(lambda x: model.encode(x))
    return dev_tests_df

def get_related(one_evo_dev_df): 
    evo_embedding = one_evo_dev_df['evo_embedding'].reset_index(drop=True)[0]
    if one_evo_dev_df['dev_embedding'].isna().sum():
        one_evo_dev_df['dev_test_src'] = 'no related test'
        return one_evo_dev_df
    else :
        one_evo_dev_df['cosin_sim_score'] = one_evo_dev_df['dev_embedding'].apply(lambda x: util.cos_sim(evo_embedding, x)) 
        return one_evo_dev_df.sort_values(by='cosin_sim_score', ascending = False)
