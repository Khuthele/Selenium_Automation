package eohmc.selenium.core.helperClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static eohmc.selenium.core.helperClasses.BaseClassHelper.currentPath;
import static eohmc.selenium.core.helperClasses.BaseClassHelper.getConfig;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * Created by Jonathan Mbuyi Tshitenda on 10/05/2017.
 *
 * This Class Calls on the respective browsers as per the test requirements
 */
public class BrowserFactoryHelper
{

    //Global Variable
    static WebDriver driver;

    public static WebDriver startBrowser(String browserName ,String url)
    {
        if(browserName.equalsIgnoreCase("firefox"))
        {
            //Initialize Firefox driver
            //Get the firefox driver path from TestConfig properties file
            System.setProperty("webdriver.gecko.driver", currentPath + getConfig().getFirefoxDriverPath());
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("chrome"))
        {
            //Initialize chrome driver
            //Get the chrome driver path from TestConfig properties file
            System.setProperty("webdriver.chrome.driver", currentPath + getConfig().getChromeDriverPath());
            driver = new ChromeDriver();
        }
        else 
        {
			//Initialize internet explorer driver
            //Get the internet explorer driver path from TestConfig properties file
            System.setProperty("webdriver.ie.driver", currentPath + getConfig().getInterExplorerDriverPath());
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
            driver = new InternetExplorerDriver(capabilities);
        }
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//      driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url); 
        return driver;
    }
}
