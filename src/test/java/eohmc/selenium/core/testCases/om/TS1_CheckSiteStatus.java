package eohmc.selenium.core.testCases.om;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.pagesObjects.om.HomePage;
import eohmc.selenium.core.helperClasses.BrowserFactoryHelper;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


/**
 * Created by Jonathan Mbuyi Tshitenda on 09/05/2017.
 *
 * This test case checks that the site is up and running
 */
public class TS1_CheckSiteStatus extends BaseClassHelper {

    //Create an Instance-Variable to access the HomePage objects/Elements
    HomePage home_page;

    //Launch the browser with the specified URL
    WebDriver driver = BrowserFactoryHelper.startBrowser(getConfig().getChromeBrowser(),getConfig().getApplicationUrl());

    @Test(priority=1)
    public void verifyHomepageExist() throws InterruptedException, IOException {

       //Create Test Objects
        test = extent.createTest("Verify That the Home Page Exist"); //Declare & Create Report Object
        home_page = new HomePage(driver);//Create HomePage Page object

        //Capture-server-error
        try {
            driver.get(getConfig().getApplicationUrl());
            test.info(MarkupHelper.createLabel("OMCOZA is up", ExtentColor.BLUE));
        } catch (Exception e) {
            test.log(Status.FATAL,"SITE IS DOWN/500-Internal Server Error");
        }

        //*******************************ELEMENT & OBJECTS INTERACTIONS/BEHAVIORS***************************************

        //Check Login Button Exist
        if (home_page.CheckLoginButton()==true){
            test.info(MarkupHelper.createLabel("The Login form is available for use", ExtentColor.BLUE));
            home_page.ClickLogin();
        }
        else{
            test.log(Status.FAIL,"The Login form is not available for use");
        }

        //Check if the registration link is displayed
        Thread.sleep(5000);
        //Assert.assertTrue(home_page.isRegistrationDisplayed());
        if (home_page.IsRegistrationDisplayed()==true){
            test.info(MarkupHelper.createLabel("The Login Page Loaded Correctly",ExtentColor.BLUE));
            test.info(MarkupHelper.createLabel("Test Passed::The Login Page Loaded Correctly",ExtentColor.BLUE));
            File screenshotPath = captureScreen(driver,"Passed Test_Home Page");
            test.pass("View Screenshots below: "+ test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
        }
        else{
            test.log(Status.FAIL,"Login Page did not load correctly");
        }

    }
}
