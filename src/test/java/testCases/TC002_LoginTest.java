package testCases;

import Pages.Home_Page;
import Pages.Login_Page;
import Pages.My_Account_page;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class TC002_LoginTest extends BaseClass {

    @Test
    public void Verify_LoginPage() {
        logger.info("---Starting TC002_LoginTest---");

        Home_Page homePage = new Home_Page(driver);
        homePage.clickMyAccount();
        homePage.clickOnLoginOption();
        logger.info("Clicked on Login Option");

        Login_Page loginPage = new Login_Page(driver);

        String email = p.getProperty("Email");
        String password = p.getProperty("Password");

        if (email == null || email.trim().isEmpty()) {
            logger.error("Email property is missing or empty in config.properties");
            Assert.fail("Missing test data: Email");
        }
        if (password == null || password.trim().isEmpty()) {
            logger.error("Password property is missing or empty in config.properties");
            Assert.fail("Missing test data: Password");
        }

        loginPage.enterEmail(email);
        logger.info("Provided Email");

        loginPage.enterPassword(password);
        logger.info("Provided Password");

        loginPage.clickLoginButton();
        logger.info("Clicked on Login Button");

        // ✅ CHECK SUCCESS OR FAILURE
        My_Account_page ma = new My_Account_page(driver);

        if (ma.verifyMyAccountHeaderIsDisplayed()) {
            logger.info("Login Successful - My Account Page Displayed");
            Assert.assertTrue(true);
        } else {
            String errorMessage = loginPage.loginFailed();
            logger.warn("Login failed with message: " + errorMessage);
            Assert.assertTrue(errorMessage.contains("Invalid"),
                    "Login failed message not displayed or did not contain 'Invalid'");
        }

        logger.info("---Finished TC002_LoginTest---");
    }

}
