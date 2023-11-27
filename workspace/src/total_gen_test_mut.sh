#!/bin/bash
PROJECT="Time"
VERSION="1"
ID="newTS_300"
BUDGET=300
SEED=0


for i in {1..5}; do
    sh gen_test_mut.sh $PROJECT $VERSION $i $ID $BUDGET $SEED
done