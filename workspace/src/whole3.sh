#!/bin/bash

param1="Time"
param3="newTS_600"
param4=600
param5=0

# for i in {1..27}; do
#   if [ $i -ne 211 ]; then
#     sh gen_test.sh $param1 $i $param3 $param4 $param5
#   fi
# done

# for i in {1..27}; do
#   if [ $i -ne 211 ]; then
#     BUGGY_TMP_DIR=/tmp/$param1-${i}b
#     defects4j checkout -p $param1 -v ${i}b -w $BUGGY_TMP_DIR
#   fi
# done

# for i in {1..27}; do
#   if [ $i -ne 211 ]; then
#     python get_prompt6.py $param1 $i -i $param3 -pn 6 -en 2
#   fi
# done

# for i in {1..27}; do 
#   if [ $i -ne 21 ]; then
#     python transforming6.py $param1 $i -i $param3 -pn 6 -en 2
#   fi
# done

################### ###Original###################################################
# 1st try
for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 1
  fi
done
# # 2nd try
for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 2
  fi
done
# # 3rd try
for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 3
  fi
done
#####################Original###################################################


#####################Transforming###################################################
for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 1 -m 
  fi
done

for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 2 -m 
  fi
done

for i in {1..27}; do
  if [ $i -ne 21 ]; then
    python query.py $param1 $i -i $param3 -pn 6 -en 2 -t 3 -m 
  fi
done
#####################Transforming###################################################
