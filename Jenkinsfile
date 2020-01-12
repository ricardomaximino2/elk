pipeline {
	agent any
	
	stages {
		stage('Build') {
			echo 'Building...'
			steps {
				dir("${env.WORKSPACE}/app"){
					sh "${tool name: 'Maven', type: 'maven'}/bin/mvn clean"
				}
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
