package org.example.model;

import lombok.Getter;
import lombok.ToString;
import java.util.ArrayList;

@Getter
@ToString
public class Student extends Person {
    protected ArrayList<String> major;

    public Student(String name, int age, char gender, ArrayList<String> major) {
        super(name, age, gender);
        this.major = new ArrayList<>();
        this.setMajor(major);
    }

    public Student() {
        this.major = new ArrayList<>();
    }

    public void setMajor(String major) {
        if (major != null) {
            this.major.add(major);
        }
    }

    public void setMajor(ArrayList<String> major) {
        if (major != null) {
            this.major.addAll(major);
        }
    }
}
