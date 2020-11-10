pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/millj5/jgsu-spring-petclinic.git',branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            
            post {
                always {
                  junit '**/target/surefire-reports/TEST-*.xml'
                   archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}