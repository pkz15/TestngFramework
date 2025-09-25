pipeline {
    agent any

    environment {
        // Jenkins credentials ID for GitHub (username + PAT)
        GIT_CREDENTIALS = credentials('github')
        MAVEN_SETTINGS = 'C:\\Users\\premm\\.m2\\settings.xml'
    }

    tools {
        jdk 'JAVA_HOME'       // Must match your Jenkins JDK installation name
        maven 'Maven'         // Must match your Jenkins Maven installation name
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main',
                    url: 'https://github.com/pkz15/TestngFramework.git',
                    credentialsId: 'github'
            }
        }

        stage('Verify Tools') {
            steps {
                echo 'Checking Java and Maven versions...'
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Running Maven clean and test...'
                // Force Maven to update dependencies in case of missing artifacts
                  bat 'mvn clean test -Dmaven.repo.local=lib'
            }
        }

        stage('Publish TestNG Results') {
            steps {
                echo 'Publishing TestNG results...'
                // TestNG plugin parses testng-results.xml in the target folder
                testngResults pattern: '**/target/surefire-reports/testng-results.xml'
            }
        }
    }

    post {
        always {
            cleanWs()  // Clean workspace after every build
        }
        success {
            echo 'Build Successful! ✅'
        }
        failure {
            echo 'Build Failed! ❌ Check logs for errors.'
        }
    }
}
