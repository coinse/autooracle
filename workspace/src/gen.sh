#!/bin/bash!bin

param1="Lang"
param3="newTS"
param4=300
param5=0

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    sh gen_test.sh $param1 $i $param3 $param4 $param5
  fi
done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python get_prompt.py $param1 $i -i $param3 -en 2
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python mutate.py $param1 $i -i $param3 -pn 1 -en 2
#   fi
# done


# # 1st try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 1 -en 2 -t 3
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 1 -en 2 -t 2 -m 
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 1 -en 2 -t 2 -m 
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 1 -en 2 -t 3 -m 
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 1 -en 2 -t 3 -m 
#   fi
# done


