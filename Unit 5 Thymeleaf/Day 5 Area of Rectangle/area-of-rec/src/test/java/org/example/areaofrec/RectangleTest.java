package org.example.areaofrec;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle();
    }

    @Test
    void testCalcAreaWithValidDimensions() {
        rectangle.setLength(5);
        rectangle.setWidth(10);

        assertEquals(50, rectangle.calcArea());
    }



    @Test
    void testZeroWidth() {
        rectangle.setLength(10);
        rectangle.setWidth(0);

        ValidatorFactory factory = buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Rectangle>> violations = validator.validate(rectangle);

        // there are violations
        assertFalse(violations.isEmpty());

    }

}
