package eohmc.selenium.core.testCases.om;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import eohmc.selenium.core.helperClasses.DataProviderHelper;
import eohmc.selenium.core.pagesObjects.om.HomePage;
import eohmc.selenium.core.pagesObjects.om.TaxCalculatorsPage;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jonathan Mbuyi Tshitenda on 22/05/2017.
 */
public class TS2_CheckTaxCalculator extends BaseClassHelper {

    //Create an Instance-Variable to access the TaxCalculatorsPage and HomePage objects/Elements
    TaxCalculatorsPage calculators_Page;
    HomePage home_page;
    int testRuns = intRowCount;/*Run test based on the number of data supplied*/

    @Test(priority =2, dataProvider = "taxCalculatorData",dataProviderClass = DataProviderHelper.class)
    public void checkTaxCalculators(ITestResult result,String intUserAge,String intMonthlySalary,String intOtherIncome,String intDeductionsOtherIncome,String intTaxable) throws InterruptedException, IOException {

        //Create Test Objects
        test = extent.createTest("Check Tax Calculators"); //Declare & Create Report Object
        calculators_Page = new TaxCalculatorsPage(driver);//Create Page object
        home_page = new HomePage(driver);//Create HomePage Page object
        Actions actions = new Actions(driver);//To perform web action
        SoftAssert softAssert = new SoftAssert();//To Perform SoftAssertion(Test Will continue even if an assertion fails)

        //*******************************TEST****************************************************************************


        //Click on the tools Link to access all other web features
        home_page.ClickToolsLink();
        Thread.sleep(1500);

        //Click Tax Cal link
        calculators_Page.ClickTaxCalcLink();
        this.scrollDown();

            //Perform Income Tax Calculations
            calculators_Page.EnterIncomeDetails(intUserAge, intMonthlySalary, intOtherIncome, intDeductionsOtherIncome, intTaxable);

            //Click Calculate Button
            Thread.sleep(8000);
            this.scrollDown();
            calculators_Page.clickCalculateBtn();
            this.scrollDown();

            //Verify Calculation results
            String strExpected = "R 5,610.61";
            String strActual = calculators_Page.getGrossIncome();

            if (!strExpected.equals(strActual)) {
                test.fail(MarkupHelper.createLabel("Tax Income Calculations are not correct,please check urgently; Expected " + strActual + " but found " + strExpected, ExtentColor.RED));

                File screenshotPath = captureScreen(driver, "Tax Income Calculations");
                test.fail("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
                test.fail(result.getThrowable());
            } else {
                test.info(MarkupHelper.createLabel("Test Passed::Tax Calculator is functioning as expected", ExtentColor.BLUE));
                File screenshotPath = captureScreen(driver, "Passed Test_Tax Calculator");
                test.pass("View Screenshots below: " + test.addScreenCaptureFromPath(String.valueOf(screenshotPath)));
            }
            this.scrollToTop();
            Thread.sleep(5000);
    }
}


