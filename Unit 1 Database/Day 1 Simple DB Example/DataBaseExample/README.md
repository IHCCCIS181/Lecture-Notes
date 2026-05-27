# Unit 1 DB Day 1

Today we will install mysql and build a simple project to create a db and a table

## Install mySQL

### Linux

Run the following commands

#### Install

Using docker / podman is most easy

<!-- ```
$ sudo setenforce 0
``` -->

### Create the Project

You will want to install maven if you have not already: https://maven.apache.org/install.html

```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=DatabaseExample -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

### Import driver

You can get the driver .jar from your system (it is downloaded with mySQL) or you can use a package manager like Maven

- [Mavan Repo](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Model

User model from prev examples.

- [model.Person.java](src/main/java/Person.java)
- [model.Student](src/main/java/Student.java)
- [model.Graduate](src/main/java/Graduate.java)

### Build Main Example

How to run

```bash
mvn clean compile exec:java -Dexec.mainClass="Main"
```

or Maybe, if you set the package.

```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.Main"
```

## EOD
