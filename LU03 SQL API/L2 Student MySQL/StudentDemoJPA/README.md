# Part 2 MySQL Day 2

This example is a custom example and follows the Student data scenario.

## Create the project

- Use Spring Initializr and call it `StudentDemoJPA`
- Use the following Dependencies
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
    - Lombok

## The DB

### Create a .env file

### Config the Project

We need to change a few things to get up and running in `application.properties`

- without these changes, our project will use [H2](https://www.h2database.com/html/main.html) this is an in memory
  database.
- change the data source url to your db name. Leave the `${MYQL_HOST:localhost}` alone
- Change username and password to your own

Complete example:

```
spring.jpa.hibernate.ddl-auto=create
spring.config.import=optional:file:.env[.properties]
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/SpringStudents?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## Build the Project

## Talk to the Project

[http](src/test/java/com/example/studentdemojpa/IntegrationTesting.http)
