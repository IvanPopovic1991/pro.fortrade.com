package Pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/screenshots/"+name+".png"));
    }
    public void reportScreenshot(String name) throws IOException {
        takeScreenshot(name);
        Path path = Paths.get("src/screenshots/"+name+".png");
        try(InputStream is= Files.newInputStream(path)){
            Allure.addAttachment(name,is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
