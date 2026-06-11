package org.example.moodform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FeedbackForm {
    @NotBlank(message = "Comment is required")
    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;


    private SatisfactionLevel satisfactionLevel;

    public FeedbackForm() {}

    public FeedbackForm(String comment, SatisfactionLevel satisfactionLevel) {
        this.comment = comment;
        this.satisfactionLevel = satisfactionLevel;
    }

    // Getters and Setters
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SatisfactionLevel getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(SatisfactionLevel satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }
}


