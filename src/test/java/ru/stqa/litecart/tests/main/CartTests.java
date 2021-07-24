package ru.stqa.litecart.tests.main;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.stqa.BaseTest;
import ru.stqa.litecart.pages.main.*;
import ru.stqa.litecart.pages.main.blocks.CartWidget;
import ru.stqa.litecart.pages.main.blocks.TopMenu;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest {

    @Test
    public void checkAddingProductsToCartTest() {
        MainPage mainPage = new MainPage(driver);
        TopMenu topMenu = new TopMenu(driver);
        CartWidget cartWidget = new CartWidget(driver);

        mainPage.openMainPage();
        mainPage.isMainPage();

        for (int i = 1; i <= 3; i++) {

            topMenu.clickHomeButton();

            List<WebElement> popularProducts = mainPage.getPopularProductList();
            ProductPage productPage = mainPage.openFirstPopularProduct(popularProducts);

            int trashCountBeforeAdding = cartWidget.getProductCountOfCart();

            productPage.selectFirstProductSize();
            productPage.addProductToCart();
            cartWidget.waitUntilProductIsAddingToCart(trashCountBeforeAdding);

            int trashCountAfterAdding = cartWidget.getProductCountOfCart();

            assertEquals(1, trashCountAfterAdding - trashCountBeforeAdding);
        }

        CartPage cartPage = cartWidget.clickCheckoutButton();
        cartPage.isCartPage();

        while (cartPage.getShortcuts().size() > 0) {
            cartPage = cartPage.openFirstShortcut();
            int productCountBeforeDelete = cartPage.getOrderQuantity();

            cartPage.removeProductFromCart();
            cartPage.waitUntilProductIsRemovingFromOrder(productCountBeforeDelete - 1);
        }

        cartPage.removeProductFromCart();
        cartPage.waitUntilProductIsRemovingFromOrder(0);
        cartPage.isEmptyCart();

        topMenu.clickHomeButton();
    }
}
