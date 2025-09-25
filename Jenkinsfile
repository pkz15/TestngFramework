pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = credentials('github')
        // MAVEN_SETTINGS = 'C:\\Users\\premm\\.m2\\settings.xml'
        // LOCAL_M2_REPO = 'C:\\MavenLocalRepo'
    }

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven'
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
                echo 'Running Maven clean test with local repo...'
                // Correct way to handle Windows paths
                // bat "mvn clean test -s \"${MAVEN_SETTINGS}\" -Dmaven.repo.local=\"${LOCAL_M2_REPO}\""
                bat"mvn clean test"
            }
        }

        stage('Publish TestNG Results') {
            steps {
                echo 'Publishing TestNG results...'
                testngResults pattern: '**/target/surefire-reports/testng-results.xml'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build Successful! ✅'
        }
        failure {
            echo 'Build Failed! ❌ Check logs.'
        }
    }
}
