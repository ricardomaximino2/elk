pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				${tool name: 'Maven', type: 'maven'}/bin/mvn clean
				
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
