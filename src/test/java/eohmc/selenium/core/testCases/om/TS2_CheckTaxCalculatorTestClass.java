package eohmc.selenium.core.testCases.om;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.helperClasses.DataProviderHelper;
import eohmc.selenium.core.pagesObjects.om.HomePagePageObjects;
import eohmc.selenium.core.pagesObjects.om.TaxCalculatorsPageObjects;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

/**
 * Created by Jonathan Mbuyi Tshitenda on 22/05/2017.
 */
public class TS2_CheckTaxCalculatorTestClass extends BaseClassHelper
{
    //Create an Instance-Variable to access the TaxCalculatorsPageObjects and HomePagePageObjects objects/Elements
    TaxCalculatorsPageObjects calculators_Page;
    HomePagePageObjects home_page;
    Actions actions;
    SoftAssert softAssert;

    /*Run test based on the number of data supplied*/
    int testRuns = intRowCount;
    String strExpected;

    @Test(priority = 3,dataProvider = "taxCalculatorData",dataProviderClass = DataProviderHelper.class)
    public void checkTaxCalculators(String intUserAge,String intMonthlySalary,String intOtherIncome,String intDeductionsOtherIncome,String intTaxable,String intPensionAmount,String strExpected) 
    {
		try
		{
        //Create Test Object
        // Declare & Create Report Object
        test = extent.createTest("Check Tax Calculators");

        //Create Page object
        calculators_Page = new TaxCalculatorsPageObjects(driver);

//         home_page = new HomePagePageObjects(driver);

            //To perform web action
          actions = new Actions(driver);

          //To Perform SoftAssertion(Test Will continue even if an assertion fails)
//          softAssert = new SoftAssert();

        //*******************************TEST****************************************************************************

        Thread.sleep(3000);  
		  
        //Click on the tools Link to access all other web features
        calculators_Page.ClickToolsLink();
		
        Thread.sleep(6000);
		
		 File screenshotPathIncomeTaxCalculator = captureScreen(driver, "Income Tax Calculator");
         test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPathIncomeTaxCalculator)));
		 	
		 Thread.sleep(6000);
		 
        //Click Tax Cal link
        calculators_Page.ClickIncomeTaxCalculatorLink();
		
		 Thread.sleep(3000);
		
        this.scrollDownOnFrame();

        //Click Retirement Savings & Risk Benefits link
        calculators_Page.ClickRetirementBenefitsLink();
		
		 Thread.sleep(5000);
		
		 File screenshotPathRetirement = captureScreen(driver, "Income Tax Retirement");
         test.info("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPathRetirement)));

        //Perform Income Tax Calculations
        calculators_Page.EnterIncomeDetails(intUserAge, intMonthlySalary, intOtherIncome, intDeductionsOtherIncome, intTaxable,intPensionAmount);

            Thread.sleep(3000);
            this.scrollDownOnFrame();
			
			//Click Calculate Button
            calculators_Page.clickCalculateButtton();
			
			this.scrollDownOnFrame();
			
			File screenshotPathCalculate = captureScreen(driver, "Income Tax Calculator");
           test.info("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPathCalculate)));
			
            //Verify Calculation results
            String strActual = calculators_Page.getGrossIncome();
			

            if (!strExpected.equals(strActual))
			{
                test.fail(MarkupHelper.createLabel("Test Failed: [ERROR] - Failed Tax Income Calculations are not correct,please check urgently; Expected " + strActual + " but found " + strExpected, ExtentColor.RED));
                File screenshotPath = captureScreen(driver, "Failed - Income Tax Calculations");
                test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
			else
			{
                test.pass(MarkupHelper.createLabel("Test Passed: Tax Calculator is functioning as expected", ExtentColor.GREEN));
                File screenshotPath = captureScreen(driver, "Passed - Income TestTax Calculator");
                test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }

            Thread.sleep(5000);

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
	
	//**************************************************The above Code is Generating Extent Reports Across all our Test Classes ****************************************************

    //**************Used to scroll the browser to the Top of the page************
    public void scrollToTop()
    {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).click();
        actions.build().perform();
    }

    //TODO: Add scroll to bottom of the

    //**************Used to scroll the browser to the Down************
    public void scrollUp()
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-450)", "");
    }

    //**************Used to scroll the browser ************
    public void scrollDown()
    {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).click();
        actions.build().perform();
    }

    //**************Used to scroll the browser to the Top************
    public void scrollDownOnFrame()
    {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).click();
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


