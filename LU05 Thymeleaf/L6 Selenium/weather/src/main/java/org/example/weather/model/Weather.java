package org.example.weather.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

//TODO add validation

@Document(collection = "weather")
public class Weather {

    @Id
    public String id;
    public String city;
    public State state;
    public double tempC;
    public double tempF;
    public double precipitation;
    public double humidity;
    public int windKmH;
    public int windMpH;


    public Weather(String city, State state, double tempC, double precipitation, double hum, int windKmH) {
        this.city = city;
        this.state = state;
        this.tempC = tempC;
        this.tempF = convertCtoF(tempC);
        this.precipitation = precipitation;
        this.humidity = hum;
        this.windKmH = windKmH;
        this.windMpH = convertKmHToMpH(windKmH);
    }

    //TODO build unit tests
    private int convertKmHToMpH(int windKmH) {
        return (int) Math.round(windKmH * 1.60934);
    }

    //TODO build unit tests
    private double convertCtoF(double tempC) {
        return (tempC * 9 / 5) + 32;
    }


}

