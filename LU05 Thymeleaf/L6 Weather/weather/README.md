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
- Add Playwright manually to the `pom.xml` file.
    - https://mvnrepository.com/artifact/com.microsoft.playwright/playwright

## Getting Starred

Download the models from blackboard to get started.
We will build the rest together in class.

## Running the Application

Make sure Mongo is installed and is running on your machine.

```bash
./mvnw spring-boot:run
```
