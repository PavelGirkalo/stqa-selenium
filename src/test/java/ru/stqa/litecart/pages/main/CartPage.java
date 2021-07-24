package ru.stqa.litecart.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.pages.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class CartPage extends Page {
    By removeProductFromCartButtonLocator = By.cssSelector("button[name=remove_cart_item]");
    By orderSummaryLocator = By.xpath("//h2[text()='Order Summary']");
    By shortcutLocator = By.xpath("//a[@href='#']");
    By orderQuantityLocator = By.cssSelector("#order_confirmation-wrapper tr");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void isCartPage() {
        WebElement orderHeader = driver.findElement(orderSummaryLocator);
        assertTrue(orderHeader.isDisplayed());
    }

    public void isEmptyCart() {
        assertFalse(isElementPresent(driver, orderSummaryLocator));
    }

    public void removeProductFromCart() {
        driver.findElement(removeProductFromCartButtonLocator).click();
    }

    public List<WebElement> getShortcuts() {
        return driver.findElements(shortcutLocator);
    }

    public CartPage openFirstShortcut() {
        driver.findElements(shortcutLocator).get(0).click();
        return new CartPage(driver);
    }

    public int getOrderQuantity() {
        return driver.findElements(orderQuantityLocator).size();
    }

    public void waitUntilProductIsRemovingFromOrder(int quantityAfterRemove) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr"),
                quantityAfterRemove));
    }
}
