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
                        sh '. venv/bin/activate'
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
                        sh '''
                        . venv/bin/activate
                        pip install bandit 
                        bandit -r *.py -f html -o bandit.html || true
                        ''' 
                        archiveArtifacts artifacts: 'bandit.html'
                    }
                }
            }
            stage('Package'){
                when {
                    expression { env.GIT_BRANCH == 'origin/main'}
                }
                steps {
                        withCredentials([string(credentialsId: 'jackDockerHub', variable: 'TOKEN')]) {
                        sh "docker login -u 'jacklf2' -p '$TOKEN' docker.io"
                        sh "docker build -t ${dockerRepoName}:latest --tag jacklf2/${dockerRepoName}:${imageName} ."
                        sh "docker push jacklf2/${dockerRepoName}:${imageName}"
                    }
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