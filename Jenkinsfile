
pipeline {
    agent any
    
    environment {
        PATH = "/opt/gradle/gradle-4.7/bin:/opt/vagrant:$PATH"
        RESOURCE_SERVER = '192.168.56.100'
        RESOURCE_SERVER_ROOT = '/home/user/resources'
        PHANTOMJS_PATH = '/home/patrick/Downloads/phantomjs-2.1.1-linux-x86_64/bin/phantomjs'
        
        
    }
    
    stages {
		stage('Preparation') {
			steps {
	   		 git(url: 'https://github.com/ediordna/parcelSizeComponent', branch: 'master')
			}
		}
		stage('Build') {
			steps {
				sh 'gradle clean unitTest fatJar' //gradle conducts build.gradle with the tasks unitTest and fatJar
	  				withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'ressourceServerCredentials', \
	                                             keyFileVariable: 'RESOURCE_SERVER_KEY', \
	                                             usernameVariable: 'RESOURCE_SERVER_USER')]) {
	                     		sh './scripts/build_docker.sh'  
	                	}
	         }
	    }
	    stage('Integration') {
	        steps {
	        		withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'ressourceServerCredentials', \
                                             keyFileVariable: 'RESOURCE_SERVER_KEY', \
                                             usernameVariable: 'RESOURCE_SERVER_USER')]) {
                    			sh './scripts/setup_test_env.sh' //behind this script is a second one to build the test containers
						}
	            sh 'gradle integrationTest' //gradle conducts build.gradle with the task integrationTest
	        }

	    }
        stage('UAT') {
            steps {
                sh 'gradle UATest'
            }
		}
		stage('Performance') {
        	steps {
                sh 'gradle gatlingRun'
        	}
		}
		stage('Manual testing') {
        	steps {
        		input(message: 'Release ready?')
        	}
		}
     }
     post {
         always {
    		sh './scripts/cleanup_test_env.sh'
     		sh './scripts/cleanup_workspace.sh'
     		junit('**/build/test-results/unitTest/*.xml,**/build/test-results/integrationTest/*.xml')
     		gatlingArchive() //creates nice charts
     	}
     }
     
}

