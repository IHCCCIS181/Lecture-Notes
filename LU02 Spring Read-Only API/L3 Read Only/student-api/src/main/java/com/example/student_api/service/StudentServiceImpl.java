package com.example.student_api.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.student_api.data.SeedData;
import com.example.student_api.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final ArrayList<Student> people;

    public StudentServiceImpl() {
        SeedData sd = new SeedData("src/main/java/com/example/student_api/data/students.json");
        this.people = sd.getPeople();
        for (Student s : people) {
            System.out.println(s.getName());
        }
    }

    @Override
    public List<Student> findAll() {
        return people;
    }

    @Override
    public Student findStudentById(int id) throws StudentNotFoundException {
        return people.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student findStudentByName(String name) throws StudentNotFoundException {
        return people.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);
    }

}
