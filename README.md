## coding-challenge-nordea
Repository containing a coding challenge project

### Goal
Project shows usage of Spring Boot, Spring Batch, Spring Data with HSQL, Spring Cache and REST services.

### Workflow
On startup application creates database structure (table Person) and populates it with some random data.

Application also exposes two endpoints: 
```
GET /persons
POST /request/send
```

First one is used just to validate that data coming from the DB is correct.

Second endpoint's role is to send a message on JMS queue. When JMS consumer receives the message, it triggers the Batch Job start.
While executing the Job, in Reader item data is pulled from repository through service and put into Cache.

### Tests
Two simple test were added. 
First verifies the Person data, second verifies if the Response for POST trigger is of correct status and body.

### Additional remarks
By default DEBUG logs are enabled for the app so user can see the flow in details as well as notice elapsed time reduction due to Cache usage.

### Contact

michal.bladowski@gmail.com

https://github.com/michalbladowski
