package ru.stqa.litecart.models.main.prices;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CampaignPrice {
    String campaignPriceText;
    Double campaignPriceFontSize;
    String campaignPriceColor;
    String campaignPriceWeight;

    public CampaignPrice() {
    }

    public static CampaignPrice buildCampaignPrice(WebElement webElement) {
        CampaignPrice campaignPrice = new CampaignPrice();

        try {
            webElement.findElement(By.cssSelector("strong.campaign-price"));
            campaignPrice.campaignPriceText = webElement.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");
            String fontSize = webElement.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
            campaignPrice.campaignPriceFontSize = Double.parseDouble(fontSize.substring(0, fontSize.length() - 2));
            campaignPrice.campaignPriceColor = webElement.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
            campaignPrice.campaignPriceWeight = webElement.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        } catch (NoSuchElementException ex) {
            campaignPrice = null;
        }

        return campaignPrice;
    }

    public String getCampaignPriceText() {
        return campaignPriceText;
    }

    public void setCampaignPriceText(String campaignPriceText) {
        this.campaignPriceText = campaignPriceText;
    }

    public Double getCampaignPriceFontSize() {
        return campaignPriceFontSize;
    }

    public void setCampaignPriceFontSize(Double campaignPriceFontSize) {
        this.campaignPriceFontSize = campaignPriceFontSize;
    }

    public String getCampaignPriceColor() {
        return campaignPriceColor;
    }

    public void setCampaignPriceColor(String campaignPriceColor) {
        this.campaignPriceColor = campaignPriceColor;
    }

    public String getCampaignPriceWeight() {
        return campaignPriceWeight;
    }

    public void setCampaignPriceWeight(String campaignPriceWeight) {
        this.campaignPriceWeight = campaignPriceWeight;
    }
}
