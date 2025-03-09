pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/priyakamal73/cucumber-framework.git'
            }
        }

        stage('Setup Environment') {
            steps {
                bat 'echo Setting up environment...'
                bat 'mvn --version'  // Check if Maven is installed
                bat 'java -version' // Check if Java is installed
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test' // Run tests
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' 
                    cucumber '**/target/cucumber-reports/*.json' 
                }
            }
        }

        stage('Generate Reports') {
            steps {
                bat 'echo Generating reports...'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true 
            archiveArtifacts artifacts: '**/target/cucumber-reports/*.json', fingerprint: true 
        }
        success {
            echo 'Build and Tests completed
