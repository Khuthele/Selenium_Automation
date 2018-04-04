package eohmc.selenium.core.testCases.om;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.pagesObjects.om.HomePagePageObjects;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
/**
 * Created by Jonathan Mbuyi Tshitenda on 09/05/2017.
 *
 * This test case checks that the site is up and running
 */
public class TS1_CheckSiteStatusTestClass extends BaseClassHelper
{
    //Create an Instance-Variable to access the HomePagePageObjects objects/Elements
    HomePagePageObjects home_page;

    @Test(priority=1)
    public void verifyHomepageExist() 
	{
	   try
	   {
		   //Create Test Objects
			test = extent.createTest("Verify the Home Page exists and navigate to the login page."); //Declare & Create Report Object
			
            //Create HomePagePageObjects Page object
			home_page = new HomePagePageObjects(driver);

			//Capture-server-error
			try
			{
				driver.get(getConfig().getApplicationUrl());
				test.info(MarkupHelper.createLabel("Old Mutual Wealth South Africa URL is up and running.", ExtentColor.GREY));
			} 
			catch (Exception e)
			{
				test.log(Status.FATAL,"SITE IS DOWN/500-Internal Server Error: " + e.getMessage());
				test.info(MarkupHelper.createLabel("[ERROR] - Failed to navigate to URL Site.", ExtentColor.GREY));
			}

			//*******************************ELEMENT & OBJECTS INTERACTIONS/BEHAVIORS**************************

			//Check Login Button Exist
			if (home_page.CheckLoginButton()==true)
			{
			//Check Login Button Exist
			if (home_page.CheckLoginButton()==true)
				test.pass(MarkupHelper.createLabel("Successfully navigated to the home page.", ExtentColor.GREEN));
				home_page.ClickLogin();
			}
			else
			{
				test.fail(MarkupHelper.createLabel("[ERROR] - Failed to navigate to the home page.", ExtentColor.RED));
				test.log(Status.FAIL,"[ERROR] - Failed to navigate to the home page.");
			}

			//Check if the registration link is displayed
			Thread.sleep(5000);
			
			//Assert.assertTrue(home_page.isRegistrationDisplayed());
			if (home_page.IsRegistrationDisplayed() == true)
			{
				test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the login page.",ExtentColor.GREEN));
				File screenshotPath = captureScreen(driver,"Passed login Page.");
				test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
			else
			{
				test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Login Page.",ExtentColor.RED));
				test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Login Page.");
				File screenshotPath = captureScreen(driver,"[ERROR] - Failed Login Page not found.");
				test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
			
		   //Check if the registration link is displayed
			Thread.sleep(5000);
			//Assert.assertTrue(home_page.isRegistrationDisplayed());
			if (home_page.IsRegistrationDisplayed() == true)
			{
				home_page.RegisterForServiceLink();
				test.pass(MarkupHelper.createLabel("Test Passed: Successfully navigated to the registration page.",ExtentColor.GREEN));
				File screenshotPath = captureScreen(driver,"Passed Registration Page.");
				test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
			else
			{
				test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Login Page.",ExtentColor.RED));
				test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Login Page.");
				File screenshotPath = captureScreen(driver,"[ERROR] - Failed Login Page not found.");
				test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
			
		    //Check if the registration link is displayed
			Thread.sleep(5000);
			//Assert.assertTrue(home_page.isRegistrationDisplayed());
			if (home_page.IsLoginButtonLinkDisplayed() == true)
			{
			   home_page.RegistrationLogin();
				Thread.sleep(5000);
					
				test.pass(MarkupHelper.createLabel("Test Passed: Successfully navigated to the Login page.",ExtentColor.GREEN));
				File screenshotPath = captureScreen(driver,"Passed Login Page.");
				test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
			else
			{
				test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the registration Page.",ExtentColor.RED));
				test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Registration Page.");
				File screenshotPath = captureScreen(driver,"[ERROR] - Failed Registration Page not found.");
				test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
			}
		  }
	   catch(InterruptedException ex)
	   {
		   System.out.println("[ERROR] - Interrupted Exception: " + ex.getMessage());
	   } 
	   catch (IOException ex)
	   {
		   System.out.println("[ERROR] - IO Exception: " + ex.getMessage());
	   }

	}
	
	//**************************************************The above Code is Generating Extent Reports Across all our Test Classes ****************************************************

    //TODO: Add scroll to bottom of the

    //**************Used to scroll the browser to the Down************
    public void scrollUp()
    {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_UP).click();
		actions.build().perform();
    }

    //**************Used to scroll the browser ************
    public void scrollDown()
    {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_UP).click();
		actions.build().perform();
    }

    //**************Used to select dropdown items by Sending keys************
    public void selectBySendKeys()
    {
        //To perform web action
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).click();
        actions.build().perform();
    }

    //**************Used to select/hover on dropdown items or invisible elements ************
    public void selectHoverMove(int itemPosition)
    {
        for(int i = 0; i <= itemPosition; i++)
        {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.DOWN).build().perform();//press down arrow key
            actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).build().perform();//press enter
        }
    }
}
