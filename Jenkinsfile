
node { 
   
   
  def maven = docker.image('maven:3.3.3-jdk-8'); // https://registry.hub.docker.com/_/maven/

  stage 'Mirror'
  
  maven.pull()
  // make sure we have the latest available from Docker Hub
   
   
  maven.inside() {
     echo 'show java version'
     checkout scm
     sh 'mvn -Dmaven.repo.local=/m2repo -f app -B -DskipTests clean package'
     sh "java -version"
   
   }

   
}
