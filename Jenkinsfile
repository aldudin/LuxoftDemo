pipeline {
  agent any
  stages {
    stage('Fetch source') {
      steps {
        git(url: 'https://github.com/Richand101/LuxoftDemo', branch: 'develop', credentialsId: 'GitHub Webhooks Token')
      }
    }

    stage('Build') {
      steps {
        load 'deploy/jenkins/jenkinsfile-ansible-webapp'
      }
    }

  }
}