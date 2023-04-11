package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, 10);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, 10);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " in " + log + " field");
        }
    }

    public String getText(By by, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, 10);
            wdWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
            System.out.println("Get text " + log);
            return driver.findElement(by).getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Get text " + log);
            return driver.findElement(by).getText();
        }
    }
}
