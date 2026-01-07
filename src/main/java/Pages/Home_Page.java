package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page extends Base_page
{


    public Home_Page( WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath ="//span[normalize-space()='My Account']" )
    public WebElement myAccountLink;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    public WebElement registerOption;

    public void clickMyAccount()
    {
        myAccountLink.click();
    }

    public void clickRegisterOption()
    {

        registerOption.click();
    }

}
