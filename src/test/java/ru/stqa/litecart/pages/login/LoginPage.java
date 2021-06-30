package ru.stqa.litecart.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.litecart.pages.admin.AdminPage;

public class LoginPage {
    WebDriver driver;

    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    By loginButtonLocator = By.cssSelector("[type = submit]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPage successLoginByAdmin(String login, String pass) {
        WebElement username = driver.findElement(usernameLocator);
        username.clear();
        username.sendKeys(login);
        WebElement password = driver.findElement(passwordLocator);
        password.clear();
        password.sendKeys(pass);
        driver.findElement(loginButtonLocator).click();

        return new AdminPage(driver);
    }

    public LoginPage errorLoginByUser(String login, String pass) {
        WebElement username = driver.findElement(usernameLocator);
        username.clear();
        username.sendKeys(login);
        WebElement password = driver.findElement(passwordLocator);
        password.clear();
        password.sendKeys(pass);
        driver.findElement(loginButtonLocator).click();

        return new LoginPage(driver);
    }
}
