# L3 RSS Feed

This project is a simple Thymeleaf web application that allows users to submit podcast episodes and view them in a feed.
It is technically (Kafka wise) no more complex then L2 example.

<!-- TODO change this project to use Jackson instead of Gson -->

## Create the Project

This project will be just one application.
Create it with the [Spring Initializr](https://start.spring.io/) and select the following options:

- Project Name: `ihcc-podcast`
- Dependencies:
    - Spring Web
    - Spring for Apache Kafka
    - Spring Boot DevTools
    - Lombok
    - Thymeleaf
    - Validation
    - Gson (Added manually to `pom.xml`)

## Building the Application

Students are given a starter project with the following files:

- `PodcastEpisode.java` - A simple Java class that represents a podcast episode.
- `feed.html` and `form.html` - Thymeleaf templates for the feed and form pages.

Then together build the `PodcastController.java` and `PodcastConsumer.java` classes to complete the application.

Make sure to add the following properties to `application.properties`:

```properties
spring.application.name=ihcc-podcast
server.port=8080
spring.kafka.bootstrap-servers=localhost:9092
```

## Running the Application

Make sure the Kafka server is running
In the directory of the docker compose file, run the following command to start the Kafka server:

```bash
docker compose up -d
```

Then to run the application with the following command in the project directory:

```bash
./mvnw spring-boot:run
```
