package com.example.commerce.model;

import lombok.*;

// We needs this class with no validation.

@AllArgsConstructor
@Data
public class OrderEvent {
    private String itemName;
    private int quantity;
    private String customerEmail;
}