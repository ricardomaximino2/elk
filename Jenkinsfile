pipeline {
	agent any
	
	stages {
		stage('Build') {
			steps {
				echo 'Building..'
				withMaven(maven : 'mvaven_3_6_3') {
					sh 'mvn clean package'
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
