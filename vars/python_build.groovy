def call(dockerRepoName, imageName){
    pipeline {
        agent any
        parameters {
            booleanParam(defaultValue: false, description: 'Deploy the App', name: 'DEPLOY')
        }
        stages {
            stage('Build') {
                steps {
                    echo 'Building the Python App'
                }
            }
            stage('Lint'){
                steps {
                    echo 'Building the Python App'
                }
            }
            stage('Security'){
                steps {
                    echo 'Building the Python App'
                }
            }
            stage('Package'){
                steps {
                    echo 'Building the Python App'
                }
            }
            stage('Deploy'){
                steps {
                    echo 'Building the Python App'
                }
            }
        }
    }
}