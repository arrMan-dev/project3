# project-registry-account-microservice
## Project Description
This is a microservice representation of the Registry Backend. This microservice contains Controller and Service methods for Account, none have which needed to be implemented for the most recent completed sprint.

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
* Communicate with the Account table within a AWS S3 database.
* CRUD operations for Account.
* Has API endpoints to connect to each service.

## To-do List:
* Create methods for the frontend login functionality.
* Create separate database structure for this microservice.
* Implement AOP Logging Aspect.
* Implement DTO methods for secure inputs to the api.

## Usage

## Running Locally
#### Reference the **Running Locally** section in https://github.com/Project-Registry-991-Kevin-Tran-Huu/project-registry-gateway and have it running before this service
* git clone https://github.com/Project-Registry-991-Kevin-Tran-Huu/project-registry-account-microservice.git
* Run this project in Eclipse or SpringToolSuite
* Do a Maven update to import the dependencies needed from the pom.xml
* Launch a Consul instance from a Docker container on port 8500
* Launch the Gateway service
* Launch this application using spring boot.
* This app will run at http://localhost:8081
