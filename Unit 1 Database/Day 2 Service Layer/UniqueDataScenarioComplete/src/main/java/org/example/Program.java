package org.example;

import org.example.service.StudentCreation;
import org.example.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

import org.example.model.*;

public class Program {

    public static String dbUrl = Optional.ofNullable(System.getenv("DB_URL")).orElse("jdbc:mysql://localhost:3306/");
    public static String dbUsername = Optional.ofNullable(System.getenv("DB_USERNAME")).orElse("root");
    public static String dbPassword = Optional.ofNullable(System.getenv("DB_PASSWORD")).orElse("root");
    public static StudentCreation sCreate = new StudentCreation();
    public static StudentService studentService = new StudentService(dbUrl, dbUsername, dbPassword, "STUDENTS", "student" );

    public static void main(String[] args) {
        //create db and table
        sCreate.Create(dbUrl, dbUsername, dbPassword, "STUDENTS", "student");

        //TODO add this major in one line
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("math");
        studentService.insertPerson(new Graduate("Joe", 19, 'M'
                , subjects , 2024));
        //TODO update a person

        studentService.getAllPeople().forEach(System.out::println);

        studentService.deletePerson(2);

        studentService.getAllPeople().forEach(System.out::println);
    }

    private static void updatePerson() {
        //TODO finish this method
    }
}
