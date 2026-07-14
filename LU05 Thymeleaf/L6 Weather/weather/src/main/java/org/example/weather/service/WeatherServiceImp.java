package org.example.weather.service;

import org.example.weather.model.State;
import org.example.weather.model.Weather;
import org.example.weather.model.WeatherDTO;
import org.example.weather.model.WeatherSearchForm;
import org.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WeatherServiceImp implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> getWeather(WeatherSearchForm weatherSearchForm) {
        String city = weatherSearchForm.getCity();
        State state = weatherSearchForm.getState();
        boolean hasCity = city != null && !city.isBlank();

        if (hasCity && state != null) {
            Weather weather = weatherRepository.findByStateAndCity(state, city);
            return weather != null ? List.of(weather) : Collections.emptyList();
        }

        if (hasCity) {
            Weather weather = weatherRepository.findByCity(city);
            return weather != null ? List.of(weather) : Collections.emptyList();
        }

        if (state != null) {
            return weatherRepository.findByState(state);
        }

        return weatherRepository.findAll();
    }

    @Override
    public void addWeather(WeatherDTO weatherDTO) throws CityAndStateAlreadyExistsException {
        // Checking if it exists
        // This might not be right
        if (weatherRepository.findByStateAndCity(weatherDTO.getState(), weatherDTO.getCity()) != null) {
            throw new CityAndStateAlreadyExistsException();
        }
        Weather weather = new Weather(
                weatherDTO.getCity(),
                weatherDTO.getState(),
                weatherDTO.getTempC(),
                weatherDTO.getPrecipitation(),
                weatherDTO.getHumidity(),
                weatherDTO.getWindKmH());
        weatherRepository.save(weather);
    }

    // @Override
    // public void updateWeather(WeatherDTO weatherDTO) {
    // Weather existingWeather = getWeather(weatherDTO.getCity());
    // if (existingWeather != null) {
    // existingWeather.setState(weatherDTO.getState());
    // existingWeather.setTempC(weatherDTO.getTempC());
    // existingWeather.setPrecipitation(weatherDTO.getPrecipitation());
    // existingWeather.setHumidity(weatherDTO.getHumidity());
    // existingWeather.setWindKmH(weatherDTO.getWindKmH());
    // weatherRepository.save(existingWeather);
    // }
    // }

    // @Override
    // public void deleteWeather(String city, State state) {
    // Weather weather = getWeather(city);
    // if (weather != null && weather.getState().equals(state)) {
    // weatherRepository.delete(weather);
    // }
    // }
}
