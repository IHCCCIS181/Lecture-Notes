package org.example.weather.Controller;

import jakarta.validation.Valid;
import org.example.weather.model.*;
import org.example.weather.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherAdminController {

    @Autowired
    private WeatherService weatherService;

    // @GetMapping("/{city}")
    // public ResponseEntity<Weather> getWeatherByCity(@PathVariable String city) {
    // Weather weather = weatherService.getWeather(city);
    // return weather != null ? ResponseEntity.ok(weather) :
    // ResponseEntity.notFound().build();
    // }

    // @GetMapping("/state/{state}")
    // public ResponseEntity<List<Weather>> getWeatherByState(@PathVariable State
    // state) {
    // List<Weather> weathers = weatherService.getWeatherByState(state);
    // return weathers != null && !weathers.isEmpty() ? ResponseEntity.ok(weathers)
    // : ResponseEntity.noContent().build();
    // }

    @PostMapping
    public ResponseEntity<?> addWeather(@Valid @RequestBody WeatherDTO weatherDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(errors);
        }

        try {
            weatherService.addWeather(weatherDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CityAndStateAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PutMapping
    // public ResponseEntity<Void> updateWeather(@RequestBody WeatherDTO weatherDTO)
    // {
    // weatherService.updateWeather(weatherDTO);
    // return ResponseEntity.ok().build();
    // }

    // @DeleteMapping("/{city}/{state}")
    // public ResponseEntity<Void> deleteWeather(@PathVariable String city,
    // @PathVariable State state) {
    // weatherService.deleteWeather(city, state);
    // return ResponseEntity.noContent().build();
    // }
}
