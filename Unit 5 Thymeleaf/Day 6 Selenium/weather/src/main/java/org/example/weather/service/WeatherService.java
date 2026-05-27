package org.example.weather.service;

import org.example.weather.model.*;
import org.example.weather.model.WeatherDTO;

import java.util.List;

public interface WeatherService {
    Weather getWeather(String city);
    List<Weather> getWeatherByState(State state);
    void addWeather(WeatherDTO weather) throws CityAndStateAlreadyExistsException;
    void updateWeather(WeatherDTO weather);
    void deleteWeather(String city, State state);
}
