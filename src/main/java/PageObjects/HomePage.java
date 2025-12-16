package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	// Global Variables :->
	WebDriver driver ;
		
	// Constructor :->
	public HomePage(WebDriver driver)
	  {
			this.driver = driver ;
			PageFactory.initElements(driver , this);
	  }
	
	/* ******************************************************** */
	
	// Locators :->
	
	By loginHomeByEmt = By.xpath("//a[contains(text(),'Login')]") ; // By Locator for Login Button
	
	@FindBy(xpath="//a[contains(text(),'Login')]")  // Login Button on HomePage
	WebElement loginHomeEmt ;
	
	// *********
	
	// Locators for List of All Products and their Add Cart Buttons :->
	
	@FindBy(xpath = "//div[@class='features_items']/div[@class='col-sm-4']/div/div/div/p")  // List of all Product Titles
	List<WebElement> productTitleList ;
	
	@FindBy(xpath = "//div[@class='features_items']/div[@class='col-sm-4']/div/div/div[@class='product-overlay']/div/a[contains(text(),'Add to cart')]")
	List<WebElement> addCartButtonList ;            // List of all Add Cart Buttons for each Product
	
	// *********
	
	// Locators for Cart Verify Message which appears after Add Cart Click :->
	
	@FindBy(xpath = "//div[@class='modal-content']")  // Modal Message which appears after add Cart
	WebElement cartVerifyEmt ;
	
	By cartVerifyByEmt = By.xpath("//div[@class='modal-content']"); // By Locator of Modal Message
	
	@FindBy(xpath = "//div[@class='modal-content']/div[2]/p[2]/a/u") // View Cart Button on Modal Message
	WebElement viewCartButtonEmt ;
	
	@FindBy(xpath = "//div[@class='modal-content']/div[@class='modal-footer']/button[contains(text(),'Continue Shopping')]")
	WebElement continueButtonEmt ;                                   // Continue Shopping Button on Modal Message
	
		
    /* ******************************************************** */
	/* ******************************************************** */
	
	// Home Page Util Methods :->
	
	// 1. Method to click on Login Button from Home Page
	public void clickLoginButton()
	{
		loginHomeEmt.click();
	}
	
	// 2. Method to Scroll Down on Page after its initial Launch :->
	public void scrollDownInitialLaunch() throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
	}
	
	// 3. Method to Add Required Products in Cart :->
	public void addProdToCart(List<String> requiredProdList) throws InterruptedException
	{
		int count = 0 ;
		
		for(int i = 0 ; i < productTitleList.size() ; i++)
		{
			
			String prodElementName = productTitleList.get(i).getText() ;          // Extract Name from each prod section
						
			// Scroll down after each 3 prodcuts display 
			if(i!=0 && i%3 == 0)
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,550)");				
				Thread.sleep(1000);
			}
			
			if(requiredProdList.contains(prodElementName))   // If match is found then go inside if conditon
			{
				count++ ;
								
				// Hover on required section
				Actions aObj = new Actions(driver);
				aObj.moveToElement(productTitleList.get(i)).build().perform();
				Thread.sleep(1000);
				
				// Click on its Add Cart Button
				addCartButtonList.get(i).click();
				Thread.sleep(1000);
				
				// Wait for Cart Verify Message to appear
				WebDriverWait waitCartMessage = new WebDriverWait(driver, Duration.ofMillis(3000));
				waitCartMessage.until(ExpectedConditions.visibilityOfElementLocated(cartVerifyByEmt));
				
				//  Verify Cart Message
				String cartVerifyMessage = driver.findElement(By.xpath("//div[@class='modal-content']/div[2]/p[1]")).getText();
                if(cartVerifyMessage.contains("added"))
                {                	               				
				   // Click on Verify Cart button and break from loop if count equals list of products
				   if(count == requiredProdList.size())
				   {
					 viewCartButtonEmt.click();
					 Thread.sleep(2000);
					 break;
				   }
				
				   // Else click on Continue Shopping Button
				   else
				   {
					 continueButtonEmt.click() ;
					 Thread.sleep(1000);
				   }
				
                } // End of 2nd if condition
					
			}  // End of 1st if Condition
			
		} // End of for loop
				
	} // End of addProdToCart Method
	
	
    // 4. Wait until Login Button is displayed :->
	public void waitForLoginButton()
	{
		WebDriverWait waitHomePage = new WebDriverWait(driver,Duration.ofMillis(2000)) ;
		waitHomePage.until(ExpectedConditions.visibilityOfElementLocated(loginHomeByEmt)) ;
	}
	
	
	
	
	// Method to return driver object from Home Page
	public WebDriver returnDriverHomePage()
	{
	  return driver ;
	}
	
	/* ******************************************************** */
	/* ******************************************************** */
	

}
