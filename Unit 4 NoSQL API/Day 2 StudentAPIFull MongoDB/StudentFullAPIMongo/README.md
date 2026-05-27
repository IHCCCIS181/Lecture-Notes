# Part 2 MongoDB Day 2

This project is a straight copy of the MySQL project with some notable changes.
I don't think we really need .env for this project (maybe for db port). 

## Dependency

Remove JPA
Add MongoDB

## Model Changes

- Some attributes have the same name, make sure they are from spring
    - Change the attributes to use the ones from mongo
- Change model `id` to be a string. And change this through the project.

## Database

Make a database with MongoDb Compass with the name `SpringStudents`


## Controller

change to use `@RestController`

## Config

```
spring.data.mongodb.uri=mongodb://localhost:27017/SpringStudents?createDatabaseIfNotExist=true
```

