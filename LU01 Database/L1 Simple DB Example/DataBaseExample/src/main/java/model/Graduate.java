package model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Graduate extends Student {
    private int graduationYear;

    public Graduate(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;;
        this.major = new ArrayList<>();
    }

    public Graduate(String name, int age, char gender, ArrayList<String> major, int graduationYear) {
        super(name, age, gender, major);
        this.graduationYear = graduationYear;
    }
}
