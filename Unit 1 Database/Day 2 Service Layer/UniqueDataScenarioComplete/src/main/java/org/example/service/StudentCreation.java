package org.example.service;

import org.example.data.ParseJSON;
import org.example.model.Graduate;

import java.sql.*;
import java.util.ArrayList;

public class StudentCreation {

    private Connection connection;
    private StudentService sService;

    public void Create(String url, String user, String pass, String dbName, String tableName) {
        sService = new StudentService(url, user, pass, dbName, tableName);
        createDB(url, user, pass, dbName);
        createTable(url, user, pass, dbName, tableName);
        seedPeople(
                "Unit 1 Database/Day 2 Service Layer/UniqueDataScenarioComplete/src/main/java/org/example/data/Students.json");
    }

    // TODO only create the table if it doesn't exists
    public void createTable(String url, String user, String pass, String dbName, String tableName) {
        try {
            connection = DriverManager.getConnection((url + dbName), user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement s;
        try {
            s = connection.createStatement();

            s.executeUpdate("DROP TABLE IF EXISTS " + tableName);
            String createStatement = ("CREATE TABLE student ("
                    + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "PRIMARY KEY (id),"
                    + "name VARCHAR(40), "
                    + "age INT, "
                    + "gender CHAR(1), "
                    + "major VARCHAR(225),"
                    + "graduation INT"
                    + ")");
            System.out.println(createStatement);
            s.executeUpdate(createStatement + " ENGINE = innoDB");
        } catch (SQLException e) {
            System.out.println("SQL error during DROP/CREATE");
            e.printStackTrace();
        }
    }

    // TODO only create the database if it doesn't exists
    public void createDB(String url, String user, String pass, String dbName) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            Statement stmt = connection.createStatement();
            String sql;
            sql = "Drop DATABASE IF EXISTS " + dbName;
            stmt.executeUpdate(sql);
            sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database made.");
        } catch (SQLException e) {
            System.err.println("Cannot create database.");
            System.err.println(e.getMessage());
        }
    }

    private boolean isTableEmpty() {
        try {
            String query = "SELECT COUNT(*) FROM student";
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    return rowCount == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void seedPeople(String filename) {
        if (!isTableEmpty()) {
            System.out.println("Table not empty. Skipping seeding.");
            return;
        }
        ParseJSON parseJSON = new ParseJSON(filename);
        ArrayList<Graduate> people = parseJSON.getPeople();
        for (Graduate p : people) {
            sService.insertPerson(p);
        }
    }

}
