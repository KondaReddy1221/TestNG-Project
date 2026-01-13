package TestBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    protected Logger logger;
    public Properties p;
    protected WebDriverWait wait;


    @BeforeClass
    @Parameters({"browser", "os"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("windows") String os) throws IOException {

        new File("logs").mkdirs();
        p = ConfigReader.initializeProperties();

        logger = LogManager.getLogger(this.getClass());
        logger.info("==== Test Started ====");
        System.out.println("Running on browser: " + browser);

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();   // ← setup Chrome driver
                driver = new ChromeDriver();
                break;

            case "firefox":
                // use WebDriverManager and FirefoxOptions instead of hard-coded System.setProperty path
                WebDriverManager.firefoxdriver().setup(); // ← setup Firefox driver
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                // use WebDriverManager and EdgeOptions instead of hard-coded System.setProperty path
                WebDriverManager.edgedriver().setup();     // ← setup Edge driver
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;


            default:
                logger.error("Browser not supported: " + browser + ". Launching Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(p.getProperty("baseURL"));
    }



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Browser closed");
            } catch (Exception e) {
                logger.warn("Error during driver.quit(): " + e.getMessage());
            }
        } else {
            logger.warn("Driver was null in tearDown()");
        }
    }

    protected String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    protected String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    protected String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);
        return (str + "@" + num);
    }
}