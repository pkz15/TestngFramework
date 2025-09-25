pipeline {
    agent any
    environment {
        GIT_CREDENTIALS = credentials('github')  // Your GitHub credentials ID
    }
    tools {
        jdk 'JAVA_HOME'      // Name from Jenkins Global Tool Configuration
        maven 'Maven'    // Name from Jenkins Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pkz15/TestngFramework.git',
                    credentialsId: 'github'
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'  // Runs TestNG tests through Maven
            }
        }

        stage('Publish TestNG Results') {
            steps {
                publishTestNGResults testResultsPattern: '**/target/surefire-reports/testng-results.xml'
            }
        }
    }

    post {
        always {
            cleanWs() 
        }
        failure {
            echo 'Build failed! Check logs for details.'
        }
        success {
            echo 'Build successful!'
        }
    }
}
