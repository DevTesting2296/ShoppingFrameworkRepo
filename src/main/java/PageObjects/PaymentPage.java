package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {

	    // Global Variables :->
		WebDriver driver ;
				
		// Constructor :->
		public PaymentPage(WebDriver driver)
	    {
		  this.driver = driver ;
		  PageFactory.initElements(driver , this);
		}
			
		/* ******************************************************** */
			
		// Locators :->
		
		By submitByEmt = By.id("submit") ; // By Locator for Submit Button
		
		@FindBy(id = "submit")
		WebElement submitButtonEmt ;  // Submit Button Locator
		
		@FindBy(name = "name_on_card")
		WebElement cardNameEmt ;       // Name on card Locator
		
		@FindBy(name = "card_number")
		WebElement cardNumEmt ;       // Card Number Locator
		
		@FindBy(name = "cvc")
		WebElement cvcEmt ;           // CVC Number Locator
		
		@FindBy(name = "expiry_month")
		WebElement monthEmt ;          // Expiry Month Locator
		
		@FindBy(name = "expiry_year")
		WebElement yearEmt ;          // Expiry Year Locator
		
		
		
		
		/* ******************************************************** */
		/* ******************************************************** */
		
		// Payment Page Util Methods :->
	
		// 1. Wait until Submit Button is displayed :->
		public  void waitForSubmitButton() 
		{
			WebDriverWait waitPaymentPage = new WebDriverWait(driver,Duration.ofMillis(2000)) ;
			waitPaymentPage.until(ExpectedConditions.visibilityOfElementLocated(submitByEmt)) ;
		}
		
		// 2. Enter Name on Card :->
		public void enterNameOnCard(String nameOnCard)
		{
			cardNameEmt.sendKeys(nameOnCard);
		}
		
		// 3. Enter Card Number :->
		public void enterCardNumber(String cardNumber)
		{
			cardNumEmt.sendKeys(cardNumber);		
		}
		
		// 4. Enter CVC Number :->
		public void enterCVCNumber(String cvcNumber)
		{
			cvcEmt.sendKeys(cvcNumber);		
		}
		
		// 5. Enter Exp Month :->
		public void enterExpMonth(String expMonth)
		{
			monthEmt.sendKeys(expMonth);		
		}
		
		// 6. Enter Exp Year :->
		public void enterExpYear(String expYear)
		{
			yearEmt.sendKeys(expYear);		
		}
		
	    // 7. Click on Submit Button :->
		public void clickOnsubmit()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			submitButtonEmt.click();
		}
		
		
		
		
		// Method to return driver object from Checkout Page
		   public WebDriver returnDriverPaymentPage()
		   {
			 return driver ;
		   }
				
		/* ******************************************************** */
		/* ******************************************************** */
	
	
}
