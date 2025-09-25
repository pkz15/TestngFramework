pipeline {
    agent any

    environment {
        // Jenkins credentials ID for GitHub username + PAT
        GIT_CREDENTIALS = credentials('github')
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pkz15/TestngFramework.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Run Tests') {
            steps {
                bat """
                REM Navigate to workspace
                cd %WORKSPACE%

                REM Run TestNG tests (if you have testng.xml in repo)
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
