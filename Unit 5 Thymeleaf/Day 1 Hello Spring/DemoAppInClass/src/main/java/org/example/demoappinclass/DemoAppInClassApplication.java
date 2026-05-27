package org.example.demoappinclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoAppInClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAppInClassApplication.class, args);
    }

//    @GetMapping("/hello")
//    public String hello(){
//        return "<h1>Hello World</h1>" +
//                "<ul>" +
//                "<li>Item in list </li>" +
//                "</ul>";
//    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("<h1>Hello %s ! </h1> <p>more text</p>", name);
    }


}
