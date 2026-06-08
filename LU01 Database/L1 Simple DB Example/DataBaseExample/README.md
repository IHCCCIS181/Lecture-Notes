# Unit 1 DB Day 1

Today we will install mysql and build a simple project to create a db and a table

<!-- TODO rebuild L2 and then back port it to L1 so they line up better for more code reuse for L2. Make it kind of part 1 and 2 -->

## Install mySQL

Using docker / podman.

[How to Install mySQL](../../Install%20MySQL/Install%20MySQL%20Docker.md)

## Create the Project

You will want to install maven if you have not already: https://maven.apache.org/install.html

```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=DatabaseExample -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

### Import driver

You can get the driver .jar from your system (it is downloaded with mySQL) or you can use a package manager like Maven

- [Mavan Repo](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

```xml
<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.38</version>
    <scope>provided</scope>
</dependency>
```

- We also want to download Lombok to make our lives easier.
- We need to change the maven-compiler-plugin to use the annotation processor for lombok.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>1.18.38</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
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
