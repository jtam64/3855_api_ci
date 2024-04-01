def call(dockerRepoName, imageName){
    pipeline {
        agent any
        parameters {
            booleanParam(defaultValue: false, description: 'Deploy the App', name: 'DEPLOY')
        }
        stages {
            stage('Build') {
            }
            stage('Lint'){
            }
            stage('Security'){
            }
            stage('Package'){
            }
            stage('Deploy'){
            }
        }
    }
}