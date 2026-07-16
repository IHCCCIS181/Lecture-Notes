package com.example.ihcc_podcast;

import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PodcastConsumer {

    private final Gson gson = new Gson();

    @KafkaListener(topics = "ihcc-podcast-episodes", groupId = "ihcc-podcast-group")
    public void handleNewEpisode(String message) {
        // Log the received message for debugging purposes
        System.out.println("[KAFKA CONSUMER] Received message: " + message);
        
        PodcastEpisode episode = gson.fromJson(message, PodcastEpisode.class);
        
        // Save straight to the static list in memory
        PodcastController.episodes.add(0, episode);
    }
}