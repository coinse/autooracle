#!/bin/bash
param1="Lang"
param2="1"
param4="newTS_300"
param5=300
param6=0

###1. Generate test and metadata
for i in {301..500}; do
    echo "****************************************************"
    echo "Generating test suite for class [$i] "
    echo "****************************************************"
    sh gen_test_mut.sh $param1 $param2 $i $param4 $param5 $param6
done