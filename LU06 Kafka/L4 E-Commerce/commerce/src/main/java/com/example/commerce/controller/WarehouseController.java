package com.example.commerce.controller;

import com.example.commerce.consumer.WarehouseConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WarehouseController {

    private final WarehouseConsumer warehouseConsumer;

    public WarehouseController(WarehouseConsumer warehouseConsumer) {
        this.warehouseConsumer = warehouseConsumer;
    }

    @GetMapping("/warehouse")
    public String showDashboard(Model model) {
        // Gets only recent orders from the in-memory list, not the database
        model.addAttribute("orders", warehouseConsumer.getRecentOrders());
        return "warehouse";
    }
}