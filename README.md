# EventService
Spring Based API with events entities for micro services architecture with rabbitmq, mongo,swagger.
Second part of Employee service for events


## Requirements

* Gradle less than 5 (available in /gradle)
* Docker

### Run/Install: 

Initially main idea of project is to provide fast setup for producer-consumer spring based solutions with api's exchanged entities,
and run this solution in different profile modes.

In current setup all full loaded project will produce 4 docker containers connected in a their hub network network.

    1. Employee Service for producing messages and storing entities in Mongo.
    2. Event Service for receiving messages from queue and also storing them in Mongo
    3. Docker with MongoDb. no required authenticateion for mongo.
    4. Docker with RabbitMq.

Services  itself can run locally without any docker setups.
it requires two of  repositories, EmployeeService with EventService 
and run thru:
* ./gradle buildLocalContainers - for launching mongo and rabbitmq dockers 
* ./gradlew clean build run - for EmployeeService and  EventService running jars separately
but using "run" default profile will be profile: = -Dspring.profiles.active=dev, when mongo and rabbitmq will be a part of local network.
while regular profile will connect to mongo:27777 and rabbitmq:15672


#### Option 1:

   * Verify you have mongodb on you local machine if not, you can use docker instead.
      
    ./gradlew buildLocalContainers 
       
   * Build your EmployeeService with with dev profile   
           
    ./gradle clean build run
    
   * Build your EventService    with with dev profile  
   
    ./gradle clean build run
        
   * run itself is part of application plugin and using   
        
    -Dspring.profiles.active=dev


#### Option 2:

    ./gradlew startDocker 


#### Invironment:
Big part of launching environment are different gradle tasks, for creating building and launching dockers and connect them into network.
event.sh will clone EventService and produce docker image for this service.
Available tasks:

   **getEventService** -  will build event container and connect to internal microservice network




#### Requests:
EventService exposed on 8081 port by default.


#### SWAGGER 

  http://localhost:8080/swagger-ui.html
  
  
#### Requests


**GET Events** 

    curl -X GET \
      http://localhost:8081/event/{employeeid} \
      -H 'cache-control: no-cache'

array of events
