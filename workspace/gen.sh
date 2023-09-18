#!/bin/bash

for version in {1..3}; do
  # Skip version 2
  if [ "$version" -eq 2 ]; then
    continue
  fi

  # Set the project name
  project_name="Lang"

  # Execute gen_test.sh with options
  sh gen_test.sh "$project_name" "$version" 
done
