# recipe-website
This is a repository to learn some technologies and integrate them in web applications

Developed on Linux, not tested on other environments.

## General Structure

In [dev](dev/README.md) you will find all the Software Development stuff including webapps and apache configurations.

In [docker](docker/README.md) you will find all deployment related stuff to setup the example webapp as testsystem with docker.

## Quickstart: Setup testsystems (not tested in fresh environment)

Precondition: Java, Maven and Docker are installed

1. Go to [dev](dev/README.md) and compile  `mvn clean install`
2. Go to [docker](docker/README.md) and build containers in the order below
   1. Go to [docker/ssh](docker/ssh/README.md) and run `sudo ./build.ssh`
   2. Go to [docker/examplewebapp](docker/examplewebapp/README.md) and run `sudo ./build.sh`
   3. Go to [docker/test-system](docker/test-system/README.md) and run scripts insid
      1. `sudo ./docker-build.sh`
      2. `sudo ./docker-run.sh` **CAUTION: Your /etc/hosts will be manipulated to provide access to the docker container via apache**

## Used Frameworks and Technologies

### Software Development

#### Vertical - Integrated in all layers of the webapp
+ Java
+ Maven
+ Spring MVC, Dependency Injection
+ Logging (slf4j)

#### Horizontal - Integrated in horizontal layers

##### Backend

+ Mongo DB
+ REST

##### Frontend

+ Currently JSP (planning migration to freemarker)
+ SASS
+ Twitter Bootstrap
+ Grunt
+ NPM

###Deployment
+ Maven
+ Apache
+ Tomcat
+ Docker
+ Shell
+ Unix
