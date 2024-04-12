# METAMON: Finding Inconsistencies between Program Documentation and Behavior using Metamorphic LLM Queries

This is an artifact accompanying the paper **METAMON: Finding Inconsistencies between Program Documentation and Behavior using Metamorphic LLM Queries**.

## Requirements
- H/W
  - A processor with the linux/amd64 architecture (only for RQ2-4)
- S/W
  - 🐍 Python 3.9.1
    - Installing dependencies
      ```shell
      pip install -r requirements.txt
      ```
  - 🐳 docker
- Evaluation Dataset
  - The regression tests, metamorphic prompts, and LLM responses used in our evaluation are located within the `workspace/result` folder, organized by each project's name. 
  - The analysis for the Research Questions based on these dataset can be found in `workspace/result/analyze/analyze.ipynb`



### Package structure
```bash
├── workspace
│     ├──result
│     │   ├── Chart
│     │   ├── Closure
│     │   ├── Lang
│     │   ├── Math
│     │   └── Time
│     └── src
│         ├── analyze
│         ├── step1_specification_quality_check
│         ├── step2_test_genertaion
│         ├── step3_prompt_generation
│         └── step4_querying_LLM
│
├── Dockerfile
│
├── resources
│
└── README.md
```
