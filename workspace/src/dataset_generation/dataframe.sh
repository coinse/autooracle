#!/bin/bash
param0="Lang"
param1="1"
param2="newTS_300"

start_idx=$1
end_idx=$2

for ((i=start_idx; i<=end_idx; i++)); do
  python dataframe.py $param0 $param1 -idx $i -i $param2 
done