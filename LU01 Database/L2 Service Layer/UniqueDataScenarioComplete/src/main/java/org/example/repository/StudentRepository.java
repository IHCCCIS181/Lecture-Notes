package org.example.repository;

import org.example.model.*;
import java.util.List;

public interface StudentRepository {
    Person getPersonById(int personId);

    List<Graduate> getAllPeople();

    void insertPerson(Graduate person);

    void updatePerson(Graduate person);

    void deletePerson(int personId);
}
