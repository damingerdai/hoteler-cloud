#!/bin/bash

services=('auth' 'workflow')
for service in ${services[@]}
do
  work_path=$(dirname $(readlink -f $0))
  parent_path=$(dirname "$work_path")
  echo $parent_path
  file_path="$parent_path/$service"
  echo $file_path
  cd $file_path && mvn flyway:migrate && cd $parent_path
done

