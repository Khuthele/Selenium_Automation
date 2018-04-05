/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eohmc.selenium.core.testCases.cdp;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mongodb.assertions.Assertions;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.helperClasses.DataProviderHelper;
import java.io.File;
import java.io.IOException;
import eohmc.selenium.core.pagesObjects.cdp.LoginPageObjects;
import eohmc.selenium.core.pagesObjects.cdp.UserProfilePageObjects;
import org.testng.annotations.Test;

/**
 *
 * @author KhutheleM
 */
public class userProfileTestClass extends BaseClassHelper
{
	//Create an Instance-Variable to access the TaxCalculatorsPageObjects and HomePagePageObjects objects/Elements
    LoginPageObjects careerDevelopmentPageObjects;
    UserProfilePageObjects userProfilePageobjects;

    @Test(priority = 1,dataProvider = "CDPLoginDetailsData",dataProviderClass = DataProviderHelper.class)
    public void LoginDetails(String UserNameField, String PassWordField)
    {
        try
        {
            //Create Page Objects
            careerDevelopmentPageObjects = new LoginPageObjects(driver);

            //Capture-server-error
            try
            {
                test = extent.createTest("Navigate To URL.");
                driver.get(getConfig().getApplicationUrl());
                test.pass(MarkupHelper.createLabel("Career Development Portal URL is up and running.", ExtentColor.GREY));
            }
            catch (Exception e)
            {
                test.log(Status.FATAL,"SITE IS DOWN Internal Server Error: " + e.getMessage());
                test.fail(MarkupHelper.createLabel("[ERROR] - Failed to navigate to URL Site.", ExtentColor.RED));
            }

            test = extent.createTest("Navigate To The Home Page.");

            Thread.sleep(2000);

            Assertions.isTrue("true", careerDevelopmentPageObjects.isUserNameFieldDisplay());

            careerDevelopmentPageObjects.EnterUsernameDetails(UserNameField);

            if (careerDevelopmentPageObjects.IsNextButtonDisplayed() == true)
            {
                Thread.sleep(3000);

                careerDevelopmentPageObjects.clickNextButton();

                Thread.sleep(6000);

                Assertions.isTrue("true", careerDevelopmentPageObjects.IsPassWordFieldDisplayed());

                careerDevelopmentPageObjects.EnterPassWordDetails(PassWordField);

                Assertions.isTrue("true", careerDevelopmentPageObjects.IsSignInButtonDisplayed());

                careerDevelopmentPageObjects.clickSignInButton();

                Assertions.isTrue("true", careerDevelopmentPageObjects.IsBackButtonDisplayed());

                careerDevelopmentPageObjects.clickBackButton();

                test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Home Page.", ExtentColor.GREEN));
                File screenshotPath = captureScreen(driver,"Passed Home Page.");
                test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
            else
            {
                test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Home Page.",ExtentColor.RED));
                test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Home Page.");
                File screenshotPath = captureScreen(driver,"[ERROR] - Failed Home Page.");
                test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
            Thread.sleep(6000);
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

    @Test(priority = 2)
    public void LoginDetails()
    {
        try
        {
            //Create Page Objects
            userProfilePageobjects = new UserProfilePageObjects(driver);

            //Capture-server-error
            try
            {
                test = extent.createTest("Navigate To Careers Reviews.");
                driver.get(getConfig().getApplicationUrl());
                test.pass(MarkupHelper.createLabel("Successfully navigated to the career reviews page.", ExtentColor.GREY));
            }
            catch (Exception e)
            {
                test.log(Status.FATAL,"Error - Failed to navigate to career reviews page." + e.getMessage());
                test.fail(MarkupHelper.createLabel("[ERROR] - Failed to navigate to career reviews page.", ExtentColor.RED));
            }

            Thread.sleep(3000);
          
			if (userProfilePageobjects.isEditButtonDisplayed() == true)
            {
				Thread.sleep(3000);
                test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Home Page.", ExtentColor.GREEN));
                File screenshotPath = captureScreen(driver,"Passed Home Page.");
                
		      	userProfilePageobjects.clickEditButton();
				
				test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
	
            }
            else
            {
                test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Home Page.",ExtentColor.RED));
                test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Home Page.");
                File screenshotPath = captureScreen(driver,"[ERROR] - Failed Home Page.");
                test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
            Thread.sleep(6000);
        }
        catch(InterruptedException ex)
        {
            System.out.println("[ERROR] - Interrupted Exception: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("[ERROR] - IO Exception: " + ex.getMessage());
        }

		test = extent.createTest("Navigate To The User Profile Page.");
	
	  try
       {
	     if (userProfilePageobjects.IsSaveButtonDisplayed()== true)
	     {
		    test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the User profile Page.", ExtentColor.GREEN));
            File screenshotPath = captureScreen(driver,"Passed user profile Page.");
			test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
		 }
         else
         {
            test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the User Profile Page.",ExtentColor.RED));
            test.log(Status.FAIL,"[ERROR] - Failed to navigate to the  User Profile Page.");
            File screenshotPath = captureScreen(driver,"[ERROR] - Failed  User Profile Page.");
            test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
          }
            Thread.sleep(6000);
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
}
