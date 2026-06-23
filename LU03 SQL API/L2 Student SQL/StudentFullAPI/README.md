# Custom Student API

<!-- Update project to be more consistent with id vs studentId -->

This project is a full API with a service layer.
This example is a custom example the follows the Students data scenario.

## Creating the Project

Using IntelliJ Spring boot creator or Spring Initializr (https://start.spring.io/) create a new project with the following settings:

- Artifact: studentdemojpa
- Project: Maven
- Dependency
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
    - Lombok
    - Validation

#### Import the following

Post

```curl
curl --location 'http://localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "Luke",
  "lastName": "Matheis",
  "email": "luke.matheis@indainhills.edu",
  "eContact": "123-456-7890",
  "student_id": 12345
}
'
```

Get

```curl
curl --location 'http://localhost:8080/adll'
```

PUT

```
curl --location --request PUT 'http://localhost:8080/update/2' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "billy2.bob2@indainhills.edu",
  "eContact": "123-456-3423"
}
'
```

DEL

```
curl --location --request DELETE 'http://localhost:8080/delete/20'
```

Get by Last name
``

###

GET http://localhost:8080/findByName/Matheis
``
