Recipe-Site Java Implementation
===============================

The Recipe-Site Java Webapplication contains two webapps:

+ recipe-server
+ recipe-website

Thirdparty applications which has to be installed
-------------------------------------------------
+ mongodb [https://www.mongodb.org/](https://www.mongodb.org/)

Running the components
----------------------------------------------------

Recipe-Server:
    `mvn tomcat7:run -f recipe-server/pom.xml`
    
Recipe-Website:
    `mvn tomcat7:run -f recipe-website/pom.xml`

Recipe-Server-Demodata:
    `mvn tomcat7:run -f recipe-server-demodata/pom.xml`
    
Website URL:
[http://localhost:8090/recipe-website](http://localhost:8090/recipe-website)

Server URL:
[http://localhost:9000/recipe-server](http://localhost:9000/recipe-server)

Recipe Server Demodata URL:
[http://localhost:9010/recipe-server-demodata](http://localhost:9010/recipe-server-demodata)

More informations
--------------------------------------------------

[recipe-server](recipe-server/README.md)

[recipe-website](recipe-website/README.md)

[recipe-server-demodata](recipe-server-demodata/README.md