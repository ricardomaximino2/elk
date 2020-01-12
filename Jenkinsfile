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
					sh "${tool name: 'Maven', type: 'maven'}/bin/mvn test"
				}
			}
		}
		stage('Build docker image') {
			steps {
				echo 'Build docker image....'
				sh 'dir'
				sh 'docker build -f app/docker/Dockerfile -t ricardomaximino/app:local --build-arg=NAME=app --build-arg=VERSION=local .'
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deployin....'
			}
		}
	}
}
