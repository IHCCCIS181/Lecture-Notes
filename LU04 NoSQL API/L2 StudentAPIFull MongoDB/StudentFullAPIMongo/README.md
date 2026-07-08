# Part 2 MongoDB Day 2

This project is a straight copy of the MySQL project with some notable changes.

## Dependency

In your `pom.xml` file:

- Remove JPA and MySQL Driver Dependencies
- Add Data MongoDB

## Model Changes

- Some attributes have the same name, make sure they are from spring
    - Change the attributes to use the ones from mongo
- Change model `id` to be a string. And change this through the project.
- Update the `StudentUpdateDTO` remove extra annotations.

## Database

Make a database with MongoDb Compass with the name `SpringStudents` and a collection with the name `students`.

## Repository

Change to use `MongoRepository` instead of `JpaRepository`. Also change the type of the id to be a string.

## Update Service and Imp

Anywhere with a int/long id, change to string.

## Controller

Change to use `@RestController`
Update again anywhere with a int/long id, change to string.

## Config

```
spring.data.mongodb.uri=mongodb://localhost:27017/SpringStudents
```

> I would like it in the future the app to create the database and collection if they don't exist. But for now, we will create them manually.
