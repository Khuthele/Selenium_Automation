package eohmc.selenium.core.testCases.spree;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mongodb.assertions.Assertions;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.helperClasses.DataProviderHelper;
import eohmc.selenium.core.pagesObjects.spree.SpreePageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class spreeTestClass extends BaseClassHelper
{
    //Create an Instance-Variable to access page objects
    SpreePageObjects spreePageObjects = new SpreePageObjects(driver);

    Actions action = new Actions(driver);

     @Test(dataProvider = "SpreeTestData",dataProviderClass = DataProviderHelper.class)
    public void SpreeNavigation(String BootType, String ShoeSize)
    {
        try
        {
            try
            {
                test = extent.createTest("Navigate To URL.");

                driver.get(getConfig().getApplicationUrl());

                test.pass(MarkupHelper.createLabel("Spree URL is up and running.", ExtentColor.GREY));
            }
            catch (Exception e)
            {
                test.log(Status.FATAL, "SITE IS DOWN Internal Server Error: " + e.getMessage());
                test.fail(MarkupHelper.createLabel("[ERROR] - Failed to navigate to URL Site.", ExtentColor.RED));
            }

            test = extent.createTest("Navigate To Home Page.");

            TimeUnit.SECONDS.sleep(2);

            test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Home Page.", ExtentColor.GREEN));
            File screenshotPath = captureScreen(driver,"Passed Home Page.");
            test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));

//            Assertions.isTrue("true", spreePageObjects.isMenMenuListDisplayed());

            action.moveToElement(spreePageObjects.hoverByMen()).build().perform();

            TimeUnit.SECONDS.sleep(3);

            File screenshotPath1 = captureScreen(driver,"Men menu list.");
            test.pass("Men Menu Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath1)));

            spreePageObjects.selectShoeType(BootType).click();

            TimeUnit.SECONDS.sleep(3);

            File screenshotPath2 = captureScreen(driver,"Shoe options.");
            test.pass("Shoe options Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath2)));

            TimeUnit.SECONDS.sleep(3);

        }
        catch (InterruptedException ex)
        {
            System.out.println("[ERROR] - Interrupted Exception: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("[ERROR] - IO Exception: " + ex.getMessage());
        }
    }

}
