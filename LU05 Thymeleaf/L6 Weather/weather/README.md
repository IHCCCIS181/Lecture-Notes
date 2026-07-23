# LU05 Weather App

This is the first Thymeleaf project that will connect to a database.
This is a larger example to kinda put it all together.

This project has two sides, the API only admin side and the read only user side. The admin side is where we will add, edit, and delete weather data. The user side is where we will display the weather data.

## Creating the Project

Use the [Spring Initializr](https://start.spring.io/) to create a Spring Boot project with the following dependencies:

- Name the project `weather`.
- [Spring Data MongoDB](https://docs.spring.io/spring-boot/3.4.1/reference/data/nosql.html#data.nosql.mongodb)
- [Thymeleaf](https://docs.spring.io/spring-boot/3.4.1/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
- [Validation](https://docs.spring.io/spring-boot/3.4.1/reference/io/validation.html)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.1/reference/web/servlet.html)
- [Lombok](https://projectlombok.org/)
- Add Playwright manually to the `pom.xml` file.
    - https://mvnrepository.com/artifact/com.microsoft.playwright/playwright

## Getting Starred

Download the models from blackboard to get started.
We will build the rest together in class.

- Build the Repository layer
- Build the Service layer
- Build the Controller layer
- Check out the Thymeleaf templates

## Running the Application

Make sure Mongo is installed and is running on your machine.
Then run the following command to start the application:

```bash
./mvnw spring-boot:run
```

Go to [http://localhost:8080/](http://localhost:8080/) to see the user side of the application.
And use the [http](./src/test/java/org/example/weather/IntergrationTesting.http) tests to see the admin side of the application.
