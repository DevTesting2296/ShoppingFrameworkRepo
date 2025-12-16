package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountCreatePage {
	
	    // Global Variables :->
		WebDriver driver ;
			
		// Constructor :->
		public AccountCreatePage(WebDriver driver)
		  {
				this.driver = driver ;
				PageFactory.initElements(driver , this);
		  }
		
		/* ******************************************************** */
		
		// Locators :->
		
		By continueByEmt = By.xpath("//div[@class='pull-right']/a[text()='Continue']") ; // By Locator for Continue Button
		
		@FindBy(xpath = "//div[@class='pull-right']/a[text()='Continue']")
		WebElement continueEmt ;
		
		@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/h2/b")   // title Msg for Account Created
		WebElement titleMsgEmt ;
		
		@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/p[1]")    // Msg for Account Created
		WebElement signupMsgEmt ;
		
		
		
		/* ******************************************************** */
		
		// Account Create Page Util Methods :->
		
		// 1. Wait until Continue Button is displayed on Screen :
	    public void waitForContinueButton()
	    {
	    	WebDriverWait waitAccountCreatePage = new WebDriverWait(driver, Duration.ofMillis(2000));
	    	waitAccountCreatePage.until(ExpectedConditions.visibilityOfElementLocated(continueByEmt)); 
	    }
	    
	    // *********
	    
	   // 2. Extract Title Message and send it to TC :->
	    public String getsignUpTitleMsg()
	    {
	    	return titleMsgEmt.getText() ;	    
	    }
	    
	    // *********
	    
	    // 3. Extract SignUp Message and send it to TC :->
	    public String getsignUpMsg()
	    {
	    	return signupMsgEmt.getText() ;	    
	    }
	    
	    // *********
	    
	    // 4. Click on Continue Button :>
	    public void clickContinueButton()
	    {
	    	continueEmt.click();
	    }
		
		
		
		
		// Method to return driver object from Login Page
		public WebDriver returnDriverAccountCreatePage()
		{
		   return driver ;
		}
		
		/* ******************************************************** */
		/* ******************************************************** */

}
