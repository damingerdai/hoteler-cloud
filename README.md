# hoteler cloud

hoteler for spring cloud

## docker

create docker network

```shell
docker network create hoteler-network 
```

create docker volume for `mysql`, `postgres`

```shell
docker volume create --name=hoteler-mysql-volume

docker volume create --name=hoteler-postgres-volume

docker volume create --name=hoteler-bytebase-volume

docker volume create --name=hoteler-nacos-volume
```

pull docker image

```shell
docker-compose pull
```

run service

```shell
docker-compose up
```