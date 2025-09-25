pipeline {
    agent any

    environment {
        // Jenkins credentials ID for GitHub username + PAT
        GIT_CREDENTIALS = credentials('github-creds')
        TESTNG_JAR = 'lib/testng-7.8.1.jar'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pkz15/TestngFramework.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Prepare Lib') {
            steps {
                bat """
                REM Create lib folder if not exists
                if not exist lib mkdir lib

                REM Download TestNG JAR if not exists
                if not exist %TESTNG_JAR% (
                    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/testng/testng/7.8.1/testng-7.8.1.jar' -OutFile '%TESTNG_JAR%'"
                )
                """
            }
        }

        stage('Compile Java') {
            steps {
                bat """
                REM Create bin folder for compiled classes
                if not exist bin mkdir bin

                REM Compile all Java files in src using TestNG JAR
                javac -d bin -cp "lib/*" src\\*.java
                """
            }
        }

        stage('Run TestNG') {
            steps {
                bat """
                REM Run TestNG tests
                java -cp "lib/*;bin" org.testng.TestNG testng.xml
                """
            }
        }
    }

    post {
        always {
            echo "Pipeline finished"
        }
    }
}
