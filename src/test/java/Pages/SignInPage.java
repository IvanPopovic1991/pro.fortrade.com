package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='languageDropdown languageMinimized']/div[@class='mSprite arrow']")
    WebElement languageDropdownArrow;
    @FindBy(xpath = "//div[@class='languageDropdown languageMinimized']/div[3]/div[2]")
    WebElement languageEn;
    @FindBy(xpath = "//input[@id='fullname']")
    WebElement fullName;
    @FindBy(xpath = "//input[@id='email']")
    WebElement email;
    @FindBy(xpath = "//input[@id='phoneExt']")
    WebElement countryCode;
    @FindBy(xpath = "//input[@id='signupPhone']")
    WebElement phoneNumber;
    @FindBy(xpath = "//div[@id='joinButton']")
    WebElement joinNowBtn;

    @FindBy(xpath = "//div[@id='startTradingButton']")
    public WebElement continueBtn;

    public void selectLanguage(){
        clickElement(languageDropdownArrow,"language dropdown arrow");
        clickElement(languageEn,"English language");
    }
    public void enterFullName(String fullNameValue){
        typeText(fullName,fullNameValue,"full name");
    }
    public void enterEmail(String emailValue){
        typeText(email,emailValue,"email");
    }
    public void enterCountryCodeNumber(String countryCodeNumberValue){
        typeText(countryCode,countryCodeNumberValue,"country code");
    }
    public void enterPhoneNumber(String phoneNumberValue){
        typeText(phoneNumber,phoneNumberValue,"phone number");
    }
    public void clickJoinNowBtn(){
        clickElement(joinNowBtn,"Join Now button");
    }

    public void signIn(String fullNameValue,String emailValue,String countryCodeNumberValue,String phoneNumberValue){
        //selectLanguage();
        enterFullName(fullNameValue);
        enterEmail(emailValue);
        enterCountryCodeNumber(countryCodeNumberValue);
        enterPhoneNumber(phoneNumberValue);
        clickJoinNowBtn();
    }
}
