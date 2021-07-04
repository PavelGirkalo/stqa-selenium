package ru.stqa.litecart.tests.main;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.models.main.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.stqa.litecart.models.main.Product.buildProductFromMainPage;
import static ru.stqa.litecart.models.main.Product.buildProductFromProductInfoPage;


public class MainPageTests extends BaseTest {

    String baseUrl = "http://localhost/litecart";

    @Test
    public void checkStickersForAllProductsTest() {
        By mainPageLocator = By.cssSelector("[title=Home]");
        By productLocator = By.cssSelector("li.product");
        By checkedLocator = By.cssSelector("div.sticker");

        driver.get(baseUrl);
        assertTrue(driver.findElement(mainPageLocator).isDisplayed());

        List<WebElement> products = driver.findElements(productLocator);
        for (WebElement product : products) {
            List<WebElement> stickers = product.findElements(checkedLocator);
            assertTrue(stickers.size() > 0);
            assertEquals(1, stickers.size());
        }
    }

    @Test
    public void checkProductFieldsTest() {
        By mainPageLocator = By.cssSelector("[title=Home]");
        By campaignsProductLocator = By.cssSelector("div#box-campaigns li.product");

        driver.get(baseUrl);
        assertTrue(driver.findElement(mainPageLocator).isDisplayed());

        // получение продукта с основной страницы и создание объекта на основе WebElement
        List<WebElement> campaignsProducts = driver.findElements(campaignsProductLocator);
        WebElement firstCampaignsProductFromMainPage = campaignsProducts.get(0);
        Product productFromMainPage = buildProductFromMainPage(firstCampaignsProductFromMainPage);

        // проверка параметров обычной цены на основной странице (пункт задания в)
        assertNotEquals(null, productFromMainPage.getPriceWrapper().getRegularPrice()); // замена проверки зачеркнутого текста
        String regularColor = productFromMainPage.getPriceWrapper().getRegularPrice().getRegularPriceColor();
        String greyColor = StringUtils.substringBetween(regularColor, "(", ",");
        assertTrue(regularColor.contains(greyColor + ", " + greyColor + ", " + greyColor));

        // проверка параметров акционной цены на основной странице (пункты задания г, д)
        String campaignColor = productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceColor();
        String redColor = StringUtils.substringBetween(campaignColor, "(", ",");
        assertTrue(campaignColor.contains(redColor + ", 0, 0"));
        assertTrue(productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceColor().contains(", 0, 0"));
        assertTrue(Integer.parseInt(productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceWeight()) > 500);
        assertTrue(productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceFontSize() >
                productFromMainPage.getPriceWrapper().getRegularPrice().getRegularPriceFontSize());

        // получение продукта со страницы информации о продукте и создание объекта на основе WebElement
        firstCampaignsProductFromMainPage.click();
        WebElement productInfo = driver.findElement(By.cssSelector("div#box-product"));
        Product productFromProductInfoPage = buildProductFromProductInfoPage(productInfo);

        // проверка параметров обычной цены на странице с информацией о продукте (пункт задания в)
        assertNotEquals(null, productFromProductInfoPage.getPriceWrapper().getRegularPrice()); // замена проверки зачеркнутого текста
        regularColor = productFromProductInfoPage.getPriceWrapper().getRegularPrice().getRegularPriceColor();
        greyColor = StringUtils.substringBetween(regularColor, "(", ",");
        assertTrue(regularColor.contains(greyColor + ", " + greyColor + ", " + greyColor));

        // проверка параметров акционной цены на странице с информацией о продукте (пункты задания г, д)
        campaignColor = productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceColor();
        redColor = StringUtils.substringBetween(campaignColor, "(", ",");
        assertTrue(campaignColor.contains(redColor + ", 0, 0"));
        assertTrue(Integer.parseInt(productFromProductInfoPage.getPriceWrapper().getCampaignPrice().getCampaignPriceWeight()) > 500);
        assertTrue(productFromProductInfoPage.getPriceWrapper().getCampaignPrice().getCampaignPriceFontSize() >
                productFromProductInfoPage.getPriceWrapper().getRegularPrice().getRegularPriceFontSize());

        // сравнение параметров продукта на основной странице и странице с информацией о продукте (пункты задания а, б)
        assertEquals(productFromMainPage.getName(), productFromProductInfoPage.getName());
        assertEquals(productFromMainPage.getPriceWrapper().getRegularPrice().getRegularPriceText(),
                productFromProductInfoPage.getPriceWrapper().getRegularPrice().getRegularPriceText());
        assertEquals(productFromMainPage.getPriceWrapper().getCampaignPrice().getCampaignPriceText(),
                productFromProductInfoPage.getPriceWrapper().getCampaignPrice().getCampaignPriceText());
    }
}
