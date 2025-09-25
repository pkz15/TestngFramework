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
                script {
                    def testResults = findFiles(glob: '**/test-output/testng-results.xml')
            if (testResults.length > 0) {
                testngResults pattern: '**/test-output/testng-results.xml'
            } else {
                echo 'No TestNG results found in test-output!'
            }
                }
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
