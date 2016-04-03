
de {
   
   git 'https://github.com/cristianprofile/spring-boot-mvc-complete-example.git'
   
   stage 'compile parent pom'
   sh "java -version"
   sh "mvn -f mylab-parent-pom/pom.xml  clean install"
   echo 'Compiled parent pom succesfully'
   
   stage 'run unit test mylab core'
   sh "mvn -f mylab-core/pom.xml test"
   echo 'Unit test has passed succesfully'
   stage 'run package mylab core '
   sh "mvn -f mylab-core/pom.xml  package"
   echo 'App has been packaged succesfully'
   
   stage 'run unit test rest layer'
   sh "mvn -f spring-boot-mvc-rest/pom.xml test"
   echo 'Unit test has passed succesfully'
   stage 'run package rest layer'
   sh "mvn -f spring-boot-mvc-rest/pom.xml  package"
   echo 'App has been packaged succesfully'  
   
}
