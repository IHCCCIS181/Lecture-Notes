package org.example.fundspark.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotBlank(message = "Comment text cannot be empty")
    @Length(max = 500)
    private String text;

    @NotBlank(message = "Author username cannot be empty")
    private String authorUsername;

    private String fundraiserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postedAt;

    public String getTimeAgo() {
        if (postedAt == null) {
            return "just now";
        }
        Instant now = Instant.now();
        Instant postedAtInstant = postedAt.atZone(ZoneId.systemDefault()).toInstant();

        long diffInSeconds = Math.abs(now.toEpochMilli() - postedAtInstant.toEpochMilli()) / 1000;
        long diffInMinutes = diffInSeconds / 60;
        long diffInHours = diffInMinutes / 60;
        long diffInDays = diffInHours / 24;

        if (diffInDays > 0) {
            return diffInDays + "d ago";
        } else if (diffInHours > 0) {
            return diffInHours + "h ago";
        } else if (diffInMinutes > 0) {
            return diffInMinutes + "m ago";
        } else {
            return diffInSeconds + "s ago";
        }
    }

    public Comment(String text, String authorUsername) {
        this.id = java.util.UUID.randomUUID().toString();
        this.text = text;
        this.authorUsername = authorUsername;
        this.postedAt = LocalDateTime.now();
    }
}
