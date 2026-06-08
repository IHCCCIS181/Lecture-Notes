import model.Graduate;
import model.Person;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    static Connection conn = null;

    //Change to your password and username
    //Make sure this is your port number
    public static String dbUrl = Optional.ofNullable(System.getenv("DB_URL")).orElse("jdbc:mysql://localhost:3306/");
    public static String dbUsername = Optional.ofNullable(System.getenv("DB_USERNAME")).orElse("root");;
    public static String dbPassword = Optional.ofNullable(System.getenv("DB_PASSWORD")).orElse("root");;

    public static ArrayList<Graduate> students;

    public static void main(String[] args) {

        //  Do this first time only.
        createDatabase();

        //  Do this after first time to connect to the database
        //	loadDriver();		//no need to do this anymore, happens automatically
        connect();

        // Do SQL command to create the table. Will Drop and Create if necessary.
        createTable();

        //Fill table with data
        seedTable();

        // Example of statement object with execute method for an SQL SELECT statement. Uses ResultSet.
        selectAll();
    }

    private static void seedTable() {
        //TODO - simplify the way to declare and add students to the list.
        students = new ArrayList<>();
        students.add(new Graduate("Betty", 23, 'F'));
        students.add(
                new Graduate(
                        "Bob",
                        22,
                        'M',
                        new ArrayList<>(List.of("Computer Software Development", "Robotics")),
                        2017
                )
        );
        students.add(
                new Graduate(
                        "Jane",
                        27,
                        'M',
                        new ArrayList<>(List.of("Math")),
                        2017));
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement("INSERT INTO STUDENTS (name, age, gender, major, graduation) VALUES (?, ?, ?, ?, ?)");
            for(Graduate student : students){
                ps.setString(1, student.getName());
                ps.setInt(2, student.getAge());
                ps.setString(3, String.valueOf(student.getGender()));
                ps.setString(4, String.join(",", student.getMajor()));
                ps.setInt(5, student.getGraduationYear());
                ps.executeUpdate();
                conn.commit();
            }
        }catch(SQLException e){
            System.out.println("Error seeding table");
            System.exit(1);
        }
    }


    /**
     * Run this method the first time to create a database.
     * You must have mySQL server running.
     */
    private static void createDatabase() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Connection made.");
        } catch (Exception e) {
            //serr how to type out error out
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "Drop DATABASE IF EXISTS STUDENTS";
            stmt.executeUpdate(sql);
            sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database made.");
        } catch (SQLException e) {
            System.err.println("Cannot create database.");
            System.err.println(e.getMessage());
        }
    }


    private static void connect() {
        try {
            conn = DriverManager.getConnection(dbUrl + "STUDENTS", dbUsername, dbPassword);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Create a table if it doesn't exist.
     * Using statement with executeUpdate method.
     */
    private static void createTable() {
        Statement s;
        try {
            s = conn.createStatement();

            conn.setAutoCommit(false);
            s.executeUpdate("DROP TABLE IF EXISTS student");
            String sql = """
                CREATE TABLE STUDENTS (
                    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                    PRIMARY KEY (id),
                    name VARCHAR(40),
                    age INT,
                    gender CHAR(1),
                    major VARCHAR(255),
                    graduation INT
                );
            """;
            System.out.println(sql);
            s.executeUpdate(sql);

            // some engine don't do rollbacks
            // some versions of mySQL use MyISAM as the default engine.

            conn.commit(); // Used since autocommit is turned off.


            conn.setAutoCommit(true); // turn autocommit back on.
            System.out.println("Table created.");
        } catch (SQLException e) {
            System.out.println("SQL error during DROP/CREATE");
            e.printStackTrace();
        }
    }

    private static void selectAll() {
        Statement s;
        try {
            s = conn.createStatement();

            s.execute("SELECT * FROM student");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                String majorString = rs.getString("major");
                ArrayList<String> majors = new ArrayList<>();
                if (majorString != null) {
                    majors.addAll(Arrays.asList(majorString.split(",")));
                }
                Person myPerson = new Graduate(
                            rs.getString("name"),
                            Integer.parseInt(rs.getString("age")),
                            rs.getString("gender").charAt(0),
                            majors,
                            rs.getInt("graduation")
                    );
                System.out.println(myPerson);
            }

        } catch (SQLException e) {
            System.out.println("SQL error during SELECT");
            e.printStackTrace();
        }
    }
}
