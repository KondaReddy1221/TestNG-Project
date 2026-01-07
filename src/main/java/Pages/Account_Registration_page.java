package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account_Registration_page extends Base_page
{
    public Account_Registration_page(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@id='input-lastname']")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@id='input-email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='input-telephone']")
    public WebElement telephoneField;

    @FindBy(xpath = "//input[@id='input-password']")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@id='input-confirm']")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@name='agree']")
    public WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
     public WebElement continueButton;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    public WebElement accountCreatedMessage;

    public void setFirstName(String firstName)
    {
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName)
    {
        lastNameField.sendKeys(lastName);
    }
    public void setEmail(String email)
    {
        emailField.sendKeys(email);
    }

    public void setTelephone(String telephone)
    {
        telephoneField.sendKeys(telephone);
    }

    public void setPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword)
    {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickPrivacyPolicyCheckbox()
    {
        privacyPolicyCheckbox.click();
    }

    public void clickContinueButton()
    {
        continueButton.click();
    }

    public String getAccountCreatedMessage()
    {
        try {
            return accountCreatedMessage.getText();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

}
