package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {
	
	 // Global Variables :->
	 WebDriver driver ;
					
	 // Constructor :->
	 public ConfirmationPage(WebDriver driver)
	 {
		this.driver = driver ;
		PageFactory.initElements(driver , this);
	 }
				
	 /* ******************************************************** */
				
	 // Locators :->
	 
	 By continueByEmt = By.xpath("//a[text()='Continue']") ; // By Locator for Continue Button
	 
	 @FindBy(xpath = "//a[text()='Continue']")   // Locator for Continue Button
	 WebElement continueEmt ;
	 
	 @FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/h2/b")   // Locator for Success Message Title
	 WebElement successTitleEmt ;
	 
	 @FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/p")   // Locator for Success Message
	 WebElement successMsgEmt ;
	 
	 
	 
	 /* ******************************************************** */
	 /* ******************************************************** */
		
	 // Payment Page Util Methods :->
	
	 // 1. Wait for continue button to display :->
	 public void waitForContinueButton()
	 {
		 WebDriverWait waitConfirmPage = new WebDriverWait(driver,Duration.ofMillis(2000)) ;
		 waitConfirmPage.until(ExpectedConditions.visibilityOfElementLocated(continueByEmt)) ;
	 }
	 
	 // 2. Method to return Success Message title :->
	 public String getSuccessMsgTitle()
	 {
		 return successTitleEmt.getText() ;
	 }
	 
	 // 3. Method to return success messgage :->
	 public String getSuccessMsgText()
	 {
		 return successMsgEmt.getText() ;
	 }
	 
	 // 4. Method to click on continue Button :->
	 public void clickOnContinueButton()
	 {
		 continueEmt.click(); 
	 }
	 
	
	// Method to return driver object from Checkout Page
	   public WebDriver returnDriverConfirmationPage()
	   {
		 return driver ;
	   }
			
	/* ******************************************************** */
	/* ******************************************************** */
	
}
