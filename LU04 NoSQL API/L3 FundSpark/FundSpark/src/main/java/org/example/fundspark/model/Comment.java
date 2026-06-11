package org.example.fundspark.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private UUID id;

    @NotBlank(message = "Comment text cannot be empty")
    @Length(max = 500)
    private String text;

    @NotBlank(message = "Author username cannot be empty")
    private String authorUsername;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postedAt;

    public String getTimeAgo() {
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
        this.id = UUID.randomUUID();
        this.text = text;
        this.authorUsername = authorUsername;
        this.postedAt = LocalDateTime.now();
    }
}
