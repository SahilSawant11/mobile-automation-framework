pipeline {
  agent any
  stages {
    stage('Build') { steps { sh 'mvn -DskipTests package' } }
    stage('Test') { steps { sh 'mvn test' } }
    stage('Allure') { steps { sh './scripts/generate-report.sh' } }
  }
}
