package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.AllureAttachments;

public abstract class BaseTest {
    protected WebDriver driver;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(@org.testng.annotations.Optional("chrome") String browser,
                      @org.testng.annotations.Optional("false") String headless) {
        driver = createDriver(browser, Boolean.parseBoolean(headless));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    private WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("-headless");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless=new");
                return new EdgeDriver(edgeOptions);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                return new ChromeDriver(chromeOptions);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                AllureAttachments.screenshot(driver, "Failure screenshot");
                AllureAttachments.pageSource(driver, "Page source on failure");
            }
            driver.quit();
        }
    }
}