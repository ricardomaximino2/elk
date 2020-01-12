pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				echo 'Building...'
				dir("${env.WORKSPACE}/app"){
					sh "${tool name: 'Maven', type: 'maven'}/bin/mvn clean compile"
				}
			}
		}
		stage('Test') {
			steps {
				echo 'Testing..'
				dir("${env.WORKSPACE}/app"){
					sh "${tool name: 'Maven', type: 'maven'}/bin/mvn clean package"
				}
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deployin....'
			}
		}
	}
}
