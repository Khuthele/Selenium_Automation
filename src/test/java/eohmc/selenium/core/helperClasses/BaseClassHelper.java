package eohmc.selenium.core.helperClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
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
public class BaseClassHelper {

    public static WebDriver driver;
    public static String currentPath = System.getProperty("user.dir");
    //public static String inputDataPath = currentPath+"\\docs";

//**************************************************The below Code is Generating Extent Reports Across all our Test Classes ****************************************************
    //Declare and Import Extent Reports Classes to generate Extent Reports in a Listener
    TestReportEmailHelper emailTestReport;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    //Write reusable method to capture screenshots
    public static File captureScreen(WebDriver driver, String screenShotName) throws IOException {

        /*To Create Dynamic file names based on Time Stamp*/

        // Create object of SimpleDateFormat class and decide the format
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");//dd/MM/yyyy
        //get current date time with Date()
        Date now = new Date();
        // Now format the date
        String strDate = sdfDate.format(now);

        /*Setup screenshot*/
        TakesScreenshot takeScreenShots = (TakesScreenshot)driver;
        File strSource = takeScreenShots.getScreenshotAs(OutputType.FILE);
        File strFileLocation = new File(currentPath+"\\etc\\testOutPuts\\screenshots\\"+screenShotName+"_"+strDate+".png");

        File strDestination = new File(String.valueOf(strFileLocation));
        FileUtils.copyFile(strSource,strDestination);

        return strFileLocation;
    }

    //Setup extent reporting
    @BeforeSuite(alwaysRun = true)
    public void setUpReportListener(){

        htmlReporter = new ExtentHtmlReporter(currentPath+"\\etc\\testOutPuts\\reports\\Automation_Report.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //Set Test Information withing the Report
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("Host Name","OMCOZA Automation");
        extent.setSystemInfo("Environment","Production ");
        extent.setSystemInfo("UserName","Jonathan");

        //Set Report information
        htmlReporter.config().setDocumentTitle("Old Mutual Front End Automation Report");
        htmlReporter.config().setReportName("Digital Garage");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    //Define what happens after each Test execution (Import the ITestResult interface from ExtentReports to log test results)
    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) throws IOException {

        //Log Test FAILURES
        if(result.getStatus()==ITestResult.FAILURE){

            //Declare screenshot location and name

            //Customize the appearance of the log with colors
            test.fail(MarkupHelper.createLabel(result.getName()+"::The Test Has Failed", ExtentColor.RED));
            //Get the Entire StackTrace of the failure to see the problem/issue
            test.fail(result.getThrowable());

            //Capture the screenshot
            /*test.fail("View Screenshot below: "+ test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));*/
        }
        //Log Test PASSED
        else if(result.getStatus()==ITestResult.SUCCESS){

            test.pass(MarkupHelper.createLabel(result.getName()+"::The Test Has Passed",ExtentColor.GREEN));
        }
        //Log Test SKIPPED
        else{
            test.skip(MarkupHelper.createLabel(result.getName()+"::This Test Has been Skipped",ExtentColor.AMBER));
            test.skip(result.getThrowable());
        }
    }

    //Write to Report
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        extent.flush();
        //Open the Report HTML after tests
        driver.get(currentPath+getConfig().getAutoReportPath());
        TestReportEmailHelper.emailReport();
    }

    //Create instance of the TestConfig class
    public static TestConfigHelper getConfig()
    {
        TestConfigHelper config = new TestConfigHelper();
        return config;
    }



//**************************************************The above Code is Generating Extent Reports Across all our Test Classes ****************************************************

    //**************Used to scroll the browser to the Top************
    public void scrollDown(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 650);");
    }

    //**************Used to scroll the browser to the Top************
    public void scrollDownOnFrame(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 1000);");
    }

    //**************Used to scroll the browser to the Down************
    public void scrollUp(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("scroll(0, -450);");
        jse.executeScript("window.scrollBy(0,-450)", "");
    }

    //**************Used to select dropdown items by Sending keys************
    public void selectBySendKeys(){

        //To perform web action
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).click();
        actions.build().perform();
    }

    //**************Used to select/hover on dropdown items or invisible elements ************
    public void selectHoverMove(int itemPosition){

        for(int i = 0; i <= itemPosition; i++){
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.DOWN).build().perform();//press down arrow key
            actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).build().perform();//press enter
        }

    }

    //**************Used to determine if link exist************
    public class linkDoesNotExistException extends Exception {
        public linkDoesNotExistException() {
            System.out.println("Link Does Not Exist!");
        }
    }

    //*********************************************************************************************************//
/*Below method takes the download directory and the file name, which will check for the file name mention in the
directory and will return 'True' if the document is available in the folder else 'false'. When we are sure of
the file name, we can make use of this method to verify.*/

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return flag=true;
        }
        return flag;
    }

    //**************Used to send emails ************

    public void sendEmails() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("eohmc@gmail.com", "password"));
        email.setSSLOnConnect(true);
        email.setFrom("mbikosiwapiwe@gmail.com");
        email.setSubject("OMCOZA Automation tests");
        email.setMsg("This is test mail");
        email.addTo("Siwapiwe.Mbiko@eoh.com");
        email.send();
    }

}

