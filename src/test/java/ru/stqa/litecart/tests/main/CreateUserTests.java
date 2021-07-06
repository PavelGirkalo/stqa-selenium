package ru.stqa.litecart.tests.main;

import org.junit.jupiter.api.Test;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.main.CreateAccountPage;
import ru.stqa.litecart.pages.main.MainPage;

import java.util.Random;

public class CreateUserTests extends BaseTest {

    String baseUrl = "http://localhost/litecart";

    @Test
    public void checkCreatingNewAccountTest() {
        int suffix = new Random().nextInt(999999);
        String mail = "gp_" + suffix + "@gmail.com";
        String password = "qwerty";

        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);
        mainPage.isMainPage();

        CreateAccountPage createAccountPage = mainPage.moveToCreateAccountPage();
        createAccountPage.isCreateAccountPage();
        mainPage = createAccountPage.createNewAccount(mail, password, suffix);
        mainPage.isMainPage();
        mainPage.isLoggedIn();

        mainPage.logout();
        mainPage.isLoggedOut();

        mainPage.loginByUser(mail, password);
        mainPage.isLoggedIn();

        mainPage.logout();
        mainPage.isLoggedOut();
    }
}
