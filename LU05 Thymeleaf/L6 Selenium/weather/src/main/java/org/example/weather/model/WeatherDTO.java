package org.example.weather.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDTO {

    public String city;
    public State state;
    public double tempC;
    public double precipitation;
    public double humidity;
    public int windKmH;
}


