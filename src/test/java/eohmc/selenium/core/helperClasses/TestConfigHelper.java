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
            System.out.println("[Error] - Exception is " + e.getMessage());
        }
    }

	//Get application from properties file
    public String getApplicationUrl()
    {
        String url = properties.getProperty("baseUrl");
        return url;
    }
	
    //Get internet explorer browser from properties file
    public String getInterExplorerBrowser()
    {
        String browserIE = properties.getProperty("internetExplorerBrowser");
        return browserIE;
    }

    //Get internet explorer driver path from properties file
    public String getInterExplorerDriverPath()
    {
        String ieDriverPatch = properties.getProperty("internetExplorerPatch");
        return ieDriverPatch;
    }
	
    //Get chrome browser from properties file
    public String getChromeBrowser()
    {
        String browserChrome = properties.getProperty("chromeBrowser");
        return browserChrome;
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

	//Get firefox driver path from properties file
    public String getFirefoxBrowser()
    {
        String firefoxBrowser = properties.getProperty("firefoxBrowser");
        return firefoxBrowser;
    }

    public String getSafariBrowser()
    {
        String safariBrowser = properties.getProperty("safariBrowser");
        return safariBrowser;
    }
	
    //Get inputDataFile path from properties file
    public String getInputDataFile()
    {
        String inputDataFile = properties.getProperty("masterTestSuite");
        return inputDataFile;
    }

    public String getTestDataSheet()
    {
        String testDataSheet = properties.getProperty("testDataSheet");
        return testDataSheet;
    }

    //Get project name
    public String getProjectName()
    {
        String ProjectName = properties.getProperty("projectName");
        return ProjectName;
    }

    //Get report name
    public String getReportName()
    {
        String ReportName = properties.getProperty("reportName");
        return ReportName;
    }

    //Get operating system name
    public String getOperatingSystem()
    {
        String OperatingSystem = properties.getProperty("operatingSystem");
        return OperatingSystem;
    }

    //Get Host Name
    public String getHostName()
    {
        String HostName = properties.getProperty("hostName");
        return HostName;
    }

    //Get Test Environment
    public String getTestEnvironment()
    {
        String TestEnvironment = properties.getProperty("testEnvironment");
        return TestEnvironment;
    }

    //Get report name
    public String getUserName()
    {
        String UserName = properties.getProperty("userName");
        return UserName;
    }

    //Get automation report path from properties file
    public String getReportPath()
    {
        String autoReportPath = properties.getProperty("emailReportPath");
        return autoReportPath;
    }
}
