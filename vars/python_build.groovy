def call(dockerRepoName, imageName){
    pipeline {
        agent any
        parameters {
            booleanParam(defaultValue: false, description: 'Deploy the App', name: 'DEPLOY')
        }
        stages {
            stage('Build') {
                steps {
                    dir(${imageName}) {
                        sh 'rm -rf venv'
                        sh 'python3 -m venv venv'
                        sh 'chmod +x venv/bin/activate'
                        sh '. venv/bin/activate'
                        sh 'pip install -r requirements.txt'
                    }
                }
            }
            stage('Lint'){
                steps {
                    echo '${dockerRepoName}'
                }
            }
            stage('Security'){
                steps {
                    echo '${dockerRepoName}'
                }
            }
            stage('Package'){
                steps {
                    echo '${dockerRepoName}'
                }
            }
            stage('Deploy'){
                steps {
                    echo '${dockerRepoName}'
                }
            }
        }
    }
}