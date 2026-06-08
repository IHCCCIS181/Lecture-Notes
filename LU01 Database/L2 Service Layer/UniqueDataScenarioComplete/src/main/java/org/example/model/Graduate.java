package org.example.model;

import lombok.Getter;
import lombok.ToString;
import java.util.ArrayList;

@ToString
@Getter
public class Graduate extends Student {
    private int graduation;

    public Graduate(String name, int age, char gender, ArrayList<String> majors, int graduation) {
        super(name, age, gender, majors);
        this.graduation = graduation;
    }

    public Graduate() {
        super();
    }

    public void setGraduation(String gYear) {
        this.graduation = Integer.parseInt(gYear);
    }

    public String getMajorsAsString() {
        return String.join(",", super.getMajor());
    }

}
