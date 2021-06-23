package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class GoogleSmokeTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void checkSearchByGoogleTest() {
        driver.get(baseUrl);
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("maven repository");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
