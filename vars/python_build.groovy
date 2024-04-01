def call(dockerRepoName, imageName){
    pipeline {
        agent any
        parameters {
            booleanParam(defaultValue: false, description: 'Deploy the App', name: 'DEPLOY')
        }
        stages {
            stage('Build') {
                steps {
                    dir("${imageName}") {
                        sh 'rm -rf venv'
                        sh 'python3 -m venv venv'
                        sh 'chmod +x venv/bin/activate'
                        sh '. venv/bin/activate &&' 
                        sh 'pip install -r requirements.txt'
                    }
                }
            }
            stage('Lint'){
                steps {
                    dir("${imageName}") {
                        sh 'pylint --fail-under=5 *.py'
                    }
                }
            }
            stage('Security'){
                steps {
                    dir("${imageName}") {
                        sh './venv/bin/bandit -r *.py'
                    }
                }
            }
            stage('Package'){
                steps {
                    echo "${imageName}"
                }
            }
            stage('Deploy'){
                steps {
                    echo "${imageName}"
                }
            }
        }
    }
}