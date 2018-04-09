package eohmc.selenium.core.testCases.cdp;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mongodb.assertions.Assertions;
import eohmc.selenium.core.helperClasses.DataProviderHelper;
import eohmc.selenium.core.pagesObjects.cdp.LoginPageObjects;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

/**
 * Created by KhutheleM on 2018/03/20.
 */
public class loginTestClass extends BaseClassHelper
{
    //Create an Instance-Variable to access page objects
    LoginPageObjects careerDevelopmentPageObjects;

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

                Thread.sleep(3000);

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

            Thread.sleep(3000);
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
