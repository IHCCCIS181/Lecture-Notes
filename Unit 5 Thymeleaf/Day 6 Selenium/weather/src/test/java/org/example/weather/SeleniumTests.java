package org.example.weather;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTests {

//    API MUST BE RUNNING FOR TESTS TO RUN

    private WebDriver driver;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        // Initialize WebDriver
        driver = new FirefoxDriver();
        // or if you want to use Chrome NOTE, chrome must be a bit older
        //driver = new ChromeDriver();
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {

    }

    @Test
    public void testWeatherFormSubmission() {
        driver.get("http://localhost:8080");

        //maximum wait time
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.name("city")).sendKeys("Ottumwa");
        driver.findElement(By.tagName("form")).submit();

        // wait for an element to be on screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cityName")));

        String cityName = driver.findElement(By.id("cityName")).getText();
        assert !cityName.isEmpty();
    }
}
