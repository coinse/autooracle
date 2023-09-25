#!/bin/bash

for version in {3..65}; do
  # Skip version 2
  if [ "$version" -eq 2 ]; then
    continue
  fi

  # Set the project name
  project_name="Lang"
  
  # Generate Evosuite test 
  sh gen_test.sh "$project_name" "$version" newTS 65 0

  # Make prompte using generated tests
  python make_prompt.py "$project_name" "$version" -i newTS
  
  # Mutate prompt 
  python mutate.py "$project_name" "$version" -i newTS

  # Query
  #python query.py "$project_name" "$version" -i newTS
done


