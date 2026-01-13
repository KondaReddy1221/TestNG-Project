package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class My_Account_page extends Base_page
{

    public My_Account_page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    public WebElement myAccountHeader;

    public  boolean verifyMyAccountHeaderIsDisplayed() {
        try {
            return(myAccountHeader.isDisplayed());
        }
        catch (Exception e) {
            return(false);
        }
    }
}
