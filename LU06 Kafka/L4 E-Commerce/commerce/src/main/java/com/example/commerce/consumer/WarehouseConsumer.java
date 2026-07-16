package com.example.commerce.consumer;

import com.example.commerce.model.OrderEvent;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//Used to store recent orders in memory for display in the warehouse dashboard
//This could just read from the database, but this is a simple way to demonstrate Kafka 

@Component
public class WarehouseConsumer {

    private final List<OrderEvent> recentOrders = new CopyOnWriteArrayList<>();
    private final Gson gson = new Gson();

    @KafkaListener(topics = "ihcc-orders", groupId = "warehouse-group")
    public void consume(String message) {
        OrderEvent event = gson.fromJson(message, OrderEvent.class);
        recentOrders.add(event);
    }

    public List<OrderEvent> getRecentOrders() {
        return recentOrders;
    }
}