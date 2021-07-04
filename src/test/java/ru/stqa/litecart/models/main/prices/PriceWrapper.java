package ru.stqa.litecart.models.main.prices;

import org.openqa.selenium.WebElement;

public class PriceWrapper {

    Price price;
    RegularPrice regularPrice;
    CampaignPrice campaignPrice;

    public PriceWrapper() {
    }

    public static PriceWrapper buildPriceWrapper(WebElement webElement) {
        PriceWrapper priceWrapper = new PriceWrapper();
        priceWrapper.price = Price.buildPrice(webElement);
        priceWrapper.regularPrice = RegularPrice.buildRegularPrice(webElement);
        priceWrapper.campaignPrice = CampaignPrice.buildCampaignPrice(webElement);

        return priceWrapper;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public RegularPrice getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(RegularPrice regularPrice) {
        this.regularPrice = regularPrice;
    }

    public CampaignPrice getCampaignPrice() {
        return campaignPrice;
    }

    public void setCampaignPrice(CampaignPrice campaignPrice) {
        this.campaignPrice = campaignPrice;
    }
}
