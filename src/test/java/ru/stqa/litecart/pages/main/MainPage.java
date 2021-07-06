package ru.stqa.litecart.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void isMainPage() {
        assertTrue(driver.findElement(By.cssSelector("[title=Home]")).isDisplayed());
    }

    public void isLoggedIn() {
        assertTrue(driver.findElement(By.xpath("//a[text()='Logout']")).isDisplayed());
    }

    public void isLoggedOut() {
        assertTrue(driver.findElement(By.xpath("//button[text()='Login']")).isDisplayed());
    }

    public CreateAccountPage moveToCreateAccountPage() {
        driver.findElement(By.xpath("//a[text()='New customers click here']")).click();

        return new CreateAccountPage(driver);
    }

    public CreateAccountPage logout() {
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        return new CreateAccountPage(driver);
    }

    public MainPage loginByUser(String mail, String password) {
        WebElement mailForLogin = driver.findElement(By.cssSelector("input[name=email]"));
        mailForLogin.clear();
        mailForLogin.sendKeys(mail);
        WebElement passForLogin = driver.findElement(By.cssSelector("input[name=password]"));
        passForLogin.clear();
        passForLogin.sendKeys(password, Keys.ENTER);

        return new MainPage(driver);
    }
}
