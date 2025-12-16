package PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	// Global Variables :->
	WebDriver driver ;
			
		// Constructor :->
		public CheckoutPage(WebDriver driver)
		  {
				this.driver = driver ;
				PageFactory.initElements(driver , this);
		  }
		
		/* ******************************************************** */
		
		// Locators :->
	
        @FindBy(id = "address_delivery")    // Delivery Address Locator
        WebElement deliveryAddressEmt ;
        
        @FindBy(id = "address_invoice")     // Invoice Address Locator
        WebElement invoiceAddressEmt ;
        
        @FindBy(xpath = "//tbody/tr/td[5]")  // List of Prices in WebTable
        List<WebElement> checkoutPriceListEmt ;
        
        @FindBy(xpath = "//tbody/tr[5]/td[4]/p")  // Total Price displayed on Screen
        WebElement totalPriceEmt ;
		
        @FindBy(xpath = "//a[contains(text(),'Place Order')]")  // Place Order Button
        WebElement placeOrderButtonEmt ;
        
        
        
		/* ******************************************************** */
		/* ******************************************************** */
		
		// Checkout Page Util Methods :->
		
        // 1. Check if Delivery Address Displayed :->
        public boolean isDeliveryAddressDisplayed()
        {
        	return deliveryAddressEmt.isDisplayed() ;       	       		
        }
        
        // ****
        
       //  2. Check if Invoice Address Displayed :->
        public boolean isInvoiceAddressDisplayed()
        {
        	return invoiceAddressEmt.isDisplayed() ;        	       		
        }
        
        // ****
        
        // 3. Find total price by adding all prices from Web Table :->
        public int getCountedPrice()
        {
        	int countedPrice = 0 ;    		
    		for(int i = 0 ; i < checkoutPriceListEmt.size() ; i ++)
    		{
    			countedPrice = countedPrice + Integer.parseInt(checkoutPriceListEmt.get(i).getText().split(" ")[1]) ;			
    		}       	
        	return countedPrice ;
        }
        
        // ****
        
		// 4. Find Total Price displayed on Web Page :->
        public int getTotalPrice() 
        {
        	int totalPrice = Integer.parseInt(totalPriceEmt.getText().split(" ")[1]) ;
        	return totalPrice ;
        	
        }
        
        // ****
        
        // 5. Click on Place Order Button :->
        public void placeOrderButtonClick() 
        {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1800)");	
			placeOrderButtonEmt.click();
        }
       
        // ****
        
        
		
		// Method to return driver object from Checkout Page
		public WebDriver returnDriverCheckoutPage()
		{
		  return driver ;
		}
		
		/* ******************************************************** */
		/* ******************************************************** */
		
}
