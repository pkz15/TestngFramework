pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = credentials('github')              // GitHub credentials
        MAVEN_SETTINGS = 'C:\\Users\\premm\\.m2\\settings.xml' // Optional if using custom settings
    }

    tools {
        jdk 'JAVA_HOME'       // Name of your JDK installation in Jenkins
        maven 'Maven'         // Name of your Maven installation in Jenkins
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

        stage('Build and Test') {
            steps {
                echo 'Running Maven clean and test with TestNG suite...'
                bat 'mvn clean test -DsuiteXmlFiles=src/test/resources/testng.xml'
            }
        }

        stage('Publish TestNG Results') {
            steps {
                echo 'Publishing TestNG results...'
                // TestNG plugin parses the XML generated in target/surefire-reports
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
