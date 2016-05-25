node {    
       docker.image('maven:3.3.3-jdk-8').inside('-u root') {
          sh "pwd"
       	  checkout scm
       	  sh "pwd"
       	  sh "mvn -version"
       	    stage 'compile parent pom'
       	  	  dir("mylab-parent-pom") {
        		sh "pwd"
        		sh "ls"
        	    sh "mvn clean install"
       			echo 'Compiled parent pom succesfully'
       		   }
   
   		   stage 'run unit test mylab core'
   			dir("mylab-core") {
     			sh "mvn test"
     			echo 'Unit test has passed succesfully'
     			stage 'run package mylab core '
     			sh "mvn package"
     			echo 'App has been packaged succesfully'
   				}
   
   		   stage 'run unit test rest layer'
     		dir("spring-boot-mvc-rest") {
     			sh "mvn test"
     			echo 'Unit test has passed succesfully'
     			stage 'run package rest layer'
     			sh "mvn package"
     			echo 'App has been packaged succesfully'
     	    }
     	    
     	   stage 'deploy app to develop server'
     	    input message: 'Do you want to deploy your artifact to develop server?', ok: 'OK', submitter: 'admin'
     	    echo 'App has been deployed to develop server'
     		//TODO DEPLOY TO SERVER XXXXX
      
       }
       
}