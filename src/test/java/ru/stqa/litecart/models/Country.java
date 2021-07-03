package ru.stqa.litecart.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Country {
    String selected;
    String color;
    int id;
    String code;
    String name;
    int zones;

    public Country(String selected, String color, int id, String code, String name, int zones) {
        this.selected = selected;
        this.color = color;
        this.id = id;
        this.code = code;
        this.name = name;
        this.zones = zones;

        System.out.println("Country with name " + name + " created successfully");
    }

    public Country(WebElement webElement) {
        this.selected = webElement.findElement(By.cssSelector("input[type=checkbox]")).getAttribute("checked");
        this.color = webElement.findElement(By.cssSelector("i.fa.fa-circle")).getAttribute("style");
        this.id = Integer.parseInt(webElement.findElement(By.xpath(".//td[3]")).getAttribute("textContent"));
        this.code = webElement.findElement(By.xpath(".//td[4]")).getAttribute("textContent");
        this.name = webElement.findElement(By.xpath(".//td[5]/a")).getAttribute("textContent");
        this.zones = Integer.parseInt(webElement.findElement(By.xpath(".//td[6]")).getAttribute("textContent"));

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

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int compareTo(Country c) {
        return Comparator.comparing(Country::getName)
                .compare(this, c);
    }
}
