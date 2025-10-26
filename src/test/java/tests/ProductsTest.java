package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

@Epic("SauceDemo UI Tests")
@Feature("Products Management")
@Owner("Your Name")
public class ProductsTest extends BaseTest {

    @Test(description = "Add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to add products to cart")
    public void addProductToCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();

        Assert.assertEquals(productsPage.getCartItemsCount(), "1",
                "Cart should show 1 item after adding product");
    }

    @Test(description = "Remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("User should be able to remove products from cart")
    public void removeProductFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        productsPage.removeBackpackFromCart();

        Assert.assertEquals(productsPage.getCartItemsCount(), "0",
                "Cart should show 0 items after removing product");
    }

    @Test(description = "Logout from products page")
    @Severity(SeverityLevel.MINOR)
    @Story("User should be able to logout successfully")
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User should be redirected to login page after logout");
    }
}