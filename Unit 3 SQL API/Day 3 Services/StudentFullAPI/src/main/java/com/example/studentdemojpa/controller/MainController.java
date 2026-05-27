package com.example.studentdemojpa.controller;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.model.StudentUpdateDto;
import com.example.studentdemojpa.service.NoStudentFoundException;
import com.example.studentdemojpa.service.StudentNameEmptyException;
import com.example.studentdemojpa.service.StudentService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class MainController {


    private final StudentService studentService;

    @Autowired
    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

//    https://www.jetbrains.com/help/objc/exploring-http-syntax.html#dynamic-variables

//    TODO add custom exceptions

    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        try{
            studentService.saveStudent(student);
            return new ResponseEntity<>("Saved", HttpStatus.CREATED);
        }catch(ConstraintViolationException ex){
            return new ResponseEntity<>("Invalid input, check your fields" + ex.toString(), HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentID) {
        try {
            studentService.deleteStudent(studentID);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>("No Student Found with Given Student ID", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("No Student ID Given", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json")
    public ResponseEntity<String> updateStudent(@PathVariable("id") long studentID, @RequestBody StudentUpdateDto updatedStudentDto) {
        try {
            studentService.updateStudent(studentID, updatedStudentDto);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (NoStudentFoundException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/findByName/{name}")
    @ResponseBody
    public ResponseEntity<?> findStudentsByName(@PathVariable("name") String name) {
        List<Student> students;
        try {
            students = studentService.findStudentsByName(name);
        } catch (NoStudentFoundException e) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        } catch (StudentNameEmptyException e) {
            return new ResponseEntity<>("Name can not be blank", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}



