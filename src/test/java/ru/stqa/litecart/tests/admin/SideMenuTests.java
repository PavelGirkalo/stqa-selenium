package ru.stqa.litecart.tests.admin;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.admin.AdminPage;
import ru.stqa.litecart.pages.login.LoginPage;

import java.util.List;


public class SideMenuTests extends BaseTest {

    String baseUrl = "http://localhost/litecart/admin";
    AdminPage adminPage;

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

    @DisplayName("Test all locators from Sidebar menu")
    @Test
    public void checkAllMenuItemsTest() {
        By parentLocator = By.cssSelector(".list-vertical > li");
        By childLocator = By.cssSelector("#app- li");
        By checkedLocator = By.cssSelector("#content h1");

        List<WebElement> webElements = driver.findElements(parentLocator);
        if (areElementsPresent(driver, parentLocator)) {
            for (int i = 0; i < webElements.size(); i++) {
                webElements = driver.findElements(parentLocator);
                webElements.get(i).click();
                if(areElementsPresent(driver, childLocator)) {
                    List<WebElement> childElements = driver.findElements(childLocator);
                    for (int j = 0; j < childElements.size(); j++) {
                        childElements = driver.findElements(childLocator);
                        childElements.get(j).click();
                        Assertions.assertTrue(isElementPresent(driver, checkedLocator));
                    }
                } else {
                    Assertions.assertTrue(isElementPresent(driver, checkedLocator));
                }
            }
        } else {
            Assertions.assertTrue(isElementPresent(driver, checkedLocator));
        }
    }
}
