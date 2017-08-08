package eohmc.selenium.core.pagesObjects.om;


import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Jonathan Mbuyi Tshitenda on 09/05/2017.
 *
 * This page houses all the Home Page elements with the Page Factory Model
 *
 * All WebElements are identified by @FindBy annotation
 */
public class HomePage extends BaseClassHelper {

    //Find the needed elements on this page
    //All WebElements are identified by @FindBy annotation

    @FindBy(how = How.XPATH,using = ".//*[@id='Head1']/title") WebElement titleText;
    @FindBy(how = How.XPATH,using = "//ul[@id='ulSocialNetwork']/li/a") WebElement toolsLink;
    @FindBy(how = How.ID,using = "login-btn") WebElement loginButton;
    @FindBy(how = How.LINK_TEXT, using = "Register for a service") WebElement RegisterLink;
    @FindBy(how = How.XPATH,using = "//*[@id=\"menu-section-mobile\"]/li[4]/a") WebElement marketLink;


    //Create a Constructor to initialize objects
    public HomePage(WebDriver localDriver){

        this.driver = localDriver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }


    //*******************************ELEMENT & OBJECTS INTERACTIONS/BEHAVIORS*******************************************

    //Get the title of the home Page
    public String getSiteTitle(){

        return titleText.getText();
    }

    //Check Login Button
    public boolean CheckLoginButton(){

        return loginButton.isDisplayed();
    }

    //Click login button
    public void ClickLogin(){
        loginButton.click();
    }

    //Check Registration Link
    public boolean IsRegistrationDisplayed(){
        return RegisterLink.isDisplayed();
    }


    //Click on the tools link
    public void ClickToolsLink() throws InterruptedException {
        //((JavascriptExecutor)driver).executeScript("arguments[0].click()",toolsLink);
        //toolsLink.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        toolsLink.click();
    }
}

