package org.example.service;

import org.example.model.Graduate;
import org.example.repository.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentRepository {
    private Connection connection;


    //establish the database connection
    public StudentService(String url, String username, String password, String dbName, String tableName) {
        try {
            this.connection = DriverManager.getConnection((url + dbName), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPerson(Graduate person) {
        String query = "INSERT INTO student (name, age, gender, major, graduation) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, String.valueOf(person.getGender()));
            preparedStatement.setString(4, person.getMajorsAsString());
            preparedStatement.setInt(5, person.getGraduation());
            preparedStatement.executeUpdate();

            // TODO simplify this I DON'T THINK I NEED THIS
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePerson(Graduate person) {
        String query = "UPDATE student SET name = ?, age = ?, gender = ?, major = ?, graduation = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, String.valueOf(person.getGender()));
            preparedStatement.setString(4, person.getMajorsAsString()); // Fix majors field
            preparedStatement.setInt(5, person.getGraduation()); // Use getGraduation method
            preparedStatement.setInt(6, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePerson(int personId) {
        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Graduate getPersonById(int personId) {
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPerson(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Graduate> getAllPeople() {
        List<Graduate> people = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                people.add(mapResultSetToPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    // helper method private and not part of interface
    private Graduate mapResultSetToPerson(ResultSet resultSet) throws SQLException {
        Graduate person = new Graduate();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getString("age"));
        person.setGender(String.valueOf(resultSet.getString("gender").charAt(0)));
        person.setMajor(resultSet.getString("major"));
        person.setGraduation(resultSet.getString("graduation"));
        return person;
    }

    // you do not need to close SQL connection, but it is best to do so
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
