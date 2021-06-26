package ru.stqa.google;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.stqa.BaseTest;


public class GoogleTests extends BaseTest {

    String baseUrl = "https://www.google.com/";

    @Test
    public void checkSearchByGoogleTest() {
        String searchKey = "maven";

        driver.get(baseUrl);
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(searchKey);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Assertions.assertEquals(searchKey + " - Поиск в Google", driver.getTitle());
    }

    @Test
    public void checkNavigatingFromSearchPageTest() {
        String searchKey = "maven repository";

        driver.get(baseUrl);
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(searchKey);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Assertions.assertEquals(searchKey + " - Поиск в Google", driver.getTitle());
        driver.findElement(By.xpath("//h3[contains(text(),\"Maven Repository:\")]")).click();
        Assertions.assertEquals("Maven Repository: Search/Browse/Explore", driver.getTitle());
    }
}
