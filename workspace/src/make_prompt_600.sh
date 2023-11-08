# #!/bin/bash!bin

param1="Lang"
param3="newTS_600"
param4=600
param5=0



# for i in {1..65}; do
#   if [ $i -ne 2 ]; then
#     BUGGY_TMP_DIR=/tmp/${param1}-${i}b
#     defects4j checkout -p ${param1} -v ${i}b -w $BUGGY_TMP_DIR
#     if [ -d "$BUGGY_TMP_DIR" ]; then
#         echo "Checkout succeed!"
#     else 
#         echo "Checkout failed"
#         exit 1
#     fi
#   fi
# done

for i in {20..65}; do
  if [ $i -ne 2 ]; then
    python get_prompt.py $param1 $i -i $param3 -en 2
  fi
done


