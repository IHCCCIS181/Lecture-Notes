# Part 2 MySQL Day 1

This example follows [this tutorial](https://spring.io/guides/gs/accessing-data-mysql/)

I went a little to far trying to make this project perfect. Maybe just stick to the example. 

## Create the project

- Use Spring Initializr and call it `accessingdatamysql`
- Use the following Dependencies
    - Spring Web
    - Spring Data JPA
    - MySQL Driver.

## The DB

### Creating The DB

- Using MySQL create a DB called `springJPA`
- We will be just using root user
    - DO NOT DO THIS IN PRODUCTION

### Config the Project

We need to change a few things to get up and running in `application.properties`

- Without these changes, our project will use [H2](https://www.h2database.com/html/main.html) this is an in memory
  database.

### .env

Make sure to make a file called .env

```
DB_URL=jdbc:mysql://localhost:3306/
DB_USERNAME=root
DB_PASSWORD=root
```

Complete example:

```
spring.jpa.hibernate.ddl-auto=update
# reads vars from .env
spring.config.import=optional:file:.env[.properties]
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/springJPA?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true shows in logs
```

## Integration Testing

Look at [this](src/test/java/org/example/accessingdatamysql/IntegrationTesting.http) file to test out the controller endpoints. 
