# First Kafka Example

The Producer and the Consumer are two separate applications that communicate with each other through Kafka.
The Producer sends messages to a Kafka topic, and the Consumer reads messages from that topic.

## Creating the Projects

Create two projects with [Spring Initializr](https://start.spring.io/).
Both will use the same dependencies:

- Spring Web
- Spring for Apache Kafka
- Spring Boot DevTools
- Name one project `kafka-producer` and the other `kafka-consumer`.

---

## Set Up Kafka

If you cleaned up your Docker environment since last lecture. Please recreate the Kafka container with the the compose file linked [here](https://github.com/IHCCCIS181/Lecture-Notes/blob/main/LU06%20Kafka/L1%20Intro/docker-compose.yml).

---

## App 1: The Producer

Writes the messages

### Configuration

Set `application.properties` to:

```
spring.application.name=kafka-producer
spring.kafka.bootstrap-servers=localhost:9092
```

### Java Code

This project is very simple.
One file including one REST endpoint that sends a message to the Kafka topic.
The endpoint is passed a message in as a query parameter.

```java
package com.example.kafka_producer;

@SpringBootApplication
@RestController
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public String sendMessage(@RequestParam("msg") String message) {
        // Sends the message to the topic named "test-topic"
        kafkaTemplate.send("test-topic", message);
        return "Sent: " + message;
    }
}

```

### Run the Producer

```bash
cd kafka-producer
./mvnw spring-boot:run
```

Then open your browser and go to:

```
http://localhost:8080/send?msg=HelloSpring
```

You can see the message in the Kafka Web UI at [http://localhost:8010](http://localhost:8010).
Go to Local > Topics > test-topic > Messages to see the message you just sent.

---

## App 2: The Consumer

Listens to Messages and displays them in the console.

### Configuration

In `application.properties`

To prevent port conflicts, we tell this app **not** to start a web server.

```
spring.application.name=kafka-consumer
# Disable web server to avoid port 8080 conflicts
spring.main.web-application-type=none

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-group-id
# Start reading from the beginning if no offset is saved
spring.kafka.consumer.auto-offset-reset=earliest
```

### Java Code

`ConsumerApplication.java`

```java
package com.example.kafka_consumer;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @KafkaListener(topics = "test-topic", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("\nRECEIVED MESSAGE: " + message);
    }
}
```

### Test the Consumer

The producer doesn't need to be running for the consumer to work.

**This is a huge advantage of Kafka.
Unlike an HTTP request, the producer doesn't need to be running for the consumer to receive messages.**

While your Kafka Docker container is running, open a new terminal and run the consumer:

```bash
cd kafka-consumer
./mvnw spring-boot:run
```

You should see all new messages.
Note only new messages.
This is due to the fact that the consumer is part of a group and Kafka keeps track of which messages have been read by each group.

EOD
