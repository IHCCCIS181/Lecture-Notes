package org.example.readonlystudentapi.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.readonlystudentapi.model.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SeedData {
    private ArrayList<Student> people;

    public  SeedData (String fileName) {
        this.people = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Student>>() {}.getType();
        try {
            this.people = gson.fromJson(new FileReader(fileName), listType);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + fileName);
            ex.printStackTrace();
        }
    }
}
