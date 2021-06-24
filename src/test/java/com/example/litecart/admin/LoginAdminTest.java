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
    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");

    @DisplayName("Проверка входа в админку приложения litecart")
    @Test
    public void checkLoginToAdminPanelTest() {
        driver.get(baseUrl);

        WebElement username = driver.findElement(usernameLocator);
        username.clear();
        username.sendKeys("admin");


        WebElement password = driver.findElement(passwordLocator);
        password.sendKeys("admin");
        password.sendKeys(Keys.ENTER);

        Assertions.assertTrue(driver.findElement(By.id("box-widgets")).isDisplayed());
    }
}
