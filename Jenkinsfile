pipeline {
    agent any

    environment {
        // Jenkins credentials ID for GitHub (username + PAT)
        GIT_CREDENTIALS = credentials('github')
    }

    tools {
        jdk 'JAVA_HOME'    // Jenkins JDK installation name
        maven 'Maven'      // Jenkins Maven installation name
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
                echo 'Running Maven clean install and test...'
                bat 'mvn clean test'
            }
        }

        stage('Publish TestNG Results') {
    steps {
        echo 'Publishing TestNG results...'
        // Works for both default TestNG and Maven Surefire
        testngResults pattern: '**/test-output/testng-results.xml, **/target/surefire-reports/testng-results.xml'
    }
}
    }

    post {
        always {
            cleanWs()  // Clean workspace after every build
        }
        success {
            echo 'Build Successful!'
        }
        failure {
            echo 'Build Failed! Check logs for errors.'
        }
    }
}
