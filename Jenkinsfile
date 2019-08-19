node {    


       // more info about docker workflow https://documentation.cloudbees.com/docs/cje-user-guide/docker-workflow-sect-inside.html
       
       def image = docker.image('maven:3.3.3-jdk-8')
       image.pull() // make sure we have the latest available from Docker Hub
       image.inside('-u root') {
       	  checkout scm
       	  writeFile file: 'settings.xml', text: "<settings><localRepository>${pwd()}/.m2repo</localRepository></settings>"
       	  sh "mvn -version"
       	   stage 'compile parent pom'
        	    sh "mvn -f mylab-parent-pom/pom.xml -s settings.xml  clean install"
       			echo 'Compiled parent pom succesfully'
   
   		   stage 'run unit test mylab core'
     			sh "mvn -f mylab-core/pom.xml -s settings.xml test"
     			echo 'Unit test has passed succesfully'
     			stage 'run package mylab core '
     			sh "mvn -f mylab-core/pom.xml -s settings.xml install"
     			echo 'App has been packaged succesfully'
   
   		   stage 'run unit test rest layer'
     			sh "mvn -f spring-boot-mvc-rest/pom.xml -s settings.xml test"
     			echo 'Unit test has passed succesfully'
     			stage 'run package rest layer'
     			sh "mvn -f spring-boot-mvc-rest/pom.xml -s settings.xml  install"
     			echo 'App has been packaged succesfully'
     	    
     	   stage 'deploy app to develop server'
     	    	input message: 'Do you want to deploy your artifact to develop server?', ok: 'OK', submitter: 'admin'
     	    	echo 'App has been deployed to develop server'
     			//TODO DEPLOY TO SERVER XXXXX
      
       }
       
}