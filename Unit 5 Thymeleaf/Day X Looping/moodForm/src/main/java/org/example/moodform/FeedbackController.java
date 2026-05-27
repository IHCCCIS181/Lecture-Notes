package org.example.moodform;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;

@Controller
public class FeedbackController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/thankyou").setViewName("thankyou");
    }


    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new FeedbackForm());
        return "feedback";
    }

    @PostMapping("/submit-feedback")
    public String submitFeedback(@Valid FeedbackForm feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("feedback", feedback);
            return "feedback";
        }

        if (!Arrays.asList(SatisfactionLevel.values()).contains(feedback.getSatisfactionLevel())) {
            bindingResult.rejectValue("satisfactionLevel", "invalid.satisfactionLevel", "Invalid satisfaction level");
            return "feedback";
        }

        // Process the feedback here
        System.out.println("Submitted feedback: " + feedback.getComment() + ", Satisfaction Level: " + feedback.getSatisfactionLevel());

        return "redirect:/thankyou";
    }
}
