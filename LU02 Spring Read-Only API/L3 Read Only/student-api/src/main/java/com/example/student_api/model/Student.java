package com.example.student_api.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private ArrayList<String> majors;
    private int graduationYear;
}
