pipeline {
    agent any

    environment {
        PATH = "${env.JAVA_HOME}\\bin;${env.MAVEN_HOME}\\bin;C:\\Ruby33-x64\\bin;${env.PATH}"
    }

    stages {
        stage('Check Tools') {
            steps {
                bat 'java -version'
                bat 'mvn --version'
                bat 'git --version'
                bat 'gem --version'
                bat 'cucumber --version'
            }
        }

        stage('Check Git Access') {
            steps {
                bat 'git --version'
            }
        }

        stage('Checkout Code') {
            steps {
                script {
                    try {
                        git branch: 'main', url: 'https://github.com/priyakamal73/cucumber-framework.git'
                    } catch (Exception e) {
                        echo "Error during checkout: ${e}"
                        throw e
                    }
                }
            }
        }

        stage('Build Project') {
            steps {
                script {
                    try {
                        bat 'mvn clean install'
                    } catch (Exception e) {
                        echo "Error during build: ${e}"
                        throw e
                    }
                }
            }
        }

        stage('Run Tests') {
            parallel {
                stage('Run Web App Tests') {
                    steps {
                        script {
                            try {
                                bat 'cucumber -t @WebApp'
                            } catch (Exception e) {
                                echo "Error during Web App tests: ${e}"
                                throw e
                            }
                        }
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
                        script {
                            try {
                                bat 'cucumber -t @MobileApp'
                            } catch (Exception e) {
                                echo "Error during Mobile App tests: ${e}"
                                throw e
                            }
                        }
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
                        script {
                            try {
                                bat 'cucumber -t @API'
                            } catch (Exception e) {
                                echo "Error during API tests: ${e}"
                                throw e
                            }
                        }
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
