package ru.stqa.litecart.pages.main.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.pages.Page;
import ru.stqa.litecart.pages.main.CartPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class CartWidget extends Page {

    @FindBy(xpath = "//span[@class='quantity']")
    WebElement productCount;

    @FindBy(xpath = "//span[@class='quantity']")
    WebElement checkoutButton;

    public CartWidget(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getProductCountOfCart() {
        return Integer.parseInt(productCount.getAttribute("textContent"));
    }

    public void waitUntilProductIsAddingToCart(int oldQuantity) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(textToBe(By.cssSelector("span.quantity"), String.valueOf(oldQuantity + 1)));
    }

    public CartPage clickCheckoutButton() {
        checkoutButton.click();
        return new CartPage(driver);
    }
}
