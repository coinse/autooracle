#!/bin/bash

param1="Lang"
param3="newTS_600"
param4=600
param5=0

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     echo "/root/workspace/result/Lang-${i}b/generated_test/newTS_600/prompt_mut/prompt8"
#     rm -rf /root/workspace/result/Lang-${i}b/generated_test/newTS_600/prompt_mut/prompt8
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     BUGGY_TMP_DIR=/tmp/$param1-${i}b
#     defects4j checkout -p $param1 -v ${i}b -w $BUGGY_TMP_DIR
#   fi
# done

# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python get_prompt8.py $param1 $i -i $param3 -pn 8 -en 2
#   fi
# done

# for i in {1..65}; do 
#   if [ $i -ne 21 ]; then
#     python transforming.py $param1 $i -i $param3 -pn 8 -en 2
#   fi
# done

################### ###Original###################################################
# # 1st 
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 1
#   fi
# done
# # # 2nd try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 2
#   fi
# done
# # # 3rd try
# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 3
#   fi
# done
#####################Original###################################################


#####################Transforming###################################################
for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 1 -m 
  fi
done

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 2 -m 
  fi
done

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 8 -en 2 -t 3 -m 
  fi
done
#####################Transforming###################################################
