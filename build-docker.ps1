$sha = (git rev-parse HEAD)
# 定义 Maven 容器名称
$mavenContainerName = "maven-build"

# 构建 Maven 容器
docker build -t $mavenContainerName -f Dockerfile .

# 定义 targets
$targets = @("auth", "workflow", "orchestration")

# 循环构建每个 target 镜像
foreach ($target in $targets) {
    $targetImageName = "damingerdai/$target"

    # 构建 target 镜像
    docker build -t $targetImageName:$sha -f Dockerfile --build-arg target=$target .
}
