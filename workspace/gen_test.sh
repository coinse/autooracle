#!/bin/bash

PROJECT=$1
VERSION=$2
ID=$3
BUDGET=$4
SEED=$5

WORK_DIR=/root/workspace
RESULT_DIR=$WORK_DIR/result/${PROJECT}-${VERSION}b
BUGGY_TMP_DIR=/tmp/${PROJECT}-${VERSION}b
FIXED_TMP_DIR=/tmp/${PROJECT}-${VERSION}f

METADATA_DIR=$RESULT_DIR/metadata
FAILING_TESTS=$METADATA_DIR/tests.trigger
RELEVANT_CLASSES=$METADATA_DIR/classes.relevant
RELEVANT_METHODS_DIR=$METADATA_DIR/methods.relevant
COV_DIR=$METADATA_DIR/coverage

EVOSUITE=$WORK_DIR/evosuite-master-1.0.7-SNAPSHOT.jar
EVOSUITE_DEFAULT_CONFIG=$WORK_DIR/evosuite-config 

EVOSUITE_ID=$RESULT_DIR/$ID
EVOSUITE_CONFIG=$EVOSUITE_ID/evosuite-config.$ID.$BUDGET.$SEED
EVOSUITE_TEST=$EVOSUITE_ID/evosuite_test
EVOSUITE_REPORT=$EVOSUITE_ID/evosuite_report



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

#########################
# export metadata
#########################
if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
fi

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
cd $WORK_DIR
python save_relevant_methods.py $COV_DIR $RELEVANT_METHODS_DIR

cd $BUGGY_TMP_DIR

if [ -d "$EVOSUITE_TEST" ]; then
    echo "test suite $EVOSUITE_TEST exists"
else
    project_cp=$(defects4j export -p cp.compile -w $BUGGY_TMP_DIR)
    [ ! -d $EVOSUITE_ID ] && mkdir $EVOSUITE_ID

    if [ -f "$EVOSUITE_CONFIG" ]; then
        echo "config file exists"
    else
        echo "copying config file..."
        cp $EVOSUITE_DEFAULT_CONFIG $EVOSUITE_CONFIG
    fi
    echo "Evosuite Config: $EVOSUITE_CONFIG"

    for class in $(cat $RELEVANT_CLASSES); do
        echo $class
        if [ -f "$RELEVANT_METHODS_DIR/$class" ]; then
            budget_ratio_file=$RELEVANT_METHODS_DIR/$class.budget
            echo $budget_ratio_file
            budget_ratio=$(cat $budget_ratio_file)
            echo $budget_ratio_file
            echo $budget_ratio
            class_budget=$(python3.6 -c "from math import ceil; print(int(ceil($budget_ratio*$BUDGET)))")
            echo $class_budget
            echo "----------------------------------------------------"
            echo "Generating test suite for class [$class] (w/ budget: ${class_budget}s)"
            echo "- Target methods:"
            cat $RELEVANT_METHODS_DIR/$class
            echo ""
            echo "----------------------------------------------------"
            cat $RELEVANT_METHODS_DIR/$class
            echo "java -jar $EVOSUITE -class $class -projectCP $project_cp -Dsearch_budget=$class_budget -seed=$SEED -Dreport_dir=$EVOSUITE_REPORT -Dtest_dir=$EVOSUITE_TEST -Dtarget_method_list=$(cat $RELEVANT_METHODS_DIR/$class | tr '\n' ':' | sed 's/:$//') $(cat ${EVOSUITE_CONFIG})"
            java -jar $EVOSUITE -class $class -projectCP $project_cp -Dsearch_budget=$class_budget -seed=$SEED -Dreport_dir=$EVOSUITE_REPORT -Dtest_dir=$EVOSUITE_TEST -Dtarget_method_list=$(cat $RELEVANT_METHODS_DIR/$class | tr '\n' ':' | sed 's/:$//') $(cat ${EVOSUITE_CONFIG})
            [ ! -d $EVOSUITE_REPORT ] && mkdir $EVOSUITE_REPORT
            cat $EVOSUITE_REPORT/statistics.csv >> $EVOSUITE_REPORT/statistics.${PROJECT}-${VERSION}.csv
            rm $EVOSUITE_REPORT/statistics.csv
            
        else 
            echo "no methods info"
        fi
    done;
fi

if [ -d "$FIXED_TMP_DIR" ]; then
    echo "$FIXED_TMP_DIR exists"
else
    defects4j checkout -p ${PROJECT} -v ${VERSION}f -w $FIXED_TMP_DIR
    if [ -d "$FIXED_TMP_DIR" ]; then
        echo "Checkout succeed!" 
    else 
        exit 1
    fi
fi

cd $WORK_DIR
python ground_truth.py $PROJECT $VERSION --id $ID


#########################
