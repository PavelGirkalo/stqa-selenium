package ru.stqa.litecart.models.main.prices;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class RegularPrice {
    String regularPriceText;
    Double regularPriceFontSize;
    String regularPriceColor;
    String regularPriceDecoration;

    public RegularPrice() {
    }

    public static RegularPrice buildRegularPrice(WebElement webElement) {
        RegularPrice regularPrice = new RegularPrice();

        try {
            webElement.findElement(By.cssSelector("s.regular-price"));
            regularPrice.regularPriceText = webElement.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
            String fontSize = webElement.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
            regularPrice.regularPriceFontSize = Double.parseDouble(fontSize.substring(0, fontSize.length() - 2));
            regularPrice.regularPriceColor = webElement.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
            regularPrice.regularPriceDecoration = webElement.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");
        } catch (NoSuchElementException ex) {
            regularPrice = null;
        }

        return regularPrice;
    }

    public String getRegularPriceText() {
        return regularPriceText;
    }

    public void setRegularPriceText(String regularPriceText) {
        this.regularPriceText = regularPriceText;
    }

    public Double getRegularPriceFontSize() {
        return regularPriceFontSize;
    }

    public void setRegularPriceFontSize(Double regularPriceFontSize) {
        this.regularPriceFontSize = regularPriceFontSize;
    }

    public String getRegularPriceColor() {
        return regularPriceColor;
    }

    public void setRegularPriceColor(String regularPriceColor) {
        this.regularPriceColor = regularPriceColor;
    }

    public String getRegularPriceDecoration() {
        return regularPriceDecoration;
    }

    public void setRegularPriceDecoration(String regularPriceDecoration) {
        this.regularPriceDecoration = regularPriceDecoration;
    }
}
