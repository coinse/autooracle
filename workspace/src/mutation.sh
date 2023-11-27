#!/bin/bash

PROJECT=$1
VERSION=$2

FIXED_TMP_DIR=/tmp/${PROJECT}-${VERSION}f
METADATA_DIR=$FIXED_TMP_DIR/metadata


#########################
# checkout fixed version
#########################
if [ -d "$FIXED_TMP_DIR" ]; then
    echo "Checkout succeed!"
else 
    defects4j checkout -p ${PROJECT} -v ${VERSION}f -w $FIXED_TMP_DIR
fi

cd $FIXED_TMP_DIR
if [ ! -f "mutants.log" ]; then
    defects4j mutation
else 
    echo "mutants.log exists"
fi 

if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
fi

defects4j export -p classes.modified -o $METADATA_DIR/classes.modified
defects4j export -p dir.src.classes -o $METADATA_DIR/dir.src.classes
defects4j export -p dir.bin.classes -o $METADATA_DIR/dir.bin.classes

cd ~/workspace/src
python mutation.py ${PROJECT} ${VERSION}