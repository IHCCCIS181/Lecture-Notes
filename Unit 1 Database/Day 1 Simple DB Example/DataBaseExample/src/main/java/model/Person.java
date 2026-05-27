package model;

import lombok.*;

/**
 * This is an instantiable class that contains fields about a model.Person
 * @author Luke Matheis
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    //TODO ADD AN ID
    protected String name;
    protected int age;
    protected char gender;
}