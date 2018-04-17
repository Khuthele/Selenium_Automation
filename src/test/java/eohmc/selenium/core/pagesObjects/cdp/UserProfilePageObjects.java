/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eohmc.selenium.core.pagesObjects.cdp;

import eohmc.selenium.core.helperClasses.BaseClassHelper;
import static eohmc.selenium.core.helperClasses.BaseClassHelper.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 *
 * @author KhutheleM
 */
public class UserProfilePageObjects extends BaseClassHelper
{
    //Create a Constructor to initialize objects
    public UserProfilePageObjects(WebDriver localDriver)
    {
        PageFactory.initElements(localDriver, this);
        driver = localDriver;
    }

    //All WebElements are identified by @FindBy annotation
    @FindBy(how = How.XPATH,using = "//td/a[text()='View']") WebElement viewButton;
    @FindBy(how = How.XPATH,using = "//div/a/p[text()='Career Development']") WebElement homeButton;
    @FindBy(how = How.XPATH,using = "//td/a[text()='Edit']") WebElement editButton;
    @FindBy(how = How.ID,using = "FacilitatorId") WebElement facilitatedByDropDownButton;
    @FindBy(how = How.ID,using = "Venue") WebElement venueField;
    @FindBy(how = How.ID,using = "fromDate") WebElement meetingDateButton;
    @FindBy(how = How.ID,using = "meetingTime") WebElement meetingTimeDropDownButton;
    @FindBy(how = How.ID,using = "saveDetails")  WebElement saveButton;
    @FindBy(how = How.ID,using = "tabAssesment")  WebElement assessmentTabButton;
    @FindBy(how = How.ID,using = "Goal")  WebElement assesmentGoalsField;
    @FindBy(how = How.ID,using = "saveAssesment")  WebElement saveAssessmentButton;
    @FindBy(how = How.ID,using = "tabActions")  WebElement actionsTabButton;
    @FindBy(how = How.ID,using = "CategoryId")  WebElement actionsCategoryDropDwonButton;
    @FindBy(how = How.ID,using = "Description")  WebElement actionsDescriptionField;
    @FindBy(how = How.ID,using = "TargetDate")  WebElement actionsTargetDateDropDownnButton;
    @FindBy(how = How.CLASS_NAME,using = "addBtnBackgr")  WebElement atcionsAddButton;
    @FindBy(how = How.ID,using = "tabSummary")  WebElement summaryTab;

    //Wait for the view button
    public boolean isViewButtonDisplayed()
    {
        return viewButton.isDisplayed();
    }

    //Click view button
    public void clickViewButton()
    {
        viewButton.click();
    }

    //Wait for the home button
    public boolean isHomeButtonDisplayed()
    {
        return homeButton.isDisplayed();
    }

    //Click home button
    public void clickHomeButton()
    {
        homeButton.click();
    }

    //Wait for the edit button
    public boolean isEditButtonDisplayed()
    {
       return editButton.isDisplayed();
    }

	//Click edit button
	public void clickEditButton()
    {
       editButton.click();
    }

    //Wait for the facilitated by drop down button
    public boolean isFacilitatedByDisplayed()
    {
        return facilitatedByDropDownButton.isDisplayed();
    }

    //Click facilitated by drop down button
    public void clickFacilitatedByDrownDownButton()
    {
        facilitatedByDropDownButton.click();
    }

    //Select facilitated by person
    public WebElement selectFacilitatedByPerson(String FacilitatedByPeron)
    {
        return driver.findElement(By.xpath("//div/select/option[text()='"+ FacilitatedByPeron +"']"));
    }

    //Wait for the venue field
    public boolean isVenueDisplayed()
    {
        return venueField.isDisplayed();
    }

    //Set venue name field
    public void setVenueNameField(String Venue)
    {
        venueField.clear();
        venueField.sendKeys(Venue);
    }

    //Enter venue name field
    public void enterVenueNameField(String Venue)
    {
        this.setVenueNameField(Venue);
    }

    //Wait for the meeting date by drop down button
    public boolean isMeetingDateButtonDisplayed()
    {
        return meetingDateButton.isDisplayed();
    }

    //Click meeting date by drop down button
    public void clickMeetingDateDropDownButton()
    {
        meetingDateButton.click();
    }

    //Select meeting date
    public WebElement selectMeetingDate(String MeetingDate)
    {
        return driver.findElement(By.xpath("//tr/td/div[text()='"+ MeetingDate +"']"));
    }

    //Wait for the meeting time by drop down button
    public boolean isMeetingTimeButtonDisplayed()
    {
        return meetingTimeDropDownButton.isDisplayed();
    }

    //Click meeting time by drop down button
    public void clickMeetingTimeDropDownButton()
    {
        meetingTimeDropDownButton.click();
    }

    //Select meeting time
    public WebElement selectMeetingTime(String MeetingTime)
    {
        return driver.findElement(By.xpath("//div/ul/li[text()='"+ MeetingTime +"']"));
    }

    //wait for Next button
    public boolean IsSaveButtonDisplayed()
    {
      return saveButton.isDisplayed();
    }

    //Click save button
    public void clickSaveButton()
    {
        saveButton.click();
    }

    //Click ok pop up button
    public void clickOKButton()
    {
        driver.switchTo().alert().accept();
    }

    //wait for Next button
    public boolean isAssessmentTabButtonDisplayed()
    {
        return assessmentTabButton.isDisplayed();
    }

    //Click save button
    public void clickAssessmentTabButton()
    {
        assessmentTabButton.click();
    }

    //Wait for the assessment goal field
    public boolean isAssessmentGoalsFieldDisplayed()
    {
        return assesmentGoalsField.isDisplayed();
    }

    //Set assessment goal field
    public void enterAssessmentGoalField(String AssesssmentGoalField)
    {
        assesmentGoalsField.clear();
        assesmentGoalsField.sendKeys(AssesssmentGoalField);
    }

    //Wait for the assessment save button
    public boolean isAssessmentSaveButtonDisplayed()
    {
        return saveAssessmentButton.isDisplayed();
    }

    //Click save assessment save button
    public void clickAssessmentSaveButtonKButton()
    {
        saveAssessmentButton.click();
    }

    //Wait for the actions tab
    public boolean isActionsTabButtonDisplayed()
    {
        return  actionsTabButton.isDisplayed();
    }

    //Click actions tab button
    public void clickActionsTabButton()
    {
        actionsTabButton.click();
    }

    //Wait for the category Drop Down Button
    public boolean isActionsCategoryDropButtonDisplayed()
    {
        return  actionsCategoryDropDwonButton.isDisplayed();
    }

    //Click category Drop Down Button
    public void clickCategoryDropDownButton()
    {
        actionsCategoryDropDwonButton.click();
    }

    //Select category type
    public WebElement selectCategoryType(String Category)
    {
        return driver.findElement(By.xpath("//div/select/option[text()='"+ Category +"']"));
    }

    //Wait for the assessment goal field
    public boolean isActionsDescriptionFieldDisplayed()
    {
        return actionsDescriptionField.isDisplayed();
    }

    //Set action description field
    public void enterActionsDescriptionField(String ActionsDescriptionField)
    {
        actionsDescriptionField.clear();
        actionsDescriptionField.sendKeys(ActionsDescriptionField);
    }

    //Wait for the target date drop down button
    public boolean isActionsTargetDateButtonDisplayed()
    {
        return actionsTargetDateDropDownnButton.isDisplayed();
    }

    //Click summary tab button
    public void clickActionsTargetDateDropDownButton()
    {
        actionsTargetDateDropDownnButton.click();
    }

    //Select meeting date
    public WebElement selectTargetDate(String TargetDate)
    {
        return driver.findElement(By.xpath("//tr/td/div[text()='"+ TargetDate +"']"));
    }

    //Wait for the summary tab button
    public boolean isActionsAddButtonDisplayed()
    {
        return summaryTab.isDisplayed();
    }

    //Click summary tab button
    public void clickActionsAddButton()
    {
        summaryTab.click();
    }

    //Wait for the summary tab button
    public boolean isSummaryTabButtonDisplayed()
    {
        return summaryTab.isDisplayed();
    }

    //Click summary tab button
    public void clickSummaryTabButton()
    {
        summaryTab.click();
    }
}
