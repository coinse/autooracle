from sentence_transformers import SentenceTransformer, util
import pandas as pd 
import numpy

model = SentenceTransformer('all-MiniLM-L6-v2')

def cal_cosin_sim(one_evo_dev_df): 
    one_evo_dev_df['cosin_sim_score'] = one_evo_dev_df['dev_test_embedding'].apply(lambda x: util.cos_sim(one_evo_dev_df['evo_test_embedding'].iloc[0], x)) 
    return one_evo_dev_df.sort_values(by='cosin_sim_score', ascending = False)
