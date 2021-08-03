# project-registry-tracking-microservice

## Project Description
This is a microservice representation of the Registry Backend. This microservice contains Controller and Service methods for Iteration, Phase, and Status.

## Technologies Used
* Java 8
* Spring Boot
* Spring MVC
* Spring AOP
* Spring Data
* JUnit
* Mockito
* AWS S3
* PostgreSQL
* Jenkins

## Features
* Communicate with the Iteration, Phase, and Status tables within a AWS S3 database.
* CRUD operations for Iteration, Phase, and Status.
* Has API endpoints to connect to each service.

## To-do List:
* Create separate database structure for this microservice.
* Finish Implementing AOP Logging Aspect.
* Create Service methods for controller methods utilizing DTO. 

## Usage
* This application serves as an API for the Registry frontend.
* Entering the base endpoints into a web browser will process a get request from the api. Returning a full list of the respective database entities. 
  - Iteration endpoint: /api/iteration
  - Phase endpoint: /api/phase
  - Status endpoint: /api/status
* Adding "/id/{id}" to a base endpoint retrieves a specific entity object by its id.
* Other API calls require execution from the front end or from a testing application like Postman.

## Running Locally
#### Reference the **Running Locally** section in https://github.com/Project-Registry-991-Kevin-Tran-Huu/project-registry-gateway and have it running before this service
* git clone https://github.com/Project-Registry-991-Kevin-Tran-Huu/project-registry-tracking-microservice.git
* Run this project in Eclipse or SpringToolSuite
* Do a Maven update to import the dependencies needed from the pom.xml
* Launch a Consul instance from a Docker container on port 8500
* Launch the Gateway service
* Launch this application using spring boot.
* This app will run at http://localhost:8083
