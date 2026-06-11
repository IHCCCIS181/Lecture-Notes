# Read Only Student API

This project does not use any DB.

## Create Project

Using [Spring Initializr](https://start.spring.io/) or IntelliJ's built-in Spring Initializr, create a new Spring Boot project with the following settings:

- **Project**: Maven
- **Language**: Java
- **Name**: read-only-student-api
- Dependencies:
    - Spring Web
    - Lombok
    - Gson (Need to bring in manually)

Add the lombok plugin if it's not there.

Add the Gson dependency to your `pom.xml`:

```xml
<!-- Source: https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.13.2</version>
    <scope>compile</scope>
</dependency>
```

## Run the Application

```bash
./mvnw spring-boot:run
```

## Test the API

Build an `Test.http` in the root of the project with the following content.

## Consume the API

Show students the simple HTML/JS website that consumes the API and displays the student data.

[Example HTML/JS website](.front-end/index.html)
