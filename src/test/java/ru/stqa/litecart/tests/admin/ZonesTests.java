package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.login.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

public class ZonesTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        String baseUrl = "http://localhost/litecart/admin";
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl);
        loginPage.successLoginByAdmin("admin", "admin");
        Assertions.assertTrue(isElementPresent(driver, By.id("sidebar")));
    }

    @DisplayName("Check sorting of zones by alphabetic")
    @Test
    public void checkSortingOfZonesTest() {
        driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();
        List<WebElement> webElements = driver.findElements(By.cssSelector("form[name=geo_zones_form] tr.row"));

        List<String> countryNames = webElements.stream()
                .map(webElement -> webElement.findElement(By.xpath(".//td[3]")).getText())
                .collect(Collectors.toList());

        for (String countryName : countryNames) {
            driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();
            driver.findElement(By.xpath(".//a[text()='" + countryName + "']")).click();

            List<WebElement> zones = driver.findElements(By.cssSelector("table#table-zones tr"));
            List<WebElement> filteredZones = zones.subList(1, zones.size() - 1);
            System.out.println("Number of zones from country " + countryName + ": " + filteredZones.size());

            List<String> zoneNames = filteredZones.stream()
                    .map(webElement -> webElement.findElement(By.xpath(".//td[3]/select/option[@selected='selected']")).getText())
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
