# #!/bin/bash!bin

param1="Lang"
param3="newTS_600"
param4=600
param5=0

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    sh gen_test.sh $param1 $i $param3 $param4 $param5
  fi
done


