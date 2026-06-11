package com.example.studentdemojpa;

import com.example.studentdemojpa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class StudentController {

    //https://www.baeldung.com/java-spring-field-injection-cons
    @Autowired
    private StudentRepository sRepository;

    @PostMapping(path = "/add", consumes = "application/json")
    public @ResponseBody ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        sRepository.save(student);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        Iterable<Student> students = sRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
