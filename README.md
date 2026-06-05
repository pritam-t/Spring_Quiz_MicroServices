# Spring Quiz Microservices

A Quiz Management System built using Spring Boot Microservices architecture. The project demonstrates service discovery, API gateway routing, and inter-service communication using Spring Cloud components.

## Architecture

```text
Client
   |
   v
API Gateway
   |
   +-------------------+
   |                   |
   v                   v
Quiz Service    Question Service
        |
        v
 Eureka Server
```

## Services

### Quiz Service

* Create quizzes
* Retrieve quiz details
* Communicate with Question Service

### Question Service

* Manage questions
* Retrieve questions by category
* Provide questions for quiz generation

### API Gateway

* Central entry point for client requests
* Routes requests to appropriate services

### Server Registry

* Eureka Discovery Server
* Service registration and discovery

## Tech Stack

* Java
* Spring Boot
* Spring Cloud Gateway
* Eureka Server
* OpenFeign
* Spring Data JPA
* MySQL
* Maven

## Project Structure

```text
Spring_Quiz_MicroServices/
│
├── quiz-service/
├── question-service/
├── api-gateway/
├── server-registry/
└── README.md
```

## Running the Project

Start the services in the following order:

1. Server Registry
2. Question Service
3. Quiz Service
4. API Gateway

## Future Improvements

* JWT Authentication
* Docker Support
* Kubernetes Deployment
* Circuit Breaker Pattern
* Centralized Configuration

## Author

Pritam Thopate
