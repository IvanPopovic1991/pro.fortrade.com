package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.awt.SystemColor.text;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='viewLayer']//..//select[1]")
    public WebElement amountField;
    @FindBy(xpath = "")
    WebElement product;

   @FindBy(xpath = "//div[@class='ticketActionButton t_button primary'  and contains(text(),'BUY now')]")
    public WebElement buyButton;

    @FindBy(xpath = "//div[@class='ticketActionButton t_button primary' and contains(text(),'SELL now')]")
    public WebElement sellButton;

    String productCart = "//div[@class='TradesZone']//div/span[@class='webSymbol' and contains(text(),$$)]";

    public void checkProductsAreDisplayed(String... products){
        for (String product : products){
            getText(By.xpath(productCart.replace("$$",product)),""+product);
        }
    }

}
