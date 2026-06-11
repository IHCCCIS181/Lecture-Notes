package com.example.student_api.data;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import com.example.student_api.model.Student;

@Getter
public class SeedData {
    private ArrayList<Student> people;

    public SeedData(String filePath) {
        this.people = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Student>>(){}.getType();
        try{
            this.people = gson.fromJson(new FileReader(filePath), listType);
        }catch(FileNotFoundException e){
            System.out.println("File not found: " + filePath);

        }
    }
}
