# #!/bin/bash!bin
param1="Lang"
param3="newTS_300"
param4=300
param5=0

for i in {17..17}; do
  if [ $i -ne 2 ]; then
    python transforming.py $param1 $i -i $param3 -pn 4 -en 2
  fi
done
