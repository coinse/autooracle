# #!/bin/bash!bin

param1="Lang"
param3="newTS_600"
param4=600
param5=0


for i in {1..2}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 1
  fi
done
# # 2nd try
for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 2
  fi
done
# # 3rd try
for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 3
  fi
done


for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 1 -m 
  fi
done

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 2 -m 
  fi
done

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python query.py $param1 $i -i $param3 -pn 4 -en 2 -t 3 -m 
  fi
done

