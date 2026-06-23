package com.example.studentdemojpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "students")
// TODO swap this with @Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    @NotNull
    // @UniqueElements
    private String studentId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(0)
    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "student_majors",
            // IDK it can't find the id it is there.
            joinColumns = @JoinColumn(name = "id"))
    private List<String> majors;

    @Column(name = "graduation_year")
    private Integer graduationYear;
}
