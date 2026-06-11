package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.model.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    List<Student> findAll() throws NoStudentFoundException;
    void saveStudent(Student student);
    void deleteStudent(String studentID) throws NoStudentFoundException;
    void updateStudent(String studentID, StudentUpdateDto updatedStudent) throws NoStudentFoundException;
    List<Student> findStudentsByName(String name) throws NoStudentFoundException, StudentNameEmptyException;
}

