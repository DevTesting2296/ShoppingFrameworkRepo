package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.CartPageData;

public class CartPage {
	
	    // Adding comments from develop branch to test branching
	
	    // Global Variables :->
		WebDriver driver ;
		
			
		// Constructor :->
		public CartPage(WebDriver driver)
		  {
				this.driver = driver ;
				PageFactory.initElements(driver , this);
		  }
		
		/* ******************************************************** */
		
		// Locators :->
	
		@FindBy(xpath = "//div[@class='col-sm-6']/a[contains(text(),'Proceed To Checkout')]")
		WebElement checkoutButtonEmt ;    // Locator for Checkout Button
		
		By checkoutByEmt = By.xpath("//div[@class='col-sm-6']/a[contains(text(),'Proceed To Checkout')]") ; // By Locator for Checkout Button
		
		@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr")  // Locator for Web Table on Cart Page
		List<WebElement> cartTableList ;
		
		@FindBy(xpath = "//div[@class='modal-body']/p[1]")      // Message on Modal Popup
		WebElement cartMsgEmt ;
		
		@FindBy(xpath = "//div[@class='modal-footer']/button[text()='Continue On Cart']")   // Continue on cart Button
		WebElement continueCartButtonEmt ;
		
		
		/* ******************************************************** */
		/* ******************************************************** */
		
		// Cart Page Util Methods :->
	
		// 1. Wait for Checkout button Display :->
		public void waitForCheckoutButton()
		{
			WebDriverWait waitCartPage = new WebDriverWait(driver,Duration.ofMillis(2000)) ;
			waitCartPage.until(ExpectedConditions.visibilityOfElementLocated(checkoutByEmt)) ;			
		}
		
		// *********
		
        // 2. Cart Table Verification :->
		// Below method will extract details from Each row of cart Table in 4 variables
		// Then they are added in array list and sent into CartPageData class constructor
		// Then Test case will pick up that data in iterations to do validations on it :->>
		public List<CartPageData> cartTableVerify()
		{
			List<CartPageData> iterationDataList = new ArrayList<CartPageData>() ;
			
			for(int i = 0 ; i < cartTableList.size() ; i++)
			{
				
				// Extract Required Values from Cart Table :->
				
				String cartTableProdName = cartTableList.get(i).findElement(By.xpath("td[2]/h4/a")).getText() ;   
				// Extract Product Name from Cart Table
				
				int singlePrice = Integer.parseInt(cartTableList.get(i).findElement(By.xpath("td[3]/p")).getText().split(" ")[1]) ;   
				// Extract Price of Single Product from Cart Table
				
				int quantity = Integer.parseInt(cartTableList.get(i).findElement(By.xpath("td[4]/button")).getText()) ;                
				// Extract Quantity of Single Product from Cart Table
				
				int totalPrice = Integer.parseInt(cartTableList.get(i).findElement(By.xpath("td[5]/p")).getText().split(" ")[1]) ;     
			    //  Extract Total Price of a Product from Cart Table
				
				iterationDataList.add(new CartPageData(cartTableProdName , singlePrice , quantity , totalPrice)) ;
				
			} // End of for loop 
			
	       return iterationDataList ;		
			
		} // End of Method
		
		// *********
		
		//3. Method to Click on checkout Button :->
		public void checkoutButtonClick() throws InterruptedException
		{
			checkoutButtonEmt.click() ;
			Thread.sleep(1000) ;
		}
		
		// *********
		
		// 4. Method to extract Mesage on Modal when Cart items added without login
		public String getModalCartMessage()
		{
			return cartMsgEmt.getText() ;
		}
		
		// *********
		
		// 5. Method to click on Continue on Cart Button on modal msg in non login case 
		public void continueCartClick()
		{
			continueCartButtonEmt.click();
		}
		
		
		
		
		// Method to return driver object from Cart Page
		public WebDriver returnDriverCartPage()
		{
		  return driver ;
		}
		
		/* ******************************************************** */
		/* ******************************************************** */

}
