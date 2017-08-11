package eohmc.selenium.core.helperClasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Siwapiwe Mbiko on 8/10/2017.
 */
public class TestConfigHelper extends BaseClassHelper
{
    Properties properties;

    public TestConfigHelper()
    {
        //Use file class to load the property file
        File configFile = new File("./etc/TestConfig.properties");

        try
        {
            FileInputStream fileInputStream = new FileInputStream(configFile);

            properties = new Properties();
            properties.load(fileInputStream);
        }
        catch (java.io.IOException e)
        {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    //Get chrome browser from properties file
    public String getChromeBrowser()
    {
        String browserChrome = properties.getProperty("chromeBrowser");
        return browserChrome;
    }

    //Get application from properties file
    public String getApplicationUrl()
    {
        String url = properties.getProperty("baseUrl");
        return url;
    }

    //Get chrome driver path from properties file
    public String getChromeDriverPath()
    {
        String chromeDriver = properties.getProperty("chromePath");
        return chromeDriver;
    }

    //Get firefox browser from properties file
    public String getFirefoxDriverPath()
    {
        String firefoxDriver = properties.getProperty("firefoxPath");
        return firefoxDriver;
    }

    //Get inputDataFile path from properties file
    public String getInputDataFile()
    {
        String inputDataFile = properties.getProperty("masterTestSuite");
        return inputDataFile;
    }

    public String getFirefoxBrowser()
    {
        String firefoxBrowser = properties.getProperty("firefoxBrowser");
        return firefoxBrowser;
    }

    public String getIEBrowser()
    {
        String ieBrowser = properties.getProperty("ieBrowser");
        return ieBrowser;
    }
    public String getSafariBrowser()
    {
        String safariBrowser = properties.getProperty("safariBrowser");
        return safariBrowser;
    }

    public String getTestDataSheet()
    {
        String strTestDataSheet = properties.getProperty("testdatasheet");
        return strTestDataSheet;
    }



}
