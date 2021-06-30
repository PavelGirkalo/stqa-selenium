package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.admin.AdminPage;
import ru.stqa.litecart.pages.login.LoginPage;


public class AdminMenuTests extends BaseTest {

    String baseUrl = "http://localhost/litecart/admin";

    AdminPage adminPage;
    WebElement h1;

    @BeforeEach
    public void setUp() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl);
        adminPage = loginPage.successLoginByAdmin("admin", "admin");
        Assertions.assertTrue(isElementPresent(driver, By.id("sidebar")));
    }

    @AfterEach
    public void logout() {
        driver.findElement(By.cssSelector("[title=Logout]")).click();
        Assertions.assertTrue(isElementPresent(driver, By.cssSelector("[name=username]")));
    }

    @DisplayName("Test all locators from Appearance menu")
    @Test
    public void checkAllAppearanceItemsTest() {
        adminPage.openAppearanceMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Template", h1.getText());

        adminPage.openAppearanceTemplate();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Template", h1.getText());

        adminPage.openAppearanceLogotype();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Logotype", h1.getText());
    }

    @DisplayName("Test all locators from Catalog menu")
    @Test
    public void checkAllCatalogItemsTest() {
        adminPage.openCatalogMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Catalog", h1.getText());

        adminPage.openCatalogCatalog();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Catalog", h1.getText());

        adminPage.openCatalogProductGroups();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Product Groups", h1.getText());

        adminPage.openCatalogOptionGroups();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Option Groups", h1.getText());

        adminPage.openCatalogManufacturers();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Manufacturers", h1.getText());

        adminPage.openCatalogSuppliers();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Suppliers", h1.getText());

        adminPage.openDeliveryStatuses();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Delivery Statuses", h1.getText());

        adminPage.openCatalogSoldOutStatuses();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Sold Out Statuses", h1.getText());

        adminPage.openCatalogQuantityUnits();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Quantity Units", h1.getText());

        adminPage.openCatalogCsv();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("CSV Import/Export", h1.getText());
    }

    @DisplayName("Test all locators from Countries menu")
    @Test
    public void checkAllCountriesItemsTest() {
        adminPage.openCountriesMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Countries", h1.getText());
    }

    @DisplayName("Test all locators from Currencies menu")
    @Test
    public void checkAllCurrenciesItemsTest() {
        adminPage.openCurrenciesMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Currencies", h1.getText());
    }

    @DisplayName("Test all locators from Customers menu")
    @Test
    public void checkAllCustomersItemsTest() {
        adminPage.openCustomersMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Customers", h1.getText());

        adminPage.openCustomersCustomers();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Customers", h1.getText());

        adminPage.openCustomersCsv();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("CSV Import/Export", h1.getText());

        adminPage.openCustomersNewsletter();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Newsletter", h1.getText());
    }

    @DisplayName("Test all locators from GeoZones menu")
    @Test
    public void checkAllGeoZonesItemsTest() {
        adminPage.openGeoZonesMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Geo Zones", h1.getText());
    }

    @DisplayName("Test all locators from Languages menu")
    @Test
    public void checkAllLanguagesItemsTest() {
        adminPage.openLanguagesMenu();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Languages", h1.getText());

        adminPage.openLanguagesLanguages();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Languages", h1.getText());

        adminPage.openLanguagesStorageEncoding();
        h1 = adminPage.findFirstHeader();
        Assertions.assertEquals("Storage Encoding", h1.getText());
    }
}
