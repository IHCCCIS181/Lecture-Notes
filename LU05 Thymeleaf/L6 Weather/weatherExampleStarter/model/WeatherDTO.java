package org.example.weather.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDTO {

    @NotBlank(message = "City is required")
    public String city;

    @NotNull(message = "State is required")
    public State state;

    @DecimalMin(value = "-100.0", message = "Temperature must be at least -100 C")
    @DecimalMax(value = "60.0", message = "Temperature must be at most 60 C")
    public double tempC;

    @DecimalMin(value = "0.0", message = "Precipitation must be 0 or greater")
    public double precipitation;

    @DecimalMin(value = "0.0", message = "Humidity must be 0 or greater")
    @DecimalMax(value = "100.0", message = "Humidity must be 100 or less")
    public double humidity;

    @Min(value = 0, message = "Wind speed must be 0 or greater")
    public int windKmH;
}