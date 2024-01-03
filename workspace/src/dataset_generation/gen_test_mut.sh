#!/bin/bash

PROJECT=$1
VERSION=$2
IDX=$3
ID=$4
BUDGET=$5
SEED=$6

WORK_DIR=/root/workspace
MUTAED_TMP_DIR=/tmp/${PROJECT}-${VERSION}f_before_mutated
FIXED_TMP_DIR=/tmp/${PROJECT}-${VERSION}f

RESULT_DIR=$WORK_DIR/result/${PROJECT}-${VERSION}f_mutated
DIFF_DIR=$RESULT_DIR/git_diff
METADATA_DIR=$RESULT_DIR/${IDX}/metadata
RELEVANT_CLASSES=$METADATA_DIR/classes.relevant
RELEVANT_METHODS_DIR=$METADATA_DIR/methods.relevant
DEV_WRITTEN_INFO=$RESULT_DIR/dev_written_info

EVOSUITE=$WORK_DIR/evosuite-master-1.0.7-SNAPSHOT.jar
EVOSUITE_DEFAULT_CONFIG=$WORK_DIR/evosuite-config 

EVOSUITE_ID=$RESULT_DIR/${IDX}/generated_test/$ID
EVOSUITE_CONFIG=$EVOSUITE_ID/evosuite-config.$ID.$BUDGET.$SEED
EVOSUITE_TEST=$EVOSUITE_ID/evosuite_test
EVOSUITE_REPORT=$EVOSUITE_ID/evosuite_report
EVOSUITE_PROMPT=$EVOSUITE_ID/prompt
EVOSUITE_PROMPT_MUT=$EVOSUITE_ID/prompt_mut
EVOSUITE_CHAT_REPLY=$EVOSUITE_ID/chat_reply
EVOSUITE_CHAT_REPLY_MUT=$EVOSUITE_ID/chat_reply_mut


# SETUP
[ ! -d "$METADATA_DIR" ] && mkdir -p $METADATA_DIR 
[ ! -d "$RELEVANT_METHODS_DIR" ] && mkdir -p $RELEVANT_METHODS_DIR 
[ ! -d "$EVOSUITE_ID" ] && mkdir -p $EVOSUITE_ID 
[ ! -d "$EVOSUITE_REPORT" ] && mkdir -p $EVOSUITE_REPORT 
[ ! -d "$EVOSUITE_PROMPT" ] && mkdir -p $EVOSUITE_PROMPT 
[ ! -d "$EVOSUITE_PROMPT_MUT" ] && mkdir -p $EVOSUITE_PROMPT_MUT 
[ ! -d "$EVOSUITE_CHAT_REPLY" ] && mkdir -p $EVOSUITE_CHAT_REPLY 
[ ! -d "$EVOSUITE_CHAT_REPLY_MUT" ] && mkdir -p $EVOSUITE_CHAT_REPLY_MUT 
[ ! -d "$DEV_WRITTEN_INFO" ] && mkdir -p $DEV_WRITTEN_INFO

cwd=$(pwd)
python ../utils/save_relevant_metadata.py $DIFF_DIR/${IDX}_info $METADATA_DIR
# Checkout for mutation and mutate, compile
[ -d "$MUTAED_TMP_DIR" ] && rm -rf $MUTAED_TMP_DIR
defects4j checkout -p $PROJECT -v ${VERSION}f -w $MUTAED_TMP_DIR
cd $MUTAED_TMP_DIR
git apply $DIFF_DIR/${IDX}.diff
defects4j compile


cp $DIFF_DIR/${IDX}.diff $METADATA_DIR
cp $DIFF_DIR/${IDX}_info $METADATA_DIR

if [ -f "$EVOSUITE_CONFIG" ]; then
    echo "config file exists"
else
    echo "copying config file..."
    cp $EVOSUITE_DEFAULT_CONFIG $EVOSUITE_CONFIG
fi

echo "Evosuite Config: $EVOSUITE_CONFIG"

if [ -d "$EVOSUITE_TEST" ]; then
    echo "test suite $EVOSUITE_TEST exists"
else
    project_cp=$(defects4j export -p cp.compile -w $MUTAED_TMP_DIR)
    for class in $(cat $RELEVANT_CLASSES); do
        echo $class
        if [ -f "$RELEVANT_METHODS_DIR/$class" ]; then
            budget_ratio_file=$RELEVANT_METHODS_DIR/$class.budget
            echo $budget_ratio_file
            budget_ratio=$(cat $budget_ratio_file)
            echo $budget_ratio
            class_budget=$(python3.6 -c "from math import ceil; print(int(ceil($budget_ratio*$BUDGET)))")
            echo $class_budget
            echo "----------------------------------------------------"
            echo "Generating test suite for class [$class] (w/ budget: ${class_budget}s)"
            echo "- Target methods:"
            cat $RELEVANT_METHODS_DIR/$class
            echo ""
            echo "----------------------------------------------------"
            
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

cd $cwd
python split_tests.py --evo_dir $EVOSUITE_TEST

#dev_test_relpath
[ ! -f "$DEV_WRITTEN_INFO/dir.src.tests" ] && defects4j export -p dir.src.tests -o $DEV_WRITTEN_INFO/dir.src.tests -w $FIXED_TMP_DIR

# # extract developer written test classes
[ ! -f "$DEV_WRITTEN_INFO/tests.all" ] && defects4j export -p tests.all -o $DEV_WRITTEN_INFO/tests.all -w $FIXED_TMP_DIR


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

cd $EVOSUITE_TEST && tar -cjf $EVOSUITE_ID/$PROJECT-$VERSION.tar.bz2 *
echo "tar -cjf $EVOSUITE_ID/$PROJECT-$VERSION.tar.bz2 *" 
defects4j test -w $FIXED_TMP_DIR -s $EVOSUITE_ID/$PROJECT-$VERSION.tar.bz2
cd $FIXED_TMP_DIR && cp failing_tests $EVOSUITE_ID/failing_tests_on_fixed