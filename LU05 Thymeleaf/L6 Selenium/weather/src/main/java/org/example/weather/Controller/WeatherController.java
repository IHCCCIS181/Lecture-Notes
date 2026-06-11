package org.example.weather.Controller;

import org.example.weather.model.*;
import org.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String showForm(@RequestParam(required = false) String city,
                           @RequestParam(required = false) State state,
                           Model model) {
        if (city != null && !city.isEmpty()) {
            model.addAttribute("weatherList", weatherService.getWeather(city));
        } else if (state != null) {
            model.addAttribute("weatherList", weatherService.getWeatherByState(state));
        }

        return "weatherDisplay";
    }
}