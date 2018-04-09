package eohmc.selenium.core.pagesObjects.cdp;

import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by KhutheleM on 2018/03/20.
 */
public class LoginPageObjects extends BaseClassHelper
{
    //To perform web action
    Actions actions = new Actions(driver);

    //Create a Constructor to initialize objects
    public LoginPageObjects(WebDriver localDriver)
    {
        PageFactory.initElements(localDriver, this);
        driver = localDriver;
    }

    //All WebElements are identified by @FindBy annotation
    @FindBy(how = How.ID,using = "i0116") WebElement userNameField;
    @FindBy(how = How.ID,using = "idSIButton9")  WebElement nextButton;
    @FindBy(how = How.ID,using = "i0118")  WebElement passWordField;
    @FindBy(how = How.ID,using = "idSIButton9")  WebElement signInButton;
    @FindBy(how = How.ID,using = "idBtn_Back")  WebElement backButton;

    //Wait username field: format email address
	public boolean isUserNameFieldDisplay()
	{
		return userNameField.isDisplayed();
	}
	
    //Enter username field: format email address
    public void setUserNameField(String UserNameField)
    {
        userNameField.clear();
        userNameField.sendKeys(UserNameField);
    }

    //Wait password field
    public boolean IsPassWordFieldDisplayed()
    {
        return passWordField.isDisplayed();
    }

    //Enter password field
    public void setPassWordField(String PassWordField)
    {
        passWordField.clear();
        passWordField.sendKeys(PassWordField);
    }

    public void EnterUsernameDetails(String UserNameField)
    {
        //Fill in the values below
        this.setUserNameField(UserNameField);
    }
	
	public void EnterPassWordDetails(String PassWordField)
    {
        //Fill in the values below
        this.setPassWordField(PassWordField);
    }

    //wait for Next button
    public boolean IsNextButtonDisplayed()
    {
      return nextButton.isDisplayed();
    }

    //Click Next button
    public void clickNextButton()
    {
        nextButton.click();
    }

    //wait for SignIn button
    public boolean IsSignInButtonDisplayed()
    {
        return signInButton.isDisplayed();
    }

    //Click sign in button
    public void clickSignInButton()
    {
        signInButton.click();
    }

    //wait for Back button
    public boolean IsBackButtonDisplayed()
    {
        return backButton.isDisplayed();
    }

    //Click Back in button
    public void clickBackButton()
    {
        backButton.click();
    }


    //This will scroll up the web form
    public void scrollUp()
    {
        actions.sendKeys(Keys.PAGE_UP).click();
        actions.build().perform();
    }

    //This will to scroll down the web form
    public void scrollDown()
    {
        actions.sendKeys(Keys.PAGE_DOWN).click();
        actions.build().perform();
    }
}
