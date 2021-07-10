package ru.stqa.litecart.tests.main;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.main.MainPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TrashTests extends BaseTest {

    String baseUrl = "http://localhost/litecart";
    WebDriverWait wait = new WebDriverWait(driver, 3);

    @Test
    public void checkCreatingNewAccountTest() {
        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);
        mainPage.isMainPage();

        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.cssSelector("i[title=Home]")).click();

            List<WebElement> popularProducts = driver.findElements(By.cssSelector("#box-most-popular li"));
            popularProducts.get(0).click();
            WebElement productCountFromTrash = driver.findElement(By.xpath("//span[@class='quantity']"));
            int trashCountBeforeAdding = Integer.parseInt(productCountFromTrash.getAttribute("textContent"));

            if (isElementPresent(driver, By.cssSelector("select[name*=options]"))) {
                WebElement element = driver.findElement(By.cssSelector("select[name*=options]"));
                Select selector = new Select(element);
                selector.selectByIndex(1);
            }
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();

            wait.until(textToBe(By.cssSelector("span.quantity"), String.valueOf(trashCountBeforeAdding + 1)));

            productCountFromTrash = driver.findElement(By.xpath("//span[@class='quantity']"));
            int trashCountAfterAdding = Integer.parseInt(productCountFromTrash.getAttribute("textContent"));

            assertEquals(1, trashCountAfterAdding - trashCountBeforeAdding);
        }

        driver.findElement(By.xpath("//a[contains(text(), 'Checkout')]")).click();
        WebElement orderHeader = driver.findElement(By.xpath("//h2[text()='Order Summary']"));

        assertTrue(orderHeader.isDisplayed());

        while (driver.findElements(By.xpath("//a[@href='#']")).size() > 0) {
            driver.findElements(By.xpath("//a[@href='#']")).get(0).click();
            int productCountBeforeDelete = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr")).size();

            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr"),
                    productCountBeforeDelete-1));
        }

        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        wait.until(numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr"), 0));

        assertFalse(isElementPresent(driver, By.xpath("//h2[text()='Order Summary']")));

        driver.findElement(By.cssSelector("i[title=Home]")).click();
    }
}
