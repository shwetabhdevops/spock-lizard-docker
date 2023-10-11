pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            sh 'mvn --version'
            sh 'git --version'
            sh 'java -version'
          }
        }

        stage('Check for POM') {
          steps {
           script {
                    // Check if pom.xml file exists using standard shell command
                    if (fileExists('pom.xml')) {
                        echo "pom.xml exists."
                    } else {
                        echo "pom.xml does not exist."
                    }
                }
          }
        }

      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('Run Static Code Analysis') {
      steps {
        build job: static-code-analysis
      }
    }

  

    stage('Build Docker Image') {
      steps {
        build job: static-code-analysis
      }
    }

    stage('Create Executable JAR File') {
      steps {
        sh 'mvn package spring-boot:repackage'
      }
    }  

    stage('Build Docker IMage') {
      steps {
        sh 'sudo docker build -t cameronmcnz/cams-rps-service .'
      }
    }   

    stage('Software Versions') {
            steps {
                        docker push shwetabhdevops/cams-rps-service:first
                    
                }
            }
        }

    stage('Deploy to AWS') {
      steps {
            script {
                    def response = input message: 'Should we push to DockerHub?', 
                    parameters: [choice(choices: 'Yes\nNo', 
                    description: 'Proceed or Abort?', 
                    name: 'What to do???')]
                    
                    if (response=="Yes") {
                        sh 'aws ecs update-service --cluster rps-cluster --service rps-service --force-new-deployment'
                    }
                    if (response=="No") {
                         writeFile(file: 'deployment.txt', text: 'We did not deploy.')
                    }
                }
       
      }
    }

  }
}
