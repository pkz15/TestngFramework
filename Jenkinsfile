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
       junit '**/target/surefire-reports/*.xml'
    }
}
        stage('Archive TestNG HTML Report') {
    steps {
        archiveArtifacts artifacts: 'test-output/**', fingerprint: true
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
