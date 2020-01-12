pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				def mavenHome = tool name: 'Maven', type: 'maven'
				def mvn = "${mavenHome}/bin/mvn"
				sh "${mvn} clean package"
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
