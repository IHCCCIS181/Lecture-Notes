package org.example.weather.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "weather")
public class Weather {

    @Id
    public String id;

    @NotBlank(message = "City is required")
    public String city;

    @NotNull(message = "State is required")
    public State state;

    @DecimalMin(value = "-100.0", message = "Temperature must be at least -100 C")
    @DecimalMax(value = "60.0", message = "Temperature must be at most 60 C")
    public double tempC;

    public double tempF;

    @DecimalMin(value = "0.0", message = "Precipitation must be 0 or greater")
    public double precipitation;

    @DecimalMin(value = "0.0", message = "Humidity must be 0 or greater")
    @DecimalMax(value = "100.0", message = "Humidity must be 100 or less")
    public double humidity;

    @Min(value = 0, message = "Wind speed must be 0 or greater")
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

    private int convertKmHToMpH(int windKmH) {
        return (int) Math.round(windKmH * 1.60934);
    }

    private double convertCtoF(double tempC) {
        return (tempC * 9 / 5) + 32;
    }
}