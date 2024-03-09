package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement UsrNameTxtBx;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement PasswordTxtBx;

    @FindBy(css = "#login-button")
    WebElement LogInBtn;

    public void performLogin(String uname, String password){
        UsrNameTxtBx.sendKeys(uname);
        PasswordTxtBx.sendKeys(password);
        LogInBtn.click();
    }

}
