# Into to Spring REST API

This project works as an intro into building an API using Spring.
We will also be working with JPA and using an in-memory H2 database to store our data.
Use the Spring REST API tutorial: https://spring.io/guides/tutorials/rest

## Creating Project

Using IntelliJ Spring boot creator or Spring Initializr (https://start.spring.io/) create a new project with the following settings:

- Artifact: payroll
- Project: Maven
- Dependency
    - Spring Web
    - Spring Data JPA
    - H2 Database

> Don't complete full guide stop after controller

You will need to change this endpoint to remove custom exception.

```java
@GetMapping("/employees/{id}") Employee one(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow();
}
```

Run the application. Run `./`.

## Testing the API

First show off Curl commands to test the API endpoints.
Then using `.http` files we can test our API endpoints.
IntelliJ has a built in HTTP client.
VSCode you will need to install the [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) extension.

> Go to [`test.http`](test.http) to see tests.

## Changing Project to Lombok

If we have extra time in lecture.
To make our code cleaner we will rework the `pom.xml` and `Employee.java` files to use Lombok.

1. Add the following dependency to your `pom.xml` file:

```xml
<!-- Source: https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.42</version>
    <scope>compile</scope>
</dependency>
```

2. Add the following plugin configuration to your `pom.xml` file:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.42</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

3. Remove the getters, setters and ToString from your `Employee.java` file and add the following annotations:

- [Finished `Employee.java` file](src/main/java/com/example/payroll/Employee.java)
