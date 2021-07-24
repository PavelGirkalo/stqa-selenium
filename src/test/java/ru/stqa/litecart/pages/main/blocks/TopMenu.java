package ru.stqa.litecart.pages.main.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.litecart.pages.Page;

public class TopMenu extends Page {

    @FindBy(css = "i[title=Home]")
    WebElement homeButton;

    public TopMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickHomeButton() {
        homeButton.click();
    }
}
