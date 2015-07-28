Recipe-Server
===================

Run
---------------

You can run the server by call: `mvn tomcat7:run`

The server will run on: 
[http://localhost:9000/recipe-server](http://localhost:9000/recipe-server)

To test you cann call the url:
[http://localhost:9000/recipe-server](http://localhost:9000/recipe-server/recipe)

If no recipe has been added you should see an empty list (`[]`)

Internal Structure
-------------------

in `src/main/de/jd/webconfig` you will find the Spring configuration for the webapp 
(WebappInitializer and Dispatcher)

The RequestHandlers are exposed by `src/main/de/jd/recipe/RequestHandlerConfiguration.java`

Services are exposed by `src/main/de/jd/recipe/RecipeConfiguration.java`

Webapp Spring Configuration
---------------------------

The following configurations are provided:

+ Accept Json and XML