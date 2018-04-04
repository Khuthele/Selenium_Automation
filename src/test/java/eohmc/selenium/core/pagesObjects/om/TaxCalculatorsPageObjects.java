package eohmc.selenium.core.pagesObjects.om;
import eohmc.selenium.core.helperClasses.BaseClassHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 * Created by Jonathan Mbuyi Tshitenda on 22/05/2017.
 */
public class TaxCalculatorsPageObjects extends BaseClassHelper
{
    //All WebElements are identified by @FindBy annotation
	@FindBy(how = How.XPATH,using = "//ul/li/a[text()='TOOLS']") WebElement toolsLink;
	@FindBy(how = How.XPATH,using = "//ul/li/a[text()= 'Income Tax Calculator']") WebElement incomeTaxCalculatorLink;
    @FindBy(how = How.XPATH,using = "//div/a[text()='Retirement Savings & Risk Benefits (optional)']") WebElement RetirementBenefitsLink;
    @FindBy(how = How.ID,using = "age") WebElement ageTextBox;
    @FindBy(how = How.ID,using = "salary") WebElement salaryTextBox;
    @FindBy(how = How.ID,using = "otherincome") WebElement otherIncomeTextBox;
    @FindBy(how = How.ID,using = "deductionotherincome") WebElement deductionOtherIncomeTextBox;
    @FindBy(how = How.ID,using = "taxablecapitalgain") WebElement taxableCapitalGainTextBox;
    @FindBy(how = How.ID,using = "pensionamount") WebElement pensionAmountTextBox;
    @FindBy(how = How.ID,using = "gross_income_tax") WebElement grossIncomeOutput;
    @FindBy(how = How.ID,using = "calculate") WebElement calculateButton;
	
    //Declare Objects
    Actions actions = new Actions(driver);//To perform web action

    //Create a Constructor to initialize objects
    public TaxCalculatorsPageObjects(WebDriver localDriver)
	{
        PageFactory.initElements(localDriver ,this);
        driver = localDriver;
    }

    //*******************************ELEMENT & OBJECTS INTERACTIONS/BEHAVIORS*******************************************

    //Click on the TOOLS Link
    public void ClickToolsLink()
    {
        toolsLink.click();
    }
	
    //Click on the Tax Calculator Link
    public void ClickIncomeTaxCalculatorLink()
    {
        incomeTaxCalculatorLink.click();
    }

    //Click on the Retirement Savings & Risk Benefits (optional) Link
    public void ClickRetirementBenefitsLink()
    {
        RetirementBenefitsLink.click();
    }


    //Set User Age
    public void setUserAge(String intUserAge)
    {
        //ageTextBox.sendKeys(String.valueOf(intUserAge));
        ageTextBox.clear();
        ageTextBox.sendKeys(intUserAge);
    }

    //Set User Monthly Salary
    public void setUserMonthlySalary(String intMonthlySalary)
	{
        salaryTextBox.clear();
        salaryTextBox.sendKeys(intMonthlySalary);
    }

    //Set Other Income
    public void setOtherIncome(String intOtherIncome)
	{
        otherIncomeTextBox.clear();
        otherIncomeTextBox.sendKeys(intOtherIncome);
    }

    //Set Deductions
    public void setDeductionOtherIncome(String intDeductionsOtherIncome )
	{
        deductionOtherIncomeTextBox.clear();
        deductionOtherIncomeTextBox.sendKeys(intDeductionsOtherIncome);
    }

    //Set Tax
    public void setTaxableCapGain(String intTaxable)
	{
        taxableCapitalGainTextBox.clear();
        taxableCapitalGainTextBox.sendKeys(String.valueOf(intTaxable));
    }

    //Set Tax
    public void setPensionFundAmount(String intPensionAmount)
    {
        pensionAmountTextBox.clear();
        pensionAmountTextBox.sendKeys(String.valueOf(intPensionAmount));
    }

    //Click the Calculate Button
    public void clickCalculateButtton()
	{
        calculateButton.click();
    }

    public String getGrossIncome()
	{
        return grossIncomeOutput.getText();
    }

    public void EnterIncomeDetails(String intUserAge , String intMonthlySalary, String intOtherIncome, String intDeductionsOtherIncome,
                                   String intTaxable,String intPensionAmount)
	{
        //Fill User Age
        this.setUserAge(intUserAge);

        //Fill Salary
        this.setUserMonthlySalary(intMonthlySalary);

        //Fill Other Income
        this.setOtherIncome(intOtherIncome);

        //Fill Deductions
        this.setDeductionOtherIncome(intDeductionsOtherIncome);

        //Fill Tax
        this.setTaxableCapGain(intTaxable);

        //Fill Retirement Fund
        this.setPensionFundAmount(intPensionAmount);
    }
}
