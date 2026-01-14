package Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends Base_page {


    public Login_Page( WebDriver driver)
    {
        super(driver);
    }



    @FindBy(xpath ="//input[@id='input-email']" )
    public WebElement emailField;

    @FindBy(xpath ="//input[@id='input-password']" )
    public WebElement passwordField;

    @FindBy(xpath ="//input[@value='Login']" )
    public WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    public WebElement loginErrorMessage;



    public void enterEmail(String email)
    {

        emailField.sendKeys(email);
    }

    public void enterPassword(String password)

    {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton()
    {

        loginButton.click();
    }
    public String loginFailed() {
        try {
            return loginErrorMessage.getText();
        } catch (Exception e) {
            return " ";
        }
    }



}
