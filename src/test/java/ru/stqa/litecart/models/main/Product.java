package ru.stqa.litecart.models.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.litecart.models.main.prices.PriceWrapper;

import static ru.stqa.litecart.models.main.prices.PriceWrapper.buildPriceWrapper;

public class Product {
    String imageLink;
    String altImage;
    String name;
    String manufacturer;

    PriceWrapper priceWrapper;

    public Product() {
    }

    public static Product buildProductFromMainPage(WebElement webElement) {
        Product product = new Product();

        product.imageLink = webElement.findElement(By.cssSelector("img")).getAttribute("src");
        product.altImage = webElement.findElement(By.cssSelector("img")).getAttribute("alt");
        product.name = webElement.findElement(By.cssSelector("div.name")).getAttribute("textContent");
        product.manufacturer = webElement.findElement(By.cssSelector("div.manufacturer")).getAttribute("textContent");
        product.priceWrapper = buildPriceWrapper(webElement);

        return product;
    }

    public static Product buildProductFromProductInfoPage(WebElement webElement) {
        Product product = new Product();

        product.imageLink = webElement.findElement(By.cssSelector("img")).getAttribute("src");
        product.altImage = webElement.findElement(By.cssSelector("img")).getAttribute("alt");
        product.name = webElement.findElement(By.cssSelector("h1")).getAttribute("textContent");
        product.manufacturer = webElement.findElement(By.cssSelector("div.manufacturer img")).getAttribute("title");
        product.priceWrapper = buildPriceWrapper(webElement);

        return product;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PriceWrapper getPriceWrapper() {
        return priceWrapper;
    }

    public void setPriceWrapper(PriceWrapper priceWrapper) {
        this.priceWrapper = priceWrapper;
    }
}
