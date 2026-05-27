package com.example.studentdemojpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ElementCollection
    @CollectionTable(
            name = "student_majors",
//            IDK why JetBrains can't find the id it is there.
            joinColumns = @JoinColumn(name = "id")
    )
    private List<String> majors;

    @Column(name = "graduation_year")
    private Integer graduationYear;
}
