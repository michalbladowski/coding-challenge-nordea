## coding-challenge demo application
Repository containing a coding challenge project

### Goal
Project shows usage of Spring Boot, Spring Batch, Spring Data with HSQL, Spring Cache and REST services.

### Workflow
On startup application creates database structure (table Person in HSQL database) and populates it with some random data.

Application also exposes two endpoints: 
```
GET /persons
POST /request/send
```

First one is used just to validate that data coming from the DB is correct.

Second endpoint's role is to send a message on JMS queue. When JMS consumer receives the message, it triggers the Batch Job start.
Application has two implementations of Batch Job Step. First one (default) uses Tasklet 
to pull data from the repository through service and put into Cache.

Second implementation uses Reader item to do exactly the same.

### Tests
Two simple test were added. 
First verifies the Person data, second verifies if the Response for POST trigger is of correct status and body.

### How to run
Application makes use of embedded HSQL database so no manual application deployment is required.
Schema and initial data are loaded on application startup.

To build the application clone or download the project and run:
```
mvn clean install
```

Once the build is finished, run the application using following command:
```
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Now you can use described endpoints to trigger the process or read the PERSON table content.

### Additional remarks
By default DEBUG logs are enabled for the app so user can see the flow in details as well as notice elapsed time reduction due to Cache usage.

### Contact
```
michal.bladowski@gmail.com
https://github.com/michalbladowski
```
