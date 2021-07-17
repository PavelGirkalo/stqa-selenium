package ru.stqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static ru.stqa.utils.Utils.getProperties;


public class BaseTest {

    public static final String BROWSER_NAME = getProperties().getProperty("browser");
    public static final String URL_FOR_GRID = getProperties().getProperty("url.grid");
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setupDriver() {
        switch (BROWSER_NAME) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "FIREFOX_ESR":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxEsrOptions = new FirefoxOptions();
                firefoxEsrOptions
                        .setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")))
                        .setCapability(FirefoxDriver.MARIONETTE, false);
                driver = new FirefoxDriver(firefoxEsrOptions);
                break;
            case "FIREFOX_NIGHTLY":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxNightlyOptions = new FirefoxOptions();
                firefoxNightlyOptions
                        .setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
                driver = new FirefoxDriver(firefoxNightlyOptions);
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "IE_REMOTE":
                try {
                    driver = new RemoteWebDriver(new URL(URL_FOR_GRID), new InternetExplorerOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "CHROME_REMOTE":
                try {
                    driver = new RemoteWebDriver(new URL(URL_FOR_GRID), new ChromeOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 3);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
