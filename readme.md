##snel-transport
snel-transport is an Java EE webapp which uses the full stack: Java, Angular 2, Maven and PostgreSQL.

## Requirements for back end   

 - Java 8 (version: 1.8.0_101) 
 - Maven 3.3.9 
 - Apache Tomcat 8.5 (version: 8.5.5)

##Installation
Compiles the code and package it in its distributable format, such as a JAR en "-Dmaven.test.skip=true" zorgt ervoor dat hij de tests skipt. En "package" zorgt er tevens voor dat hij de dependencies download:

    mvn package -Dmaven.test.skip=true

## Tests
**EMBEDDED TOMCAT**
Ga naar de back_end directory om embedded Tomcat te runnen met de volgende command:

    mvn tomcat7:run

De web app draait dan als het goed is op poort 9090

Om alle tests in OrdersControllerTest te runnen:

    mvn -Dtest=OrdersControllerTest test  

Om testAddOrder() van OrdersControllerTest te runnen:

    mvn -Dtest=OrdersControllerTest#testAddOrder test


## Requirements for front end   
- Node v6.2.2 
- npm 3.10.2

##Installation
Install the modules by doing:

    npm install

To launch the application:

    npm start
