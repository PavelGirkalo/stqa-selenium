package ru.stqa.litecart.models.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Country {
    String code;
    String name;
    int zones;

    public Country(int id, String code, String name, int zones) {
        this.code = code;
        this.name = name;
        this.zones = zones;

        System.out.println("Country with name " + name + " created successfully");
    }

    public Country(WebElement webElement) {
        List<WebElement> countryFields = webElement.findElements(By.xpath(".//td"));
        this.code = countryFields.get(3).getAttribute("textContent");
        this.name = countryFields.get(4).findElement(By.xpath("./a")).getAttribute("textContent");
        this.zones = Integer.parseInt(countryFields.get(5).getAttribute("textContent"));

        System.out.println("Country with name " + name + " created successfully");
    }

    public static List<Country> createListOfCountries(List<WebElement> webElements) {
        List<Country> countries = new ArrayList<>();
        for (WebElement element : webElements) {
            Country country = new Country(element);
            countries.add(country);
        }

        return countries;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZones() {
        return zones;
    }

    public void setZones(int zones) {
        this.zones = zones;
    }
}
