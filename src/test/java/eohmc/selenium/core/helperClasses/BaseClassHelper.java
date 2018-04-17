package eohmc.selenium.core.helperClasses;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jonathan Mbuyi Tshitenda on 11/05/2017.
 *
 * Since all web pages have some commonalities, lets define that in a Generic Base Page
 *
 *
 * All we  are doing here is a Base Class that every page object would extend from. It has a static WebDriver, which is
 * the handle to the current browser instance [At some point when we implement DI ,we will remove the static, but for
 * now this model works for all our examples]
 */
public class BaseClassHelper
{

    public static String currentPath = System.getProperty("user.dir");
    public static int intRowCount = 0;
    public static File strFileLocation;
    //public static String inputDataPath = currentPath+"\\docs";

//**************************************************The below Code is Generating Extent Reports Across all ourTest Classes ****************************************************
    //Declare and Import Extent Reports Classes to generate Extent Reports in a Listener
    TestReportEmailHelper emailTestReport;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static  SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    static Date now = new Date();
    static String strDate,toDate;
	
	public static WebDriver driver = BrowserFactoryHelper.startBrowser(getConfig().getFirefoxBrowser(),getConfig().getApplicationUrl());

    //Write reusable method to capture screenshots
    public static File captureScreen(WebDriver driver, String screenShotName)
    {
        /*To Create Dynamic file names based on Time Stamp*/
        try
        {
            // Now format the date
            strDate = sdfDate.format(now);
            /*Setup screenshot*/
            TakesScreenshot takeScreenShots = (TakesScreenshot) driver;
            File strSource = takeScreenShots.getScreenshotAs(OutputType.FILE);
            strFileLocation = new File(currentPath + "\\etc\\testOutPuts\\screenshots\\" + screenShotName + "_" + strDate + ".png");
            File strDestination = new File(String.valueOf(strFileLocation));
            FileUtils.copyFile(strSource, strDestination);
        }
        catch(WebDriverException ex)
        {
          System.out.println("[ERROR] - WebDriver Exception: " + ex.getMessage());
        } 
		catch (IOException ex)
		{
			System.out.println("[ERROR] - screenshot file path Exception: " + ex.getMessage());
		}
        return strFileLocation;
    }

    //Setup extent reporting
    @BeforeSuite(alwaysRun = true)
    public void setUpReportListener()
    {
		toDate = sdfDate.format(now);
        htmlReporter = new ExtentHtmlReporter(currentPath+"\\etc\\testOutPuts\\reports\\Automation Test Execution Report_" + toDate + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        System.getProperties().list(System.out);

        //Set Test Information withing the Report
        extent.setSystemInfo("OS",  getConfig().getOperatingSystem());
        extent.setSystemInfo("Host Name",getConfig().getHostName());
        extent.setSystemInfo("Environment",getConfig().getTestEnvironment());
        extent.setSystemInfo("UserName",getConfig().getUserName());

        //Set Report information
        htmlReporter.getStartTime();
		htmlReporter.config().setDocumentTitle(getConfig().getProjectName());
        htmlReporter.config().setReportName(getConfig().getReportName());
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    //Define what happens after each Test execution (Import the ITestResult interface from ExtentReports to log test results)
    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) 
    {
		try
		{
        //Log Test FAILURES
        if(result.getStatus()==ITestResult.FAILURE)
        {
            //Declare screenshot location and name
            //Customize the appearance of the log with colors
            test.fail(MarkupHelper.createLabel(result.getName() + " :[ERROR]- The Test Has Failed.", ExtentColor.RED));
            //Get the Entire StackTrace of the failure to see the problem/issue
            test.fail(result.getThrowable());
            //Capture the screenshot
            test.fail("View Screenshot below: "+ test.addScreenCaptureFromPath(String.valueOf(strFileLocation)));
        }
        //Log Test PASSED
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
           test.pass(MarkupHelper.createLabel(result.getName() + " :The Test Has Passed.",ExtentColor.GREEN));
		   test.pass("View Screenshot below: "+ test.addScreenCaptureFromPath(String.valueOf(strFileLocation)));
        }
        //Log Test SKIPPED
        else
        {
           test.skip(MarkupHelper.createLabel(result.getName() + " :This Test Has been Skipped.",ExtentColor.GREY));
           test.skip(result.getThrowable());
        }
	  }
	  catch(IOException ex)
      {
		  System.out.println("[ERROR] - Get Results Exception: " + ex.getMessage());
	  }
    }

    //Write to Report
    @AfterSuite(alwaysRun = false)
    public void tearDown()
    {
		try
		{
          driver.close();
          extent.flush();
          //Open the Report HTML after tests
		  
		  TestReportEmailHelper.extent.attachReporter(htmlReporter);
          TestReportEmailHelper.emailReport();
		}
		catch (Exception ex)
		{
          System.out.println("[ERROR] - Test Report Email Exception: " + ex.getMessage());
	    }
    }

//  Create instance of the TestConfig class
    public static TestConfigHelper getConfig()
    {
        TestConfigHelper config = new TestConfigHelper();
        return config;
    }

    //**************Used to determine if link exist************
    public class linkDoesNotExistException extends Exception
    {
        public linkDoesNotExistException()
        {
            System.out.println("Link Does Not Exist!");
        }
    }

    //*********************************************************************************************************//
/*Below method takes the download directory and the file name, which will check for the file name mention in the
directory and will return 'True' if the document is available in the folder else 'false'. When we are sure of
the file name, we can make use of this method to verify.*/

    public boolean isFileDownloaded(String downloadPath, String fileName)
    {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++)
        {
            if (dir_contents[i].getName().equals(fileName))
                return flag = true;
        }
        return flag;
    }
}

