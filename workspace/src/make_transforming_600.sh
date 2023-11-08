# #!/bin/bash!bin
param1="Lang"
param3="newTS_600"
param4=600
param5=0

for i in {1..65}; do
  if [ $i -ne 2 ]; then
    python transforming.py $param1 $i -i $param3 -pn 4 -en 2
  fi
done
