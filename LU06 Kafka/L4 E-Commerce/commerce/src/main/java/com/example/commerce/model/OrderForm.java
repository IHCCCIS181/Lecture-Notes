package com.example.commerce.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderForm {

    @NotBlank(message = "Item name is required")
    private String itemName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email format")
    private String customerEmail;
}