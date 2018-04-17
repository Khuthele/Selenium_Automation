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
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import eohmc.selenium.core.pagesObjects.cdp.LoginPageObjects;
import eohmc.selenium.core.pagesObjects.cdp.UserProfilePageObjects;
import org.testng.annotations.Test;

/**
 *
 * @author KhutheleM
 */
public class userProfileTestClass extends BaseClassHelper
{
	//Create an Instance-Variable to access page objects
    LoginPageObjects loginPageObjects = new LoginPageObjects(driver);
    UserProfilePageObjects userProfilePageobjects = new UserProfilePageObjects(driver);

    @Test(dataProvider = "CDPDetailsData",dataProviderClass = DataProviderHelper.class)
    public void UserProfileDetailsTab(String UserNameField, String PassWordField,String FacilitatedBy, String Venue, String MeetingDate,String MeetingTIme,
                                      String AssessmentGoalsField, String ActionsCategory, String ActionsDescription,String ActionsTargetDate)
    {
        try
        {
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

            test = extent.createTest("Navigate To Home Page.");

            TimeUnit.SECONDS.sleep(2);

            Assertions.isTrue("true", loginPageObjects.isUserNameFieldDisplay());

            loginPageObjects.EnterUsernameDetails(UserNameField);

            if (loginPageObjects.IsNextButtonDisplayed() == true)
            {
                TimeUnit.SECONDS.sleep(3);

                loginPageObjects.clickNextButton();

                TimeUnit.SECONDS.sleep(6);

                Assertions.isTrue("true", loginPageObjects.IsPassWordFieldDisplayed());

                loginPageObjects.EnterPassWordDetails(PassWordField);

                Assertions.isTrue("true", loginPageObjects.IsSignInButtonDisplayed());

                loginPageObjects.clickSignInButton();

                Assertions.isTrue("true", loginPageObjects.IsBackButtonDisplayed());

                loginPageObjects.clickBackButton();

                test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Home Page.", ExtentColor.GREEN));
                File screenshotPath = captureScreen(driver,"Passed Home Page.");
                test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
            else
            {
                test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Home Page.",ExtentColor.RED));
                test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Home Page.");
                File screenshotPath = captureScreen(driver,"[ERROR] - Failed Home Page.");
                test.fail("Home Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }

            TimeUnit.SECONDS.sleep(8);
        }
        catch(InterruptedException ex)
        {
            System.out.println("[ERROR] - Interrupted Exception: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("[ERROR] - IO Exception: " + ex.getMessage());
        }

        try
        {
                if (userProfilePageobjects.isEditButtonDisplayed() == true)
                {
                    TimeUnit.SECONDS.sleep(8);

                    Assertions.isTrue("true", userProfilePageobjects.isViewButtonDisplayed());

                    userProfilePageobjects.clickViewButton();

                    TimeUnit.SECONDS.sleep(3);

                    Assertions.isTrue("true", userProfilePageobjects.isHomeButtonDisplayed());

                    File screenshotPath = captureScreen(driver,"[ERROR] - Failed see view Page.");
                    test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));

                    userProfilePageobjects.clickHomeButton();
                    TimeUnit.SECONDS.sleep(3);
                }
                else
                {
                    test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Home Page.",ExtentColor.RED));
                    test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Home Page.");
                    File screenshotPath = captureScreen(driver,"[ERROR] - Failed Home Page.");
                    test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
                }

                userProfilePageobjects.clickEditButton();

                TimeUnit.SECONDS.sleep(8);
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

            Assertions.isTrue("true", userProfilePageobjects.isFacilitatedByDisplayed());

            userProfilePageobjects.clickFacilitatedByDrownDownButton();

            try
            {
                if (userProfilePageobjects.IsSaveButtonDisplayed()== true)
                {
                    TimeUnit.SECONDS.sleep(4);

                    userProfilePageobjects.selectFacilitatedByPerson(FacilitatedBy).click();

                    TimeUnit.SECONDS.sleep(4);

                    Assertions.isTrue("true", userProfilePageobjects.isVenueDisplayed());

                    userProfilePageobjects.enterVenueNameField(Venue);

                    Assertions.isTrue("true", userProfilePageobjects.isMeetingDateButtonDisplayed());

                    userProfilePageobjects.clickMeetingDateDropDownButton();

                    TimeUnit.SECONDS.sleep(4);

                    if(driver.equals("chrome"))
                    {
                        userProfilePageobjects.selectMeetingDate(MeetingDate).click();
                        userProfilePageobjects.selectMeetingDate(MeetingDate).click();
                    }
                    else
                    {
                        userProfilePageobjects.selectMeetingDate(MeetingDate).click();
                    }

                    TimeUnit.SECONDS.sleep(4);

                    Assertions.isTrue("true", userProfilePageobjects.isMeetingTimeButtonDisplayed());

                    userProfilePageobjects.clickMeetingTimeDropDownButton();

                    userProfilePageobjects.selectMeetingTime(MeetingTIme).click();

                    TimeUnit.SECONDS.sleep(4);

                   userProfilePageobjects.clickSaveButton();

                    TimeUnit.SECONDS.sleep(4);

                    test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Details Tab.", ExtentColor.GREEN));
                    File screenshotPath = captureScreen(driver,"Passed user Details Tab.");
                    test.pass("Details Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));

                    userProfilePageobjects.clickOKButton();

                    test = extent.createTest("Navigate To The Summary Tab.");

                    Assertions.isTrue("true", userProfilePageobjects.isSummaryTabButtonDisplayed());

                    userProfilePageobjects.clickSummaryTabButton();

                    test.pass(MarkupHelper.createLabel("Test Passed : Successfully navigated to the Summary Tab.", ExtentColor.GREEN));
                    File screenshotPath1 = captureScreen(driver,"Passed user Summary Tab.");
                    test.pass("Summary Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath1)));
                }
                else
                {
                    test.fail(MarkupHelper.createLabel("Test Failed:[ERROR] - Failed to navigate to the Summary Tab.",ExtentColor.RED));
                    test.log(Status.FAIL,"[ERROR] - Failed to navigate to the Summary Tab.");
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

            try
            {
                test = extent.createTest("Navigate To Assessment Page.");

                Assertions.isTrue("true", userProfilePageobjects.isAssessmentTabButtonDisplayed());

                userProfilePageobjects.clickAssessmentTabButton();

                TimeUnit.SECONDS.sleep(4);

                Assertions.isTrue("true", userProfilePageobjects.isAssessmentGoalsFieldDisplayed());

                userProfilePageobjects.enterAssessmentGoalField(AssessmentGoalsField);

                Assertions.isTrue("true", userProfilePageobjects.isAssessmentSaveButtonDisplayed());

                userProfilePageobjects.clickAssessmentSaveButtonKButton();

                TimeUnit.SECONDS.sleep(4);

                File screenshotPath = captureScreen(driver,"Passed Assessment Page.");
               test.pass("Assessment Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));

                userProfilePageobjects.clickOKButton();

                test = extent.createTest("Navigate To Actions Page.");

                Assertions.isTrue("true", userProfilePageobjects.isActionsTabButtonDisplayed());

                userProfilePageobjects.clickActionsTabButton();

                TimeUnit.SECONDS.sleep(4);

                Assertions.isTrue("true", userProfilePageobjects.isActionsCategoryDropButtonDisplayed());

                userProfilePageobjects.clickCategoryDropDownButton();

                TimeUnit.SECONDS.sleep(4);

                userProfilePageobjects.selectCategoryType(ActionsCategory).click();

                Assertions.isTrue("true", userProfilePageobjects.isActionsDescriptionFieldDisplayed());

                userProfilePageobjects.isActionsDescriptionFieldDisplayed();

                userProfilePageobjects.enterActionsDescriptionField(ActionsDescription);

                Assertions.isTrue("true", userProfilePageobjects.isActionsTargetDateButtonDisplayed());

                userProfilePageobjects.clickActionsTargetDateDropDownButton();

                userProfilePageobjects.selectTargetDate(ActionsTargetDate);

                Assertions.isTrue("true", userProfilePageobjects.isActionsAddButtonDisplayed());

                userProfilePageobjects.clickActionsAddButton();

                TimeUnit.SECONDS.sleep(4);

                File screenshotPath1 = captureScreen(driver,"Passed Assessment Page.");
                test.pass("Assessment Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath1)));

                Assertions.isTrue("true", userProfilePageobjects.isSummaryTabButtonDisplayed());

                TimeUnit.SECONDS.sleep(4);

                userProfilePageobjects.clickSummaryTabButton();
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
