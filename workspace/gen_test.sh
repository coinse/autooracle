#!/bin/bash

PROJECT=$1
VERSION=$2
BUGGY_TMP_DIR=/tmp/${PROJECT}-${VERSION}b

METADATA_DIR=/root/results/metadata/${PROJECT}-${VERSION}
FAILING_TESTS=$METADATA_DIR/tests.trigger
RELEVANT_CLASSES=$METADATA_DIR/classes.relevant
RELEVANT_METHODS_DIR=$METADATA_DIR/methods.relevant
COV_DIR=$METADATA_DIR/coverage

echo "\n************************************"
echo $PROJECT $VERSION
echo "************************************\n"

echo $BUGGY_TMP_DIR
[ -d "$BUGGY_TMP_DIR" ] && rm -rf $BUGGY_TMP_DIR

#########################
# checkout buggy version
#########################
defects4j checkout -p ${PROJECT} -v ${VERSION}b -w $BUGGY_TMP_DIR
if [ -d "$BUGGY_TMP_DIR" ]; then
    echo "Checkout succeed!"
else 
    echo "Checkout failed@@"
    exit 1
fi

if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
fi

#########################
# export metadata
#########################
cd $BUGGY_TMP_DIR
# epxort list of test methods that trigger(expose) the bug
defects4j export -p tests.trigger -o $FAILING_TESTS
# export classes loaded by the JVM when executing all triggering tests 
defects4j export -p classes.relevant -o $RELEVANT_CLASSES

echo "Failing tests"
cat $FAILING_TESTS
echo "\nRelevant classes"
cat $RELEVANT_CLASSES

echo "" >> $FAILING_TESTS
echo "" >> $RELEVANT_CLASSES
echo ""

if [ -d "$RELEVANT_METHODS_DIR" ]; then
    echo "$RELEVANT_METHODS_DIR exists"
else
    mkdir $RELEVANT_METHODS_DIR
fi

if [ -d "$COV_DIR" ]; then
    echo "$COV_DIR exists"
else 
    mkdir $COV_DIR
fi

while IFS= read -r tc
do 
    cd $BUGGY_TMP_DIR
    COV_FILE="$COV_DIR/$tc.xml"
    echo $COV_FILE
    if [ -f "$COV_FILE" ]; then
        echo "$COV_FILE exists"
    else 
        echo "Measuring the coverage of $tc..."
        defects4j coverage -t "$tc" -i $RELEVANT_CLASSES
        mv coverage.xml "$COV_DIR/$tc.xml"
    fi 
done < $FAILING_TESTS

#########################
