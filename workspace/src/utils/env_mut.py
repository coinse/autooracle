import os

class EvoD4jEnvMut:
    WORK_DIR = '/root/workspace/'
    TMP_ROOT = '/tmp'
    
    def __init__(self, name, project, version, idx = '0', ts_id="no_name_specified", mut = False):
        cls = self.__class__
        self.project = project
        self.version = version
        self.d4j_id = "{}-{}".format(self.project, self.version)
        self.ts_id = str(ts_id)
        self.src_dir = os.path.join(cls.WORK_DIR,"src")

        self.fixed_tmp_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f")

        self.original_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f")
        self.before_mutated_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f_before_mutated")
        self.after_mutated_dir = os.path.join(cls.TMP_ROOT, self.d4j_id + "f_after_mutated")
        self.metadata_dir = os.path.join(self.original_dir, "metadata")
        self.mutants_logs = os.path.join(self.original_dir, "mutants_logs")
        self.mutants_files = os.path.join(self.original_dir, "mutants_files")
        
        self.dev_written_info = os.path.join(cls.WORK_DIR, "result", self.d4j_id+"f_mutated", "dev_written_info")
        self.dev_tests_df_path = os.path.join(self.dev_written_info, "dev_tests_df.pkl")
        self.dev_src_relpath = os.path.join(self.dev_written_info, "dir.src.classes")
        self.dev_test_relpath = os.path.join(self.dev_written_info,"dir.src.tests")
        self.dev_written_test_classes = os.path.join(self.dev_written_info,"tests.all")
        self.dev_written_src_analyze = os.path.join(self.dev_written_info,"dev_written_src_analyze/")
        self.dev_written_test_analyze = os.path.join(self.dev_written_info,"dev_written_test_analyze/")        

        self.java_analyzer = os.path.join(cls.WORK_DIR,"java-analyzer-1.0-SNAPSHOT-shaded.jar")
        
        self.result_dir = os.path.join(cls.WORK_DIR, "result", self.d4j_id+"f_mutated", idx)
        self.evosuite_test_dir  = os.path.join(self.result_dir, "generated_test", self.ts_id)
        self.evosuite_test_src_dir = os.path.join(self.evosuite_test_dir, "evosuite_test")

        self.evosuite_prompt_dir = os.path.join(self.evosuite_test_dir, "prompt")
        self.evosuite_prompt_mut_dir = os.path.join(self.evosuite_test_dir, "prompt_mut")
        self.evosuite_chat_reply_dir = os.path.join(self.evosuite_test_dir, "chat_reply")
        self.evosuite_chat_reply_mut_dir = os.path.join(self.evosuite_test_dir, "chat_reply_mut")