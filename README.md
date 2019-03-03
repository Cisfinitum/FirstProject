
Tours & Travel Agency
=====================

The project represents a website, which offers customers different tours all around the world

### User performs following functions:

   * registration
   * logging in
   * choosing an offer and making an order
    

### Admin performs following functions:
   * logging in
   * adding new offers to catalogues
   * deleting offers


### Getting Started
 
1 Clone or download this repository

> git clone https://github.com/Cisfinitum/FirstProject.git
>
2 Build project
  > gradle build
  >
Or if you did not download Gradle you can use wrapper
> gradle wrapper --gradle-version 5.1 
>
> gradle build
>
3 Start Server
 
copy .war file from FirstProject/Build/libs to a directory %CATALINA_HOME%\webapps (example: C:\tomcat\webapps)
>%CATALINA_HOME%\bin\startup.bat
>
See it up and running on http://localhost:8080


#### Also you can use IntelliJ IDEA

* File -> new -> Project from version control -> https://github.com/Cisfinitum/FirstProject.git
* Build project
* Run Server:

1. Using local Tomcat: 
    1. Go to: Add Configuration -> create new configuration -> Tomcat Server -> Local 
    2. Use path to directory with Tomcat in Application Server field
    3. In Deployment tab add war file  
2. Using  Tomcat plugin:
    
     1. Add to build.gradle
     
```groovy
apply plugin: 'com.bmuschko.tomcat' 

dependencies {
    def tomcatVersion = '9.0.1'
    tomcat "org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}",        
    "org.apache.tomcat.embed:tomcat-embed-logging-juli:9.0.0.M6",
    "org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}"
    }
    tomcat {
    httpProtocol = 'org.apache.coyote.http11.Http11Nio2Protocol'
    ajpProtocol  = 'org.apache.coyote.ajp.AjpNio2Protocol'
    }
```
ii. Use tomcatRun task
