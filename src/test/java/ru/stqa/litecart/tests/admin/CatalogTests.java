package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.admin.AddNewProductPage;
import ru.stqa.litecart.pages.admin.AdminPage;
import ru.stqa.litecart.pages.login.LoginPage;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogTests extends BaseTest {

    AdminPage adminPage;
    AddNewProductPage addNewProductPage;

    @BeforeEach
    public void setUp() {
        String baseUrl = "http://localhost/litecart/admin";
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl);
        adminPage = loginPage.successLoginByAdmin("admin", "admin");
        assertTrue(isElementPresent(driver, By.id("sidebar")));
    }

    @AfterEach
    public void logout() {
        driver.findElement(By.cssSelector("[title=Logout]")).click();
        assertTrue(isElementPresent(driver, By.cssSelector("[name=username]")));
    }

    @Test
    public void addNewProductTest() {
        String name = "Trinket_" + new Random().nextInt(999999);

        adminPage.openCatalogMenu();
        List<WebElement> listBeforeCreate = driver.findElements(By.cssSelector("form[name=catalog_form] tr.row"));
        int productCountBeforeCreate = listBeforeCreate.size();
        addNewProductPage = adminPage.openNewProductPage();
        addNewProductPage.isAddNewProductPage();
        adminPage = addNewProductPage.addNewProduct(name, "productImage.png", "30");
        List<WebElement> listAfterCreate = driver.findElements(By.cssSelector("form[name=catalog_form] tr.row"));
        int productCountAfterCreate = listAfterCreate.size();

        assertEquals(1, productCountAfterCreate - productCountBeforeCreate);
        assertTrue(isElementPresent(driver, By.xpath("//a[text()='" + name + "']")));
    }

    @Test
    public void checkConsoleLogsFromAllEditProductPagesTest() {
        adminPage.openCatalogMenu();
        driver.findElement(By.xpath("//a[contains(@href,'category_id=1')]")).click();

        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']"));

        for (int i = 0; i < listOfProducts.size(); i++) {
            adminPage.openCatalogMenu();
            driver.findElement(By.xpath("//a[contains(@href,'category_id=1')]")).click();
            listOfProducts = driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']"));

            int logCountBeforeClick = driver.manage().logs().get("browser").getAll().size();
            listOfProducts.get(i).click();
            int logCountAfterClick = driver.manage().logs().get("browser").getAll().size();

            assertEquals(logCountAfterClick, logCountBeforeClick);
        }
    }
}
