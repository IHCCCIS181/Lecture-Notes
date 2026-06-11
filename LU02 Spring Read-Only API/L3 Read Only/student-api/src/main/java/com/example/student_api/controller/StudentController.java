package com.example.student_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.service.StudentNotFoundException;
import com.example.student_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student_api.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // localhost:8080/api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // localhost:8080/api/students/{id}
    // localhost:8080/api/students/1
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        try {
            Student student = studentService.findStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // localhost:8080/api/students/name/{name}
    // localhost:8080/api/students/name/luke
    @GetMapping("name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        try {
            Student student = studentService.findStudentByName(name);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
