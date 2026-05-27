package org.example.areaofrec;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class Rectangle {
    @Positive(message = "Length must be a positive number.")
    @Min(value = 1, message = "Length must be greater than 1.")
    private double length;

    @Positive(message = "Width must be a positive number.")
    @Min(value = 1, message = "Width must be greater than 1.")
    private double width;


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double calcArea() {
        return width * length;
    }
}

