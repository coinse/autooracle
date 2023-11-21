#!/bin/bash
PROJECT=$1
VERSION=$2

WORK_DIR=/root/workspace
RESULT_DIR=/tmp/${PROJECT}-${VERSION}f
BUGGY_TMP_DIR=/tmp/${PROJECT}-${VERSION}b

METADATA_DIR=$RESULT_DIR/metadata
FAILING_TESTS=$METADATA_DIR/tests.trigger
COV_DIR=$METADATA_DIR/coverage
RELEVANT_CLASSES=$METADATA_DIR/classes.relevant
RELEVANT_METHODS_DIR=$METADATA_DIR/methods.relevant

echo "\n************************************"
echo $PROJECT $VERSION
echo "************************************\n"

echo $RESULT_DIR
[ -d "$RESULT_DIR" ] && rm -rf $RESULT_DIR

echo $BUGGY_TMP_DIR
[ -d "$BUGGY_TMP_DIR" ] && rm -rf $BUGGY_TMP_DIR


#########################
# checkout buggy version
#########################
defects4j checkout -p ${PROJECT} -v ${VERSION}f -w $RESULT_DIR
if [ -d "$RESULT_DIR" ]; then
    echo "Checkout succeed!"
else 
    echo "Checkout failed"
    exit 1
fi

#########################
# checkout buggy version
#########################
defects4j checkout -p ${PROJECT} -v ${VERSION}b -w $BUGGY_TMP_DIR
if [ -d "$BUGGY_TMP_DIR" ]; then
    echo "Checkout succeed!"
else 
    echo "Checkout failed"
    exit 1
fi

#########################
# export metadata
#########################
if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
    mkdir -p $COV_DIR
    mkdir -p $RELEVANT_METHODS_DIR
fi

cd $BUGGY_TMP_DIR
# epxort list of test methods that trigger(expose) the bug
defects4j export -p tests.trigger -o $FAILING_TESTS
# export classes loaded by the JVM when executing all triggering tests 
defects4j export -p classes.relevant -o $RELEVANT_CLASSES

echo "Failing tests"
echo "" >> $FAILING_TESTS
cat $FAILING_TESTS

echo "Relevant classes"
echo "" >> $RELEVANT_CLASSES
cat $RELEVANT_CLASSES

# measure covearage of each failing test case 
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

# get relevant methods from coverage data
cd $WORK_DIR/src
python utils/save_relevant_methods.py $COV_DIR $RELEVANT_METHODS_DIR