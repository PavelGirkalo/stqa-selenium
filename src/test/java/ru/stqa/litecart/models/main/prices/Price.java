package ru.stqa.litecart.models.main.prices;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Price {
    String priceText;
    Double priceFontSize;
    String priceColor;

    public Price() {
    }

    public static Price buildPrice(WebElement webElement) {
        Price price = new Price();

        try {
            webElement.findElement(By.cssSelector("span.price"));
            price.priceText = webElement.findElement(By.cssSelector("span.price")).getAttribute("textContent");
            String fontSize = webElement.findElement(By.cssSelector("span.price")).getCssValue("font-size");
            price.priceFontSize = Double.parseDouble(fontSize.substring(0, fontSize.length() - 2));
            price.priceColor = webElement.findElement(By.cssSelector("span.price")).getCssValue("color");
        } catch (NoSuchElementException ex) {
            price = null;
        }

        return price;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public Double getPriceFontSize() {
        return priceFontSize;
    }

    public void setPriceFontSize(Double priceFontSize) {
        this.priceFontSize = priceFontSize;
    }

    public String getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(String priceColor) {
        this.priceColor = priceColor;
    }
}
