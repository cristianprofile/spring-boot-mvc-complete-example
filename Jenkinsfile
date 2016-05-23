
node { 
   
   
   
  def maven = docker.image('maven:3.3.3-jdk-8'); // https://registry.hub.docker.com/_/maven/

  stage 'Mirror'
  
  // First make sure the slave has this image.
  // (If you could set your registry below to mirror Docker Hub,
  // this would be unnecessary as maven.inside would pull the image.)
  
  maven.pull()
  // We are pushing to a private secure Docker registry in this demo.
  // 'docker-registry-login' is the username/password credentials ID as defined in Jenkins Credentials.
  // This is used to authenticate the Docker client to the registry.
  docker.withRegistry('https://docker.example.com/', 'docker-registry-login') {
   
   
   maven.inside('--privileged=true') {
   sh "java -version"
   

    
    }
    }

   
}
