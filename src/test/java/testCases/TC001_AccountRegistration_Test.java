package testCases;

import Pages.Account_Registration_page;
import Pages.Home_Page;
import TestBase.BaseClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_AccountRegistration_Test extends BaseClass
{

    @Test
    public void verify_account_registration() {
        try {


            logger.info("---Starting TC001_AccountRegistration_Test---");

            System.out.println("Executing TC001_AccountRegistration_Test");
            Home_Page hp = new Home_Page(driver);
            hp.clickMyAccount();
            logger.info("---Click on Account link---");
            hp.clickRegisterOption();
            logger.info("---Click on Register link---");

            Account_Registration_page regpage = new Account_Registration_page(driver);
            logger.info("--- providing the user details on Register page---");
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String password = randomAlphaNumeric();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);
            regpage.clickPrivacyPolicyCheckbox();
            regpage.clickContinueButton();
            logger.info("---Validating expected message ---");
            Assert.assertEquals(regpage.getAccountCreatedMessage(), "Your Account Has Been Created!", "Account registration failed");


        }
        catch (Exception e)
        {
            logger.error("Test case TC001_AccountRegistration_Test failed");
            Assert.fail();
        }
        logger.info("---Finished TC001_AccountRegistration_Test---");
    }



}
