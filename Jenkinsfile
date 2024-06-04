pipeline {
    agent any
    stages {
        stage("Clone repo"){
            steps {
                git branch: 'main', url: 'https://github.com/OCR-Medhead/hospital-management.git'
            }
        }
        stage("Test frontend"){
            steps {
                echo "Test frontend"
                dir("front"){
                    bat "npm install"
                    bat "npm run test-headless"
                }
            }
        }
         stage("Test backend"){
            steps {
                echo "Test backend"
                dir("back"){
                    bat "mvn test"
                }
            }
        }
        stage("Clear olds containers and images"){
            steps{
                    bat "docker stop hospitalFrontEnd"
                    bat "docker stop hospitalBackEnd"
                    bat "docker container rm hospitalFrontEnd"
                    bat "docker container rm hospitalBackEnd"
                    bat "docker image rm hospital_management_web"
                    bat "docker image rm hospital_management_api"                
            }
        }
         stage("Build frontend container"){
            steps {
                echo "Build frontend image"
                  dir('front') {
                    echo "Create docker image"
                    bat "docker build -t hospital_management_web ."
                    echo "Create docker container"
                    bat "docker run --name hospitalFrontEnd -dp 80:80 hospital_management_web"
                    echo "Start container"
                    bat "docker start hospitalFrontEnd"
                }
            }
        }
         stage("Build backend container"){
            steps {
                echo "Build backend image"
                  dir('back') {
                    echo "Build java release"
                    bat "mvn package"
                    echo "Create docker image"
                    bat "docker build -t hospital_management_api ."
                    echo "Create docker container"
                    bat "docker run --name hospitalBackEnd -dp 8080:8888 hospital_management_api"
                    echo "Start container"
                    bat "docker start hospitalBackEnd"
                }
            }
        }
    }
}
