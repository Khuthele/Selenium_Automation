package eohmc.selenium.core.testCases.om;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import org.testng.annotations.Test;
import sun.reflect.annotation.ExceptionProxy;

/**
 * Created by Jonathan Mbuyi Tshitenda on 30/05/2017.
 */

/*NB : PROXY SETTINGS: PLEASE CHECK PROXY SETTING. IF PROXY IS BLOCKED THE HTTP REQUEST WILL NOT WORK AND TEST WONT RETURN ANY LINKS*/


public class TS0_FindBrokenLinksTestClass extends BaseClassHelper
{
    @Test(priority=0)

    public void verifyAllLinks()
    {
       // String site = "https://www.oldmutual.co.za/";
        //WebDriver driver = BrowserFactoryHelper.startBrowser("chrome",site);

        //Create Test Objects
        test = extent.createTest("Check that links are up"); //Declare & Create Report Object

        //To Find all the links on the page::The Method "tagName" will check the anchor tag on the page and return a List of webElements(Use FindElementS)
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
       test.info(MarkupHelper.createLabel("The current number of links found on " + getConfig().getApplicationUrl() +" is "+ allLinks.size(), ExtentColor.GREY));
        System.out.println("Total links are " + allLinks.size());

        //Go through each individual link are verify it status
        for(int i=0;i<allLinks.size();i++)
        {
            /*Get i*/
            WebElement element = allLinks.get(i);

            /*Return the href of every link/anchor tag*/
            String url = element.getAttribute("href");

            /*Create method to validate the response*/
            validateResponse(url);
        }
        try
        {
            File screenshotPath = captureScreen(driver, "Passed Login Page.");
            test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
        }
        catch (Exception ex)
        {
            System.out.println("Screenshot Driver Exception"  + ex.getMessage());
        }
    }

    public static void validateResponse(String urlFound)
    {
        try
        {
            /*Create object of the URL class from the JAVA library and parse the url "linkUrl" that will also be parsed to the constructor*/
            URL url = new URL(urlFound);

            /*Create a connection using the URL object to return the objects of HttpURLConnection class  */
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();//OpenConnection is a methods

            /*Set a timeout to allow for waits when sending a request and receiving response*/
            httpURLConnect.setConnectTimeout(5000);

            /*Connect using the httpURLConnection object created above*/
            httpURLConnect.connect();

            /*Now check the response code with the getResponseCode method and check for HTTP response code 200. 200 mean ok,any other code will be an error*/
            if(httpURLConnect.getResponseCode()==200){

                /*Print the URL and print the message*/
                System.out.println(urlFound+" - "+httpURLConnect.getResponseMessage());
                test.pass(MarkupHelper.createLabel(urlFound+"_"+httpURLConnect.getResponseMessage(),ExtentColor.GREEN));
            }
            /*If the response returns a 400 code then do the below */
            if(httpURLConnect.getResponseCode()== 404)
            {
                /*Print the URL and print the message*/
                System.out.println(urlFound + " - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
                test.fail(MarkupHelper.createLabel(urlFound + "_" + httpURLConnect.getResponseMessage()+"_"+HttpURLConnection.HTTP_NOT_FOUND,ExtentColor.RED));
            }
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] -HTTP Response Exception :" + e.getMessage());
        }
    }
}
