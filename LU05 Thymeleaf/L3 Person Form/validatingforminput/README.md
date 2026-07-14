# LU05 L3 Validation

## Create the Example

- Create a Spring Project called `validatingforminput`
- Dependencies `Spring Web`, `Thymeleaf`, `Validation`, and `Lombok`
- [Tutorial](https://spring.io/guides/gs/validating-form-input/)
- [Docs](https://www.geeksforgeeks.org/spring-boot-validation-using-hibernate-validator/)

## Run the Application

```bash
./mvnw spring-boot:run
```

## Lombok Update

- Added Lombok to `pom.xml`.
- Updated `PersonForm` to use Lombok annotations:
    - `@Data`
    - `@NoArgsConstructor`
    - `@AllArgsConstructor`
- Removed manual getters, setters, and `toString()` from `PersonForm`.
- Changed `age` from `int` to `Integer` so `@NotNull` validation is effective.

## Add Validation Message

In the `PersonForm` class, add a message to the `@Size` and `@Min` annotations.
Restart the application and test the validation messages in the browser.

EOD
