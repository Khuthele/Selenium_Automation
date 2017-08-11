package eohmc.selenium.core.helperClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import static eohmc.selenium.core.helperClasses.BaseClassHelper.currentPath;
import static eohmc.selenium.core.helperClasses.BaseClassHelper.getConfig;


/**
 * Created by Jonathan Mbuyi Tshitenda on 10/05/2017.
 *
 * This Class Calls on the respective browsers as per the test requirements
 */
public class BrowserFactoryHelper {

    //Global Variable
    static WebDriver driver;

    public static WebDriver startBrowser(String browserName ,String url){

        if(browserName.equalsIgnoreCase("firefox")){

            //Initialize Firefox driver
            //Get the firefox driver path from TestConfig properties file
            System.setProperty("webdriver.gecko.driver", currentPath+getConfig().getFirefoxDriverPath());
            driver=new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        else if(browserName.equalsIgnoreCase("chrome")){
            //Initialize chrome driver
            //Get the chrome driver path from TestConfig properties file
            System.setProperty("webdriver.chrome.driver", currentPath+getConfig().getChromeDriverPath());
            driver=new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        }
        else if(browserName.equalsIgnoreCase("IE")){

            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }
}
