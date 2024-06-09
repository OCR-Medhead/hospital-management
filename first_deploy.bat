@echo off

echo Creation de l'image et du container front-end
cd front
docker build -t hospital_management_web . && docker run --name hospitalFrontEnd -dp 80:80 hospital_management_web

cd ..

echo Creation de l'image et du container backend
cd back
mvn package && docker build -t hospital_management_api . && docker run --name hospitalBackEnd -dp 8080:8888 hospital_management_api