package org.example.readonlystudentapi.service;

import org.example.readonlystudentapi.data.SeedData;
import org.example.readonlystudentapi.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final ArrayList<Student> people;

    public StudentServiceImpl() {
        SeedData sd = new SeedData("src/main/java/org/example/readonlystudentapi/data/students.json");
        this.people = sd.getPeople();
    }

    @Override
    public List<Student> findAll() throws NoStudentDataException {
        if (people.isEmpty()) {
            throw new NoStudentDataException();
        }
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
