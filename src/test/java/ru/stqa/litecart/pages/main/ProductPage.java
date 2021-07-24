package ru.stqa.litecart.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.litecart.pages.Page;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPage extends Page {

    By addToCartButtonLocator = By.cssSelector("button[name=add_cart_product]");
    By sizeLocator = By.cssSelector("select[name*=options]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstProductSize() {
        if (isElementPresent(driver, sizeLocator)) {
            WebElement element = driver.findElement(sizeLocator);
            Select selector = new Select(element);
            selector.selectByIndex(1);
        }
    }

    public void addProductToCart() {
        driver.findElement(addToCartButtonLocator).click();
    }
}
