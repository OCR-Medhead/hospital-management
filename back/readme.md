# Hospital Management

POC pour vérifier la validité d'une architecture cible

# Testing 
From STS : Run As Maven test


# Deploy
From STS : Run As Maven Build Goals:package

# Run
```sh
java -jar Hopistal-0.0.1-SNAPSHOT.jar
```

# Docker

## Create image
Il faut se placer à la racine du fichier Dockerfile
 
```sh
docker build -t hospital_management .
```

## Create container
```sh
docker run -dp 8888:8888 hospital_management
```

## Run container
```sh
docker start container_name
```

## Stop container
```sh
docker stop container_name
```

## Remove container
```sh
docker container rm container_image
```

## Remove image
```sh
docker image rm hospital_management
```
