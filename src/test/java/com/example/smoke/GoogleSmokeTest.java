package com.example.smoke;

import com.example.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class GoogleSmokeTest extends BaseTest {

    String baseUrl = "https://www.google.com/";

    @Test
    public void checkSearchByGoogleTest() {
        driver.get(baseUrl);
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("maven repository");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }
}
