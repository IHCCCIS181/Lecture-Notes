package org.example.fundspark.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
public class FundraiserDTO {
    @NotBlank(message = "Campaign title cannot be empty")
    private String title;

    @Length(max = 500)
    private String description;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @Future
    private Date endDate;

    @NotNull(message = "Target amount cannot be null")
    @Positive
    private double targetAmount;
}
