# hoteler cloud

hoteler for spring cloud

## docker

create docker network

```shell
docker network create hoteler-network 
```

create docker volume for `user service`

```shell
docker volume create --name=hoteler-user-volume 
```

pull docker image

```shell
docker-compose pull
```