package eohmc.selenium.core.pagesObjects.om;


import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Jonathan Mbuyi Tshitenda on 22/05/2017.
 */
public class TaxCalculatorsPage extends BaseClassHelper {

    //All WebElements are identified by @FindBy annotation
    @FindBy(how = How.LINK_TEXT,using = "Income Tax Calculator") WebElement taxCalculatorLink;
    @FindBy(how = How.ID,using = "age") WebElement ageTextBox;
    @FindBy(how = How.ID,using = "salary") WebElement salaryTextBox;
    @FindBy(how = How.ID,using = "otherincome") WebElement otherIncomeTextBox;
    @FindBy(how = How.ID,using = "deductionotherincome") WebElement deductionOtherIncomeTextBox;
    @FindBy(how = How.ID,using = "taxablecapitalgain") WebElement taxableCapitalGainTextBox;
    @FindBy(how = How.ID,using = "calculate") WebElement calculateButton;
    @FindBy(how = How.ID,using = "gross_income_tax") WebElement grossIncomeOutput;

     //Declare Objects
    Actions actions = new Actions(driver);//To perform web action


    //Create a Constructor to initialize objects
    public TaxCalculatorsPage(WebDriver localDriver){
        PageFactory.initElements(localDriver ,this);

        this.driver = localDriver;
    }

    //*******************************ELEMENT & OBJECTS INTERACTIONS/BEHAVIORS*******************************************

    //Click on the Tax Calculator Link
    public void ClickTaxCalcLink(){
        taxCalculatorLink.click();
    }

    //Set User Age
    public void setUserAge(String intUserAge){
        //ageTextBox.sendKeys(String.valueOf(intUserAge));
        ageTextBox.sendKeys(intUserAge);
    }

    //Set User Monthly Salary
    public void setUserMonthlySalary(String intMonthlySalary){
        //salaryTextBox.sendKeys(String.valueOf(intMonthlySalary));
        salaryTextBox.sendKeys(intMonthlySalary);
    }

    //Set Other Income
    public void setOtherIncome(String intOtherIncome){
        //otherIncomeTextBox.sendKeys(String.valueOf(intOtherIncome));
        otherIncomeTextBox.sendKeys(intOtherIncome);
    }

    //Set Deductions
    public void setDeductionOtherIncome(String intDeductionsOtherIncome ){
        //deductionOtherIncomeTextBox.sendKeys(String.valueOf(intDeductionsOtherIncome));
        deductionOtherIncomeTextBox.sendKeys(intDeductionsOtherIncome);

    }

    //Set Tax
    public void setTaxableCapGain(String intTaxable){
        taxableCapitalGainTextBox.sendKeys(String.valueOf(intTaxable));
        //taxableCapitalGainTextBox.sendKeys(intTaxable);
    }


    //Click the Calculate Button
    public void clickCalculateBtn(){
        calculateButton.click();
    }

    public String getGrossIncome(){
        return grossIncomeOutput.getText();
    }


    public void EnterIncomeDetails(String intUserAge , String intMonthlySalary, String intOtherIncome, String intDeductionsOtherIncome,
                                   String intTaxable){

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

    }
}
