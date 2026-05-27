package org.example.readonlystudentapi.model;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int id;
    private String name;
    private int age;
    private Gender gender;
    private ArrayList<String> major;
    private int graduationYear;
    //TODO make second table with FK of courses.
}
