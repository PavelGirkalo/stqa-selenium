package ru.stqa.litecart.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {
    WebDriver driver;

    public By appearanceLocator = By.xpath("//span[text()='Appearence']");
    public By appearanceTemplateLocator = By.cssSelector("#doc-template span");
    public By appearanceLogotypeLocator = By.cssSelector("#doc-logotype span");

    public By catalogLocator = By.xpath("//span[text()='Catalog']");
    public By catalogCatalogLocator = By.cssSelector("#doc-catalog span");
    public By catalogProductGroupsLocator = By.cssSelector("#doc-product_groups a");
    public By catalogOptionGroupsLocator = By.cssSelector("#doc-option_groups a");
    public By catalogManufacturersLocator = By.cssSelector("#doc-manufacturers a");
    public By catalogSuppliersLocator = By.cssSelector("#doc-suppliers a");
    public By catalogDeliveryStatusesLocator = By.cssSelector("#doc-delivery_statuses a");
    public By catalogSoldOutStatusesLocator = By.cssSelector("#doc-sold_out_statuses a");
    public By catalogQuantityUnitsLocator = By.cssSelector("#doc-quantity_units a");
    public By catalogCsvLocator = By.cssSelector("#doc-csv a");

    public By countriesLocator = By.xpath("//span[text()='Countries']");

    public By currenciesLocator = By.xpath("//span[text()='Currencies']");

    public By customersLocator = By.xpath("//span[text()='Customers']");
    public By customersCustomersLocator = By.cssSelector("#doc-customers span");
    public By customersCsvLocator = By.cssSelector("#doc-csv span");
    public By customersNewsletterLocator = By.cssSelector("#doc-newsletter span");

    public By geoZonesLocator = By.xpath("//span[text()='Geo Zones']");

    public By languagesLocator = By.xpath("//span[text()='Languages']");
    public By languagesLanguagesLocator = By.cssSelector("#doc-languages span");
    public By languagesStorageEncodingLocator = By.cssSelector("#doc-storage_encoding span");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findFirstHeader() {
        return driver.findElement(By.cssSelector("#content h1"));
    }

    public void openAppearanceMenu() {
        driver.findElement(appearanceLocator).click();
    }

    public void openAppearanceTemplate() {
        driver.findElement(appearanceLocator).click();
        driver.findElement(appearanceTemplateLocator).click();
    }

    public void openAppearanceLogotype() {
        driver.findElement(appearanceLocator).click();
        driver.findElement(appearanceLogotypeLocator).click();
    }

    public void openCatalogMenu() {
        driver.findElement(catalogLocator).click();
    }

    public void openCatalogCatalog() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogCatalogLocator).click();
    }

    public void openCatalogProductGroups() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogProductGroupsLocator).click();
    }

    public void openCatalogOptionGroups() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogOptionGroupsLocator).click();
    }

    public void openCatalogManufacturers() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogManufacturersLocator).click();
    }

    public void openCatalogSuppliers() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogSuppliersLocator).click();
    }

    public void openDeliveryStatuses() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogDeliveryStatusesLocator).click();
    }

    public void openCatalogSoldOutStatuses() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogSoldOutStatusesLocator).click();
    }

    public void openCatalogQuantityUnits() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogQuantityUnitsLocator).click();
    }

    public void openCatalogCsv() {
        driver.findElement(catalogLocator).click();
        driver.findElement(catalogCsvLocator).click();
    }

    public void openCountriesMenu() {
        driver.findElement(countriesLocator).click();
    }

    public void openCurrenciesMenu() {
        driver.findElement(currenciesLocator).click();
    }

    public void openCustomersMenu() {
        driver.findElement(customersLocator).click();
    }

    public void openCustomersCustomers() {
        driver.findElement(customersLocator).click();
        driver.findElement(customersCustomersLocator).click();
    }

    public void openCustomersCsv() {
        driver.findElement(customersLocator).click();
        driver.findElement(customersCsvLocator).click();
    }

    public void openCustomersNewsletter() {
        driver.findElement(customersLocator).click();
        driver.findElement(customersNewsletterLocator).click();
    }

    public void openGeoZonesMenu() {
        driver.findElement(geoZonesLocator).click();
    }
    public void openLanguagesMenu() {
        driver.findElement(languagesLocator).click();
    }

    public void openLanguagesLanguages() {
        driver.findElement(languagesLocator).click();
        driver.findElement(languagesLanguagesLocator).click();
    }

    public void openLanguagesStorageEncoding() {
        driver.findElement(languagesLocator).click();
        driver.findElement(languagesStorageEncodingLocator).click();
    }
}
