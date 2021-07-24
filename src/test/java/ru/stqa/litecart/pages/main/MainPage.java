package ru.stqa.litecart.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.litecart.pages.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends Page {

    String baseUrl = "http://localhost/litecart";

    By popularProductsLocator = By.cssSelector("#box-most-popular li");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openMainPage() {
        driver.get(baseUrl);
        return new MainPage(driver);
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

    public List<WebElement> getPopularProductList() {
        return driver.findElements(popularProductsLocator);
    }

    public ProductPage openFirstPopularProduct(List<WebElement> popularProducts) {
        popularProducts.get(0).click();
        return new ProductPage(driver);
    }
}
