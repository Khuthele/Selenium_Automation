package eohmc.selenium.core.pagesObjects.spree;

import eohmc.selenium.core.helperClasses.BaseClassHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SpreePageObjects extends BaseClassHelper
{
        //Create a Constructor to initialize objects
        public SpreePageObjects(WebDriver localDriver)
        {
            driver = localDriver;

        }

        @FindBy(how = How.XPATH,using = "//*[@id='menu']/ul/li[1]/div/ul/li[2]/a") WebElement menMenuList;
        @FindBy(how = How.XPATH,using = "//html/body/div[2]/div[3]/div[5]/div[1]/div[1]/div/a/div/img") WebElement quickViewButton;

     //Select shoe type
     public WebElement hoverByMen()
    {

        return driver.findElement(By.xpath("//ul/li[3]/a[text()='MEN']"));
    }

        //Select shoe type
        public WebElement selectShoeType(String ShoeType)
        {
           return driver.findElement(By.xpath("//ul/li/a[@data-for-category='category/mens-shoes/shoes-view-all-2495?event_category=Nav_Flyout&event_action=-Men-Shoes']/..//ul/li/a[text()='"+ShoeType+"']"));
        }
}
