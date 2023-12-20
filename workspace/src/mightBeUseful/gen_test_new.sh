#!/bin/bash

PROJECT=$1
VERSION=$2
ID=$3
BUDGET=$4
SEED=$5

WORK_DIR=/root/workspace
RESULT_DIR=$WORK_DIR/result/${PROJECT}-${VERSION}f
FIXED_TMP_DIR=/tmp/${PROJECT}-${VERSION}f
METADATA_DIR=$RESULT_DIR/metadata

EVOSUITE_DEFAULT_CONFIG=$WORK_DIR/evosuite-config 
EVOSUITE_ID=$METADATA_DIR/$ID
EVOSUITE_CONFIG=$EVOSUITE_ID/evosuite-config.$ID.$BUDGET.$SEED
#########################
# checkout fixed version
#########################
if [ -d "$FIXED_TMP_DIR" ]; then
    echo "Checkout succeed!"
else 
    defects4j checkout -p ${PROJECT} -v ${VERSION}f -w $FIXED_TMP_DIR
    defects4j compile -w $FIXED_TMP_DIR
fi

if [ -d "$METADATA_DIR" ]; then
    echo "$METADATA_DIR exists"
else
    mkdir -p $METADATA_DIR
fi

python utils/getAllClassList.py ${PROJECT} ${VERSION} 

if [ -f "$EVOSUITE_CONFIG" ]; then
    echo "config file exists"
else
    mkdir -p $EVOSUITE_ID
    echo "copying config file..."
    cp $EVOSUITE_DEFAULT_CONFIG $EVOSUITE_CONFIG
fi
echo "Evosuite Config: $EVOSUITE_CONFIG"

project_cp=$(defects4j export -p cp.compile -w $FIXED_TMP_DIR)
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
        [ ! -d $EVOSUITE_PROMPT ] && mkdir $EVOSUITE_PROMPT
        [ ! -d $EVOSUITE_CHAT_REPLY ] && mkdir $EVOSUITE_CHAT_REPLY
        [ ! -d $EVOSUITE_PROMPT_MUT ] && mkdir $EVOSUITE_PROMPT_MUT
    else 
        echo "no methods info"
    fi
done;

# while IFS= read -r cl
# do 
#     [ -f "$FIXED_DIR/metadata/tmp" ] && rm $FIXED_DIR/metadata/tmp
#     echo "$cl" 
#     echo "$cl" > $FIXED_DIR/metadata/tmp
#     defects4j mutation -w $FIXED_DIR -i $FIXED_DIR/metadata/tmp
#     if [ -s "$FIXED_DIR/mutants.log" ]; then
#         cp $FIXED_DIR/mutants.log $MUTANTS_LOGS/$cl.log
#         cp -r $FIXED_DIR/mutants/. $MUTANTS_FILES/$cl
#         rm $FIXED_DIR/mutants.log && rm -r $FIXED_DIR/mutants
#     fi 
# done < $METADATA_DIR/all.classes