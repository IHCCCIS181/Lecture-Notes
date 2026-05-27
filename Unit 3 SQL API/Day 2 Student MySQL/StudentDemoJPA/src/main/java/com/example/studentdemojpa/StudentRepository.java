package com.example.studentdemojpa;

import com.example.studentdemojpa.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
