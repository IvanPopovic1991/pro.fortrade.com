package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
