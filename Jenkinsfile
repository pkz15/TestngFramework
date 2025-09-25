pipeline {
    agent any

    environment {
        // Jenkins credentials ID for GitHub (username + PAT)
        GIT_CREDENTIALS = credentials('github')
        // Path to Maven settings.xml with local repo configuration
        MAVEN_SETTINGS = 'C:\\Users\\premm\\.m2\\settings.xml'
        // Optional: override Maven local repo inside workspace
        MAVEN_LOCAL_REPO = "${WORKSPACE}\\.m2repo"
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
                echo 'Running Maven clean and test using local repository...'
                // Use -s to point to your settings.xml with local repo
                // Use -Dmaven.repo.local to force Maven to use workspace-local repo
                bat "mvn clean test -s \"${MAVEN_SETTINGS}\" -Dmaven.repo.local=\"${MAVEN_LOCAL_REPO}\""
            }
        }

        stage('Publish TestNG Results') {
            steps {
                echo 'Publishing TestNG results...'
                // Ensure path matches where surefire-plugin writes testng-results.xml
                testngResults pattern: '**/target/surefire-reports/testng-results.xml'
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
            echo 'Build Failed!  Check logs for errors.'
        }
    }
}
