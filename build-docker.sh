#!/bin/bash

set -e

sha=$(git rev-parse --short HEAD)
author=$(git log -1 --pretty=format:'%an')  # 获取提交者的用户名

# 定义 Maven 容器名称
mavenContainerName="maven-build"

# 构建 Maven 容器
docker build -t $mavenContainerName -f Dockerfile . || { echo "Maven 容器构建失败"; exit 1; }

# 定义 targets
targets="auth workflow orchestration"

# 循环构建每个 target 镜像
for target in $targets; do
  targetImageName="damingerdai/$target"

  # 构建 target 镜像
  docker build -t $targetImageName:${sha} -f Dockerfile --build-arg target=$target . || { echo "目标镜像 $targetImageName:${sha} 构建失败"; exit 1;

