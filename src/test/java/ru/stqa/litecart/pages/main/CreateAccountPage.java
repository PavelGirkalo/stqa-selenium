package ru.stqa.litecart.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.litecart.pages.Page;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateAccountPage extends Page {

    By firstNameLocator = By.name("firstname");
    By lastNameLocator = By.name("lastname");
    By mainAddressLocator = By.name("address1");
    By postcodeLocator = By.name("postcode");
    By cityLocator = By.name("city");

    By countryLocator = By.cssSelector("span.select2-selection__rendered");
    By stateLocator = By.cssSelector("select[name=zone_code]");

    By emailLocator = By.name("email");
    By phoneLocator = By.name("phone");

    By passLocator = By.name("password");
    By confirmPassLocator = By.name("confirmed_password");

    By creteAccountButtonLocator = By.name("create_account");

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public void isCreateAccountPage() {
        assertTrue(driver.findElement(By.xpath("//h1[text()='Create Account']")).isDisplayed());
    }

    public MainPage createNewAccount(String mail, String password, int suffix) {
        String firstName = "Name_" + suffix;
        String lastName = "Surname_" + suffix;

        fillFirstAndLastName(firstName, lastName);
        fillAddress("address", "93545", "Lone Pine");
        fillCountryAndState("United States", "CA");
        fillEmailAndPhone(mail, "+19873456879");
        fillPasswords(password);

        driver.findElement(creteAccountButtonLocator).click();

        return new MainPage(driver);
    }

    private CreateAccountPage fillFirstAndLastName(String firstName, String lastName) {
        WebElement firstNameField = driver.findElement(firstNameLocator);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(lastNameLocator);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        return this;
    }

    private CreateAccountPage fillAddress(String address, String postcode, String city) {
        WebElement addressField = driver.findElement(mainAddressLocator);
        addressField.clear();
        addressField.sendKeys(address);

        WebElement postcodeField = driver.findElement(postcodeLocator);
        postcodeField.clear();
        postcodeField.sendKeys(postcode);

        WebElement cityField = driver.findElement(cityLocator);
        cityField.clear();
        cityField.sendKeys(city);

        return this;
    }

    private CreateAccountPage fillCountryAndState(String country, String state) {
        WebElement countryField = driver.findElement(countryLocator);
        countryField.click();
        WebElement countrySearch = driver.findElement(By.cssSelector(".select2-search__field"));
        countrySearch.clear();
        countrySearch.sendKeys(country, Keys.ENTER);

        WebElement stateField = driver.findElement(stateLocator);
        Select selectState = new Select(stateField);
        selectState.selectByValue(state);

        return this;
    }

    private CreateAccountPage fillEmailAndPhone(String email, String phone) {
        WebElement emailField = driver.findElement(emailLocator);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement phoneField = driver.findElement(phoneLocator);
        phoneField.clear();
        phoneField.sendKeys(phone);

        WebElement newsletterCheckbox = driver.findElement(By.cssSelector("input[name=newsletter]"));
        newsletterCheckbox.click();

        return this;
    }

    private CreateAccountPage fillPasswords(String password) {
        WebElement passwordField = driver.findElement(passLocator);
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement confirmPassField = driver.findElement(confirmPassLocator);
        confirmPassField.clear();
        confirmPassField.sendKeys(password);

        return this;
    }
}
