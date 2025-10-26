package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

@Epic("SauceDemo UI Tests")
@Feature("Login Functionality")
@Owner("Your Name")
public class LoginTest extends BaseTest {

    @Test(description = "Successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to login with valid credentials")
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "User should be redirected to products page after successful login");
    }

    @Test(description = "Failed login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("User should see error message with invalid credentials")
    public void failedLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid login");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should contain appropriate text");
    }

    @Test(description = "Login with locked out user")
    @Severity(SeverityLevel.NORMAL)
    @Story("Locked out user should not be able to login")
    public void lockedOutUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for locked out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"),
                "Error message should indicate user is locked out");
    }
}