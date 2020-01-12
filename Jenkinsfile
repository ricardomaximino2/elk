pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				sh 'dir'
				cd app
				sh 'dir'
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
