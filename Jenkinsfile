pipeline {
	agent any
	
	stages {
		
		stage('Build docker image') {
			steps {
				echo 'Build docker image....'
				sh 'docker -version'
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deployin....'
			}
		}
	}
}
