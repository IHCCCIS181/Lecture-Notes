package org.example.fundspark.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "fundraiser")
public class Fundraiser {

    @Id
    private String id;

    private String title;

    @Length(max = 500)
    private String description;

    @NotBlank(message = "Must be logged in to make fundraiser")
    private User owner;

    private Date startDate;

    private Date endDate;

    private double targetAmount;

    private double currentAmount;

    private ArrayList<Comment> comments = new ArrayList<>();

    public Fundraiser(User user) {
        this.owner = user;
    }
}
