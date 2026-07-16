package com.example.commerce.controller;

import com.example.commerce.model.*;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson = new Gson();

    public CheckoutController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        return "index";
    }

    @PostMapping("/")
    public String submitForm(@Valid @ModelAttribute("orderForm") OrderForm orderForm, BindingResult result) {
        // If there are validation errors, return to the form page
        if (result.hasErrors()) {
            return "index";
        }

        // OrderForm -> OrderEvent
        OrderEvent event = new OrderEvent(
                orderForm.getItemName(),
                orderForm.getQuantity(),
                orderForm.getCustomerEmail());

        // Sends kafka event triggering all consumers (email, warehouse, audit)
        kafkaTemplate.send("ihcc-orders", gson.toJson(event));

        return "redirect:/thank-you";
    }

    @GetMapping("/thank-you")
    public String showThankYou() {
        return "thank-you";
    }
}