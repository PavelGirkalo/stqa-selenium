package ru.stqa.litecart.tests.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;

import java.util.List;


public class MainPageTests extends BaseTest {

    String baseUrl = "http://localhost/litecart";

    @Test
    public void checkStickersForAllProductsTest() {
        By mainPageLocator = By.cssSelector("[title=Home]");
        By productLocator = By.cssSelector("li.product");
        By checkedLocator = By.cssSelector("div.sticker");

        driver.get(baseUrl);
        Assertions.assertTrue(driver.findElement(mainPageLocator).isDisplayed());

        List<WebElement> products = driver.findElements(productLocator);
        for (WebElement product : products) {
            List<WebElement> stickers = product.findElements(checkedLocator);
            Assertions.assertTrue(stickers.size() > 0);
            Assertions.assertEquals(1, stickers.size());
        }
    }
}
