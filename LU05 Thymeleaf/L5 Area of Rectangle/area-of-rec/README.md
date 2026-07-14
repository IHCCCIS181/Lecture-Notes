# LU05 L5 Using Playwright

This is the first Thymeleaf lecture not from a tutorial.
The goal is to reinforce core Thymeleaf concepts and introduce Playwright for testing.

## Create the Project

- Create a project using [Spring Initializer](https://start.spring.io/)
- Call the project `area-of-rec`
- Using the Following Dependency
    - Spring Web
    - Thymeleaf
    - Lombok
    - Validation
- Add Playwright manually to the `pom.xml` file.
    - https://mvnrepository.com/artifact/com.microsoft.playwright/playwright

## Build the project

-

## Run the project

```bash
./mvnw spring-boot:run
```

Run the Playwright tests

```bash
./mvnw test
```

On my system, the tests didn't work run headless.
