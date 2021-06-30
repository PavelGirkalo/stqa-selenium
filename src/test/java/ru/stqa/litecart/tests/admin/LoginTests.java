package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.login.LoginPage;


public class LoginTests extends BaseTest {

    String baseUrl = "http://localhost/litecart/admin";
    LoginPage loginPage = new LoginPage(driver);

    @DisplayName("Login to litecart admin page")
    @Test
    public void checkSuccessLoginToAdminPanelTest() {
        driver.get(baseUrl);
        loginPage.successLoginByAdmin("admin", "admin");
        Assertions.assertTrue(isElementPresent(driver, By.id("sidebar")));
    }

    @DisplayName("Login to litecart admin page")
    @Test
    public void checkErrorLoginToAdminPanelTest() {
        driver.get(baseUrl);
        loginPage.errorLoginByUser("admin", "123456");
        Assertions.assertFalse(isElementPresent(driver, By.id("sidebar")));
    }
}
