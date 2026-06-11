package com.example.studentdemojpa.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//MAKE SURE IT SAYS COLLECTION NOT collation
@Document(collection = "students")
public class Student {

    @Id
    private String id;

    @NotNull
//    @UniqueElements
    private String studentId;

    @NotBlank
    private String name;

    @Min(0)
    private int age;

    private Gender gender;

    private List<String> majors;

    private Integer graduationYear;
}
