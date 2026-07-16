package com.example.commerce.consumer;

import com.example.commerce.model.OrderEvent;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final JavaMailSender mailSender;
    private final Gson gson = new Gson();

    public EmailConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "ihcc-orders", groupId = "email-group")
    public void consume(String message) {
        try {
            OrderEvent event = gson.fromJson(message, OrderEvent.class);

            // Create the email message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(event.getCustomerEmail());
            mailMessage.setFrom("noreply@ihcc.edu");
            mailMessage.setSubject("Thank you for your IHCC Order!");
            // Use more modern
            mailMessage.setText(String.format(
                    "Hello!\n\nYour order for %dx %s has been received and is currently being processed.\n\nThank you for shopping at IHCC!",
                    event.getQuantity(),
                    event.getItemName()));

            // Send the email
            mailSender.send(mailMessage);
            System.out.println(">>> Email successfully sent to: " + event.getCustomerEmail());

        } catch (Exception e) {
            System.err.println("Failed to send email notification: " + e.getMessage());
        }
    }
}