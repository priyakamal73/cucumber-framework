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
                bat 'java -version'
                bat 'mvn --version' 
                bat 'cucumber --version' 
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install' 
            }
        }

        stage('Run Tests in Parallel') {
            parallel {
                stage('Run Web App Tests') {
                    steps {
                        bat 'cucumber -t @WebApp' 
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml' 
                            cucumber '**/target/cucumber-reports/*.json' 
                        }
                    }
                }
                stage('Run Mobile App Tests') {
                    steps {
                        bat 'cucumber -t @MobileApp' 
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml' 
                            cucumber '**/target/cucumber-reports/*.json' 
                        }
                    }
                }
                stage('Run API Tests') {
                    steps {
                        bat 'cucumber -t @API' 
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml' 
                            cucumber '**/target/cucumber-reports/*.json' 
                        }
                    }
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
            echo 'Build and Tests completed successfully!'
        }
        failure {
            echo 'Build or Tests failed!'
        }
    }
}
