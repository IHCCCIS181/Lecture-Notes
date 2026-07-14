package org.example.weather.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherSearchForm {

    @Size(max = 100, message = "City must be 100 characters or less")
    @Pattern(regexp = "^[A-Za-z .'-]*$", message = "City can only contain letters, spaces, periods, apostrophes, and hyphens")
    private String city;

    private State state;

    private boolean submitted;

    @AssertTrue(message = "Enter a city, state, or both")
    public boolean isCityOrStateProvided() {
        return !submitted || (city != null && !city.isBlank()) || state != null;
    }
}