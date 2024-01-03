import pickle
import pandas as pd

with open('/root/workspace/result/Lang-1f_mutated/0/generated_test/newTS_300/evo_tests_df.pkl','rb') as f:
    data = pickle.load(f)

df = pd.DataFrame(object)
df.to_csv(r'file.csv')