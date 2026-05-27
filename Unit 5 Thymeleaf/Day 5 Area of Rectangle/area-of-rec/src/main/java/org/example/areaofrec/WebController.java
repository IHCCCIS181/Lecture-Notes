package org.example.areaofrec;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String showForm(Rectangle rectangle) {
        return "form";
    }

    @PostMapping("/")
    public String checkThenCalculateArea(@Valid Rectangle rectangle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "form";
        }
        double area = rectangle.calcArea();
        model.addAttribute("area", area);
        return "result";
    }
}
