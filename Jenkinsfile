pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				sh 'cd app'
				sh "${tool name: 'Maven', type: 'maven'}/bin/mvn clean"
				
			}
		}
		stage('Test') {
			steps {
				echo 'Testing..'
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deployin....'
			}
		}
	}
}
