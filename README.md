# hoteler cloud

hoteler for spring cloud

## docker

create docker network

```shell
docker network create hoteler-network 
```

create docker volume for `mysql`

```shell
docker volume create --name=hoteler-mysql-volume 
```

pull docker image

```shell
docker-compose pull
```

run service

```shell
docker-compose up
```