pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				echo "${tool name: 'Maven', type: 'maven'} is the maven location."
				
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
