package org.example.weather.repository;

import org.example.weather.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.weather.model.*;


import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
    Weather findByStateAndCity(State state, String City);
    Weather findByCity(String city);
    List<Weather> findByState(State state);
}
