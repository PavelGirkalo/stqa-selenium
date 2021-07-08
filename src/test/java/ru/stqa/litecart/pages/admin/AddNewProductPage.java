package ru.stqa.litecart.pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddNewProductPage {
    WebDriver driver;

    By enableStatusLocator = By.xpath("//label[text()=' Enabled']");
    By disableStatusLocator = By.xpath("//label[text()=' Disabled']");
    By nameLocator = By.cssSelector("input[name*=name]");
    By codeLocator = By.cssSelector("input[name=code]");
    By quantityLocator = By.cssSelector("input[name=quantity]");
    By uploadImagesButtonLocator = By.cssSelector("input[type=file]");
    By dateValidFromLocator = By.cssSelector("input[name=date_valid_from]");
    By dateValidToLocator = By.cssSelector("input[name=date_valid_to]");

    By manufacturerLocator = By.cssSelector("select[name=manufacturer_id]");
    By supplierLocator = By.cssSelector("select[name=supplier_id]");
    By keywordsLocator = By.cssSelector("input[name=keywords]");
    By shortDescLocator = By.cssSelector("input[name*=short_description]");
    By descLocator = By.cssSelector("div.trumbowyg-editor");
    By headTitleDescLocator = By.cssSelector("input[name*=head_title]");
    By metaDescLocator = By.cssSelector("input[name*=meta_description]");

    By purchaseLocator = By.cssSelector("input[name=purchase_price]");
    By purchaseCurLocator = By.cssSelector("select[name=purchase_price_currency_code]");
    By purchaseUsdLocator = By.cssSelector("input[name=gross_prices\\[USD\\]]");
    By purchaseEurLocator = By.cssSelector("input[name=gross_prices\\[EUR\\]]");

    By saveButtonLocator = By.cssSelector("button[name=save]");

    public AddNewProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void isAddNewProductPage() {
        assertTrue(driver.findElement(By.xpath("//h1[text()=' Add New Product']")).isDisplayed());
    }


    public AdminPage addNewProduct(String name, String imagePath, String price) {
        fillGeneralTab(name, imagePath);
        fillInformationTab();
        fillPricesTab(price);
        driver.findElement(saveButtonLocator).click();

        return new AdminPage(driver);
    }

    private AddNewProductPage fillGeneralTab(String name, String imagePath) {
        driver.findElement(By.xpath("//a[text()='General']")).click();

        driver.findElement(enableStatusLocator).click();
        driver.findElement(nameLocator).sendKeys(name);
        driver.findElement(codeLocator).sendKeys(name);
        WebElement quantity = driver.findElement(quantityLocator);
        quantity.clear();
        quantity.sendKeys(Keys.HOME, "5");

        try {
            URL res = getClass().getClassLoader().getResource(imagePath);
            File file = Paths.get(Objects.requireNonNull(res).toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            WebElement uploadImagesButton = driver.findElement(uploadImagesButtonLocator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].type='file'", uploadImagesButton);
            uploadImagesButton.sendKeys(absolutePath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        driver.findElement(dateValidFromLocator).sendKeys(Keys.HOME, "01012021");
        driver.findElement(dateValidToLocator).sendKeys(Keys.HOME, "01012025");

        return this;
    }

    private AddNewProductPage fillInformationTab() {
        driver.findElement(By.xpath("//a[text()='Information']")).click();

        WebElement manufacturerSelector = driver.findElement(manufacturerLocator);
        manufacturerSelector.click();
        Select select = new Select(manufacturerSelector);
        select.selectByIndex(1);

        driver.findElement(keywordsLocator).sendKeys("Keyword");
        driver.findElement(shortDescLocator).sendKeys("Short Description");
        driver.findElement(descLocator).sendKeys("Description");
        driver.findElement(headTitleDescLocator).sendKeys("Head");
        driver.findElement(metaDescLocator).sendKeys("Meta Description");

        return this;
    }

    private AddNewProductPage fillPricesTab(String price) {
        driver.findElement(By.xpath("//a[text()='Prices']")).click();

        WebElement purchase = driver.findElement(purchaseLocator);
        purchase.clear();
        purchase.sendKeys(Keys.HOME, price);
        WebElement purchaseCurSelector = driver.findElement(purchaseCurLocator);
        Select select = new Select(purchaseCurSelector);
        select.selectByValue("USD");
        purchase = driver.findElement(purchaseUsdLocator);
        purchase.clear();
        purchase.sendKeys(Keys.HOME, price);
        purchase = driver.findElement(purchaseEurLocator);
        purchase.clear();
        purchase.sendKeys(Keys.HOME, price);

        return this;
    }

}
