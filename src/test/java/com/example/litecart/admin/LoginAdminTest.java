package com.example.litecart.admin;

import com.example.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class LoginAdminTest extends BaseTest {

    String baseUrl = "http://localhost/litecart/admin";

    @DisplayName("Проверка входа в админку приложения litecart")
    @Test
    public void checkSearchByGoogleTest() {
        driver.get(baseUrl);
        By username = By.name("username");
        WebElement usernameField = driver.findElement(username);
        usernameField.clear();
        usernameField.sendKeys("admin");

        By password = By.name("password");
        WebElement passwordField = driver.findElement(password);
        passwordField.sendKeys("admin");
        passwordField.sendKeys(Keys.ENTER);

        Assertions.assertTrue(driver.findElement(By.id("box-widgets")).isDisplayed());
    }
}
