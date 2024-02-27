#!/bin/bash
param0="Lang"
param1="1"
param2="newTS_300"
param3=300
param4=0

start_idx=$1
end_idx=$2

for ((i=0; i<=4723; i++)); do
  python prompt.py $param0 $param1 -idx $i -i $param2 -pr 11 -ex 0 -conv
done