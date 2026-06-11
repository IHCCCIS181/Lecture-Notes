package com.example.studentdemojpa.service;

import com.example.studentdemojpa.model.Student;
import com.example.studentdemojpa.model.StudentUpdateDto;
import com.example.studentdemojpa.repository.StudentRepository;
import jakarta.validation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Validator validator;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public List<Student> findAll() throws NoStudentFoundException {
        List<Student> students =  (List<Student>) studentRepository.findAll();
        if(students.isEmpty()){
            throw  new NoStudentFoundException("No Students found");
        }
        return students;
    }

    @Override
    public void saveStudent(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String studentId) throws NoStudentFoundException {
        if (studentId == null) {
            throw new IllegalArgumentException("Invalid ID provided.");
        }
        Optional<Student> s = studentRepository.findById(studentId);
        if (s.isEmpty()) {
            throw new NoStudentFoundException("Character not found.");
        }
        studentRepository.delete(s.get());
    }

    @Override
    public void updateStudent(String studentID, StudentUpdateDto updatedStudentDto) throws NoStudentFoundException {
        Optional<Student> existingStudent = studentRepository.findById(studentID);
        if (existingStudent.isEmpty()) {
            throw new NoStudentFoundException("Student not found.");
        }
        Set<ConstraintViolation<StudentUpdateDto>> violations = validator.validate(updatedStudentDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }
        existingStudent.ifPresent(student -> {
            student.setName(updatedStudentDto.getName());
            student.setGender(updatedStudentDto.getGender());
            studentRepository.save(student);
        });
    }

    @Override
    public List<Student> findStudentsByName(String name) throws StudentNameEmptyException, NoStudentFoundException {
        if (name == null || name.trim().isEmpty()) {
            throw new StudentNameEmptyException("Name must not be null or empty");
        }
        List<Student> students = studentRepository.findByName(name);
        if (students.isEmpty()) {
            throw new NoStudentFoundException("No student with that name found.");
        }
        return students;
    }

}
