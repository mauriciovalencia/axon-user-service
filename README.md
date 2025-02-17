# Axon User Service

User API that serve CRUD endpoint actions for managment users

**Table of Contents**
- [Swagger](#markdown-header-swagger)
- [Environment requirements](#markdown-header-environment-requirements)
- [Environment Variables](#markdown-header-environment-variables)
- [Installation](#markdown-header-installation)
- [Docker](#markdown-header-docker) 
- [Execution](#markdown-header-execution)

## URL
```bash
# on localhost

# base url
http://localhost:8080

# url user-service
http://localhost:8080/user
```

## Swagger
```html
# on localhost

swagger-ui
http://localhost:8080/swagger-ui/index.html

open-api json specification
http://localhost:8080/v3/api-docs #

any option just installing google chrome extension  [ModHeader](https://modheader.com/)
<img width="1497" alt="image" src="https://github.com/user-attachments/assets/f166570b-6710-4ad8-b641-3a9283f15a13" />


or just 

inside the project
user_moduule: src/main/java/com/axon/userservice/modules/user/config/axon_user_service-openapi.yaml

```

## Environment requirements
```
- Java 17.0.14, vendor: Amazon.com Inc or similar
- Maven 3.8.4 or more
```

Then, on local environment case, just cloning the repo in your local environment, and set values
in the properties application.properties and described in detail in this document.

## Environment Variables

Operational

| Name           | Type   | Options | Default      | Description                |
|----------------|--------|---------|--------------|----------------------------|
| spring.application.name     | String    |         | user-service | Application name           |
| server.port  | String | 8080     | 8080          | Port server of application |

Functional

| Name                           | Type   | Options | Default                     | Description                                 |
|--------------------------------|--------|---------|-----------------------------|---------------------------------------------|
| cors.allowed-origins           | String |         | http://localhost:5173       | Clients that will consume this service      |
| cors.allowed-methods           | String |         | GET,POST,PUT,DELETE,OPTIONS | Global http methods allowed for each client |
| spring.security.user.name      | String |         | "must to be empty"          | Basic Spring security user                  |
| spring.security.user.password  | String |         | "must to be empty"          | Basic Sprint Security generated password    |
| user_service.api-key           | String |         | "nowayjose"                 | Api-Key for clients                         |

Business

| Name                           | Type   | Options | Default | Description                                            |
|--------------------------------|--------|---------|---------|--------------------------------------------------------|
| user_module.password-min-length           | Number |         | 8       | Minimal length pasword Example: Password1@             |
| user_module.min-age           | Number |         | 18      | Minimal user age for beign created in database service |


## Installation

```bash
# Step by step
git clone https://github.com/mauriciovalencia/axon-user-service.git

# copy .env values to .env execution
cp .env.example .env

# install web application libs
mvn clean install

# run application on local
mvn spring-boot:run
# or
# run application on docker
docker-compose build --no-cache && docker-compose up -d --force-recreate 

```

## Execution

```bash
# install or reinstall
mvn clean install

# run all test
mvn clean test

# run
mvn spring-boot:run
```

## Docker
```bash
# by docker run
docker build -t axon-user-service .
docker run axon-user-service

# by compose
docker-compose build --no-cache && docker-compose up -d --force-recreate
# or
docker-compose build && docker-compose up -d

```

## Future Improvements
- Make more tests, controller and validators
- implement custom exceptions and messages by errors to api consumer
- implement better logs
- implement feature toggle, for enable somethings like swagger-ui
- implement more functional tests from postman-cli
- implement change to another data-base
When?!, maybe the next 3 last versión incoming!.
