package org.example.readonlystudentapi.service;

import org.example.readonlystudentapi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll() throws NoStudentDataException;
    Student findStudentById(int id) throws StudentNotFoundException;
    Student findStudentByName(String name) throws StudentNotFoundException;
}
