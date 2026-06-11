package com.example.demoapplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//https://spring.io/quickstart

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //Put http://localhost:8080/hello?name=bob in browser
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("<h1>Hello %s!</h1><p>more text</p>", name);
//    }

    //Do this more simple version first
        @GetMapping("/hello")
        public String hello() {
            return "hello world";
        }
}