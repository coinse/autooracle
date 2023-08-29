import os
"""
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
"""
class EvoD4jEnv:
    WORK_DIR = '/root/workspace/'
    TMP_ROOT = '/tmp'
    
    def __init__(self, project, version, ts_id):
        cls = self.__class__
        self.project = project
        self.version = version
        self.d4j_id = "{}-{}".format(self.project, self.version)
        self.ts_id = str(ts_id)

        self.result_dir = os.path.join(cls.WORK_DIR,"result",self.d4j_id + "b")
        self.buggy_tmp_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "b")
        self.fixed_tmp_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f")
        
        self.metadata_dir = os.path.join(self.result_dir,"metadata")
        self.failing_tests = os.path.join(self.metadata_dir, "tests.trigger")
        self.relevant_classes = os.path.join(self.metadata_dir, "classes.relevant")
        self.coverage_dir = os.path.join(self.metadata_dir,"coverage")

        self.evosuite_test_dir = os.path.join(self.result_dir, self.ts_id, "evosuite_test")



# env = EvoD4jEnv('Lang', '1', 'newTS')

# print(env.result_dir)
# print(env.buggy_tmp_dir)
# print(env.fixed_tmp_dir)
# print(env.metadata_dir)
# print(env.failing_tests)
# print(env.relevant_classes)
# print(env.coverage_dir)