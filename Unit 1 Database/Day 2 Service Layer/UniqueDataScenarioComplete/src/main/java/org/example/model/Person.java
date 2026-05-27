package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is an instantiable class that contains fields about a Person
 * @author Luke Matheis
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {

    protected int id;
    protected String name;
    protected int age;
    protected char gender;

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public void setGender(String gender) {
        this.gender = gender.charAt(0);
    }
}