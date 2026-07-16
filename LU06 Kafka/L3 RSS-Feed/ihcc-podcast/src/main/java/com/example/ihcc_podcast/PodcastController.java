package com.example.ihcc_podcast;

import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class PodcastController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Gson gson = new Gson();

    // CopyOnWriteArrayList is needed incase multiple threads
    // Kafka consumer and web requests access the list concurrently
    public static final List<PodcastEpisode> episodes = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("episode", new PodcastEpisode());
        return "form";
    }

    @PostMapping("/publish")
    public String publishEpisode(
            @Valid @ModelAttribute("episode") PodcastEpisode episode,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        // Publish plain JSON to Kafka
        kafkaTemplate.send("ihcc-podcast-episodes", gson.toJson(episode));

        return "redirect:/feed";
    }

    @GetMapping("/feed")
    public String showFeed(Model model) {
        model.addAttribute("episodes", episodes);
        return "feed";
    }
}