pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = credentials('github')  // your existing credential ID
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/pkz15/TestngFramework.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Fetch & Merge') {
            steps {
                bat """
                cd %WORKSPACE%
                git fetch origin
                git checkout -B main origin/main
                git merge origin/main
                IF ERRORLEVEL 1 (
                    echo Merge conflicts found. Please resolve manually
                    exit /b 1
                )
                """
            }
        }

        stage('Add & Commit Changes') {
            steps {
                bat """
                git add .
                git commit -m "Automated Jenkins commit" || echo No changes to commit
                """
            }
        }

        stage('Push Changes') {
            steps {
                bat """
                git push https://%GIT_CREDENTIALS_USR%:%GIT_CREDENTIALS_PSW%@github.com/pkz15/TestngFramework.git main
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
