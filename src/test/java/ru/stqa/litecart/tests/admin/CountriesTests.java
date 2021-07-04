package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.models.admin.Country;
import ru.stqa.litecart.pages.login.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

import static ru.stqa.litecart.models.admin.Country.createListOfCountries;

public class CountriesTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        String baseUrl = "http://localhost/litecart/admin";
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl);
        loginPage.successLoginByAdmin("admin", "admin");
        Assertions.assertTrue(isElementPresent(driver, By.id("sidebar")));
    }

    @DisplayName("Check sorting of countries and zones by alphabetic")
    @Test
    public void checkSortingOfCountriesTest() {
        driver.findElement(By.xpath("//span[text()='Countries']")).click();
        List<WebElement> webElements = driver.findElements(By.cssSelector("form[name=countries_form] tr.row"));
        List<Country> countries = createListOfCountries(webElements);

        for (int i = 1; i < countries.size(); i++) {
            Assertions.assertTrue(countries.get(i).getName().compareToIgnoreCase(countries.get(i - 1).getName()) > 0);
        }

        List<Country> countriesWithZones = countries.stream()
                .filter(country -> country.getZones() > 0)
                .collect(Collectors.toList());
        System.out.println("\nNumber of countries with zones: " + countriesWithZones.size());

        for (Country country : countriesWithZones) {
            driver.findElement(By.xpath("//span[text()='Countries']")).click();
            driver.findElement(By.xpath("//a[text()='" + country.getName() + "']")).click();

            List<WebElement> zones = driver.findElements(By.cssSelector("table#table-zones tr"));
            List<WebElement> filteredZones = zones.subList(1, zones.size() - 1);
            System.out.println("Number of zones from country " + country.getName() + ": " + filteredZones.size());

            List<String> zoneNames = filteredZones.stream()
                    .map(webElement -> webElement.findElement(By.xpath(".//td[3]")).getText())
                    .collect(Collectors.toList());

            for (int i = 1; i < zoneNames.size(); i++) {
                Assertions.assertTrue(zoneNames.get(i).compareToIgnoreCase(zoneNames.get(i - 1)) > 0);
            }
        }
    }

    @AfterEach
    public void logout() {
        driver.findElement(By.cssSelector("[title=Logout]")).click();
        Assertions.assertTrue(isElementPresent(driver, By.cssSelector("[name=username]")));
    }
}
