package Tests;

import Pages.BasePage;
import Pages.ProductsPage;
import Pages.SignInPage;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class BuyProductsTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        baseSetUp("CHROME", "112");
    }

    @Test(description = "User bought product successfully")
    @Description("User bought any product successfully after registered demo account")
    @Parameters({"countryCodeNumber", "symbol", "symbolValue"})
    public void buyProduct(String countryCodeNumber, String symbol, String symbolValue) throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn("Testq Testa", "test" + System.currentTimeMillis() + "@mailinator.com",
                countryCodeNumber, System.currentTimeMillis() + "");
        signInPage.clickElement(signInPage.continueBtn, "Continue button");
        ProductsPage productsPage = new ProductsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@symbol='" + symbol + "']" +
                "/div[6]/div[3]"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@symbol='" + symbol + "']" +
                "/div[6]/div[3]")));
        WebElement product = driver.findElement(By.xpath("//div[@symbol='" + symbol + "']/div[6]/div[3]"));
        productsPage.clickElement(product, " " + symbol + " product");
        productsPage.selectElement(productsPage.amountField, "10,000", "lots");
        productsPage.clickElement(productsPage.buyButton, "buy now button");

        WebDriverWait wdWait = new WebDriverWait(driver, 10);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class," +
                "'webSymbol') and text()='"+symbolValue+"']")));
        wdWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class," +
                "'webSymbol') and text()='"+symbolValue+"']"))));
       wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'webSymbol') " +
               "and text()='"+symbolValue+"']")));
        productsPage.checkProductsAreDisplayed(symbolValue);
        new BasePage(driver).reportScreenshot("Products added to open trades position");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
