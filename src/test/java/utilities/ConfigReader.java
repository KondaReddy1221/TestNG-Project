
package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties initializeProperties() {
        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "test"
                + File.separator
                + "resources"
                + File.separator
                + "config.properties");
        try (FileInputStream ip = new FileInputStream(propFile)) {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
