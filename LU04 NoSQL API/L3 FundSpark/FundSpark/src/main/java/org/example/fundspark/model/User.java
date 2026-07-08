package org.example.fundspark.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
