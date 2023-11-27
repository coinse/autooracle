#!/bin/bash
param0="1"
param1="Lang"
param3="newTS_600"
param4=600
param5=0


###1. Generate test and metadata
# for i in {34..65}; do
#   if [ $i -ne 21 ]; then
#     sh gen_test.sh $param1 $i $param3 $param4 $param5
#   fi
# done

# ###2. Make dataframes of evo tests and developer tests
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python dataframe.py $param0 $param1 $i -i $param3 -pr 6 -ex 2
#   fi
# done

# ###3. Make prompt txt 
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python prompt.py $param0 $param1 $i -i $param3 -pr 6 -ex 2
#   fi
# done

# ###4. Query
# ################### ###Original###################################################
# #1st try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 1
#   fi
# done

# # # 2nd try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 2
#   fi
# done

# # # 3rd try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 3
#   fi
# done
# ######################Original###################################################


# ######################Transforming###################################################
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 1 -m
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 2 -m 
#   fi
# done

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param0 $param1 $i -i $param3 -pr 6 -ex 2 -t 3 -m 
  fi
done
#####################Transforming###################################################
