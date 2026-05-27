package model;

import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    protected ArrayList<String> major;

    public Student(String name, int age, char gender, ArrayList<String> major) {
        super(name, age, gender);
        this.major = major;
    }

    public String getMajors() {
        return String.join(",", major);
    }
}
