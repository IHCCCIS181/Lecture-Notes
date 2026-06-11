package com.example.student_api.service;

import java.util.List;
import com.example.student_api.model.Student;

public interface StudentService {
    List<Student> findAll();
    Student findStudentById(int id) throws StudentNotFoundException;
    Student findStudentByName(String name) throws StudentNotFoundException;
}
