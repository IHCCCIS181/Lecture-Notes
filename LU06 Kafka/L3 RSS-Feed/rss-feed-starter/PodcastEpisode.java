package com.example.ihcc_podcast;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodcastEpisode {

    @NotBlank(message = "Title is required.")
    @Size(min = 3, message = "Title must be at least 3 characters.")
    private String title;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotBlank(message = "Audio URL is required.")
    private String audioUrl;

    @NotBlank(message = "Author is required.")
    private String author;
}