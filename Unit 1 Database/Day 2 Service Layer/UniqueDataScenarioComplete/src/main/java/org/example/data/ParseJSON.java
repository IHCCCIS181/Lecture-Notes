package org.example.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.model.Graduate;


import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ParseJSON {

    private ArrayList<Graduate> people;

    public  ParseJSON (String fileName) {
        this.people = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Graduate>>() {}.getType();
        try {
            this.people = gson.fromJson(new FileReader(fileName), listType);
        } catch (Exception ex) {
            System.err.println("Parsing error: " + ex.getMessage());
        }
    }
}
