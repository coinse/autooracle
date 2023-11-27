import os

class EvoD4jEnvMut:
    WORK_DIR = '/root/workspace/'
    TMP_ROOT = '/tmp'
    
    def __init__(self, project, version, idx = '0', ts_id="no_name_specified"):
        cls = self.__class__
        self.project = project
        self.version = version
        self.d4j_id = "{}-{}".format(self.project, self.version)
        self.ts_id = str(ts_id)

        self.original_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f")
        self.mutated_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f_mutated")
        self.metadata_dir = os.path.join(self.original_dir, "metadata")
        self.mutant_log = os.path.join(self.original_dir, "mutants.log")
        self.dev_src_relpath = os.path.join(self.metadata_dir, "dir.src.classes")
        self.classes_modified = os.path.join(self.metadata_dir, "classes.modified")
        ##TO FIX
        self.dev_test_relpath = os.path.join(self.metadata_dir,"dir.src.tests")
        self.dev_written_test_classes = os.path.join(self.metadata_dir,"tests.all")

        self.java_analyzer = os.path.join(cls.WORK_DIR,"java-analyzer-1.0-SNAPSHOT-shaded.jar")
        
        self.result_dir = os.path.join(cls.WORK_DIR, "result", self.d4j_id+"f_mutated", idx)
        self.evosuite_test_dir  = os.path.join(self.result_dir, "generated_test", self.ts_id)
        self.evosuite_test_src_dir = os.path.join(self.evosuite_test_dir, "evosuite_test")