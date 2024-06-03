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
         stage("Build backend"){
            steps {
                echo "Build back end"
                dir('back') {
                    echo "Move in backend folder"
                    bat "mvn install -DskipTests"
                    // some block
                    
                }
            }
        }
         stage("Build frontend"){
            steps {
                echo "Build frontend"
                  dir('front') {
                    echo "Move in frontend folder"
                    bat "npm install"
                }
            }
        }
    }
}
