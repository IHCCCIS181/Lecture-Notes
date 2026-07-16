package com.example.commerce.consumer;

import com.example.commerce.model.*;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//Used to save audit logs to the mongodb database when an order is placed

@Component
public class AuditConsumer {

    private final AuditRepository repository;
    private final Gson gson = new Gson();

    public AuditConsumer(AuditRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "ihcc-orders", groupId = "audit-group")
    public void consume(String message) {
        OrderEvent event = gson.fromJson(message, OrderEvent.class);
        
        AuditLog log = new AuditLog();
        log.setItemName(event.getItemName());
        log.setQuantity(event.getQuantity());
        log.setCustomerEmail(event.getCustomerEmail());
        
        repository.save(log);
    }
}