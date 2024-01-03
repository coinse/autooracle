#!/bin/bash
PROJECT=$1
VERSION=$2

FIXED_DIR=/tmp/${PROJECT}-${VERSION}f
METADATA_DIR=$FIXED_DIR/metadata
MUTANTS_LOGS=/tmp/${PROJECT}-${VERSION}f/mutants_logs
MUTANTS_FILES=/tmp/${PROJECT}-${VERSION}f/mutants_files
DEV_WRITTEN_INFO=/root/workspace/result/${PROJECT}-${VERSION}f_mutated/dev_written_info
########################
#checkout fixed version
########################
if [ -d "$FIXED_DIR" ]; then
    echo "Checkout succeed!"
else 
    defects4j checkout -p ${PROJECT} -v ${VERSION}f -w $FIXED_DIR
    defects4j compile -w $FIXED_DIR
fi

if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
fi

python utils/getAllClassList.py ${PROJECT} ${VERSION}

# Export mutated files
export MAJOR_OPT="-J-Dmajor.export.mutants=true"

[ ! -d "$MUTANTS_LOGS" ] && mkdir "$MUTANTS_LOGS"
[ ! -d "$MUTANTS_FILES" ] && mkdir "$MUTANTS_FILES"
while IFS= read -r cl
do 
    [ -f "$METADATA_DIR/tmp" ] && rm $METADATA_DIR/tmp
    echo "$cl" 
    echo "$cl" > $METADATA_DIR/tmp
    defects4j mutation -w $FIXED_DIR -i $METADATA_DIR/tmp
    if [ -s "$FIXED_DIR/mutants.log" ]; then
        cp $FIXED_DIR/mutants.log $MUTANTS_LOGS/$cl.log
        cp -r $FIXED_DIR/mutants/. $MUTANTS_FILES/$cl
        rm $FIXED_DIR/mutants.log && rm -r $FIXED_DIR/mutants
    fi 
done < $METADATA_DIR/all.classes

[ ! -d "$DEV_WRITTEN_INFO" ] && mkdir -p "$DEV_WRITTEN_INFO"
defects4j export -p dir.src.classes -o $DEV_WRITTEN_INFO/dir.src.classes -w $FIXED_DIR
defects4j export -p dir.bin.classes -o $METADATA_DIR/dir.bin.classes -w $FIXED_DIR

# # Mutation
echo "********************************"
echo "Mutation!"
echo "********************************"
python mutation.py ${PROJECT} ${VERSION}