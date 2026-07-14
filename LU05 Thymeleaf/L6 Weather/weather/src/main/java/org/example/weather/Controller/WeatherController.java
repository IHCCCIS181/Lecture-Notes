package org.example.weather.Controller;

import jakarta.validation.Valid;
import org.example.weather.model.WeatherSearchForm;
import org.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String showForm(@Valid @ModelAttribute("weatherForm") WeatherSearchForm weatherForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("weatherList", Collections.emptyList());
            return "weatherDisplay";
        }

        model.addAttribute("weatherList", weatherService.getWeather(weatherForm));

        return "weatherDisplay";
    }
}