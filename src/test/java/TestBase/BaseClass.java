package TestBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
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


    @BeforeClass
    @Parameters({"browser", "os"})
    public void setUp(@Optional("firefox") String browser,
                      @Optional("windows") String os) throws IOException {

        new File("logs").mkdirs();
        p = ConfigReader.initializeProperties();

        logger = LogManager.getLogger(this.getClass());
        logger.info("==== Test Started ====");
        logger.info("Requested browser: " + browser + ", OS: " + os);
        System.out.println("Running on browser: " + browser);

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();   // ← setup Chrome driver
                driver = new ChromeDriver();
                logger.info("Launching Chrome browser");
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();// ← setup Firefox driver
               // System.setProperty("webdriver.gecko.driver", "C:\\Users\\konda\\.cache\\selenium");
                driver = new FirefoxDriver();
                logger.info("Launching Firefox browser");
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();     // ← setup Edge driver
                driver = new EdgeDriver();
                logger.info("Launching Edge browser");
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
        driver.quit();
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