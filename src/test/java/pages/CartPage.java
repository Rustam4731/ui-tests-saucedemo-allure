package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By cartItem = By.cssSelector(".cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if cart has items")
    public boolean hasItems() {
        return isElementVisible(cartItem);
    }

    @Step("Go to checkout")
    public void checkout() {
        click(checkoutButton);
    }

    @Step("Continue shopping")
    public void continueShopping() {
        click(continueShoppingButton);
    }
}