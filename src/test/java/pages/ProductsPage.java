package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private final By pageTitle = By.cssSelector(".title");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By cartButton = By.cssSelector(".shopping_cart_link");
    private final By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get page title")
    public String getPageTitle() {
        return getText(pageTitle);
    }

    @Step("Add backpack to cart")
    public void addBackpackToCart() {
        click(addToCartBackpack);
    }

    @Step("Remove backpack from cart")
    public void removeBackpackFromCart() {
        click(removeBackpack);
    }

    @Step("Get cart items count")
    public String getCartItemsCount() {
        return isElementVisible(cartBadge) ? getText(cartBadge) : "0";
    }

    @Step("Logout")
    public void logout() {
        click(menuButton);
        waitForElement(logoutLink);
        click(logoutLink);
    }

    @Step("Go to cart")
    public void goToCart() {
        click(cartButton);
    }
}