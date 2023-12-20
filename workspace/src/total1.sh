#!/bin/bash
param0="Time"
param1="1"
param2="newTS_300"
param3=300
param4=0

for i in {1..200}; do
  sh gen_test_mut.sh $param0 $param1 $i $param2 $param3 $param4
done
