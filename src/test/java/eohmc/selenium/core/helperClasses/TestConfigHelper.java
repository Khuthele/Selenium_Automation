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

    public String getChromePath()
    {
        String browser = properties.getProperty("chromePath");
        return browser;
    }

    public String getApplicationUrl()
    {
        String url = properties.getProperty("baseUrl");
        return url;
    }
}
