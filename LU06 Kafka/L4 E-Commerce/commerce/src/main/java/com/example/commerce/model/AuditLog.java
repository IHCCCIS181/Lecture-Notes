package com.example.commerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Document(collection = "ihcc_audit_logs")
public class AuditLog {

    @Id
    private String id;
    private String itemName;
    private int quantity;
    private String customerEmail;
    private LocalDateTime timestamp = LocalDateTime.now();
}