package org.example.fundspark.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
public class User {

    @Id
    private Long id;

    @UniqueElements
    @Size(min = 3, max = 30)
    private String username;

//    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
}

