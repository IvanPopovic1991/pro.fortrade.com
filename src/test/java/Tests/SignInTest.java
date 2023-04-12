package Tests;

import Pages.SignInPage;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        baseSetUp("CHROME", "112");
    }

    @Test(description = "User successfully registered demo account on pro.fortrade.com page")
    @Description("Account is successfully registered under certain regulation.")
    @Parameters({"countryCodeNumber", "regulative"})
    public void sigInMethod(String countryCodeNumber, String regulative) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn("Testq Testa", "test" + System.currentTimeMillis() + "@mailinator.com",
                countryCodeNumber, System.currentTimeMillis() + "");
        signInPage.clickElement(signInPage.continueBtn, "Continue button");
        //Waiting for data-lcreg attribute to bi visible
        WebDriverWait wdWait = new WebDriverWait(driver, 10);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body[data-lcreg='" + regulative + "']")));
        //Finding element in Elements
        WebElement attribute = driver.findElement(By.cssSelector("body[data-lcreg='" + regulative + "']"));
        //Taking the value from attribute data-lcreg (FSC, FCA, cysec, Asic, iiroc) and storage as a String type
        String regulativeValue = attribute.getAttribute("data-lcreg");
        //Verifying they are matching
        Assert.assertEquals(regulativeValue, regulative);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
