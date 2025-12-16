package TestCases;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import AbstractComponents.AbstractCompPO;
import AbstractComponents.CartPageData;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.PaymentPage;
import TestComponents.BaseTest;
import TestComponents.RetryTest;

public class EndToEndTest extends BaseTest {
	
	/* ******************************************************** */
	/* ******************************************************** */
	
	// 1. Test Case to add multiple products in E2E Flow 
	
	@DataProvider
	public Object[][] getJsonDataMultiProd() throws IOException
	{
		String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//submitOrderMultiProdData.json" ;
		
		List<HashMap<String,String>> dataMapList = getJsonReaderUtil(path) ;
		
		return new Object[][] {{dataMapList.get(0)}} ;
	}
	
	@Test(dataProvider = "getJsonDataMultiProd" , retryAnalyzer = RetryTest.class)
	public void submitMultiProductOrder(HashMap<String,String> dataMap) throws IOException, InterruptedException
	{
		// 0. Declare Variables to Hold Test Data :->
		
		String userName = dataMap.get("userName") ;
		String passWord = dataMap.get("passWord") ;
		
		String nameOnCard = dataMap.get("nameOnCard") ;
		String cardNumber = dataMap.get("cardNumber") ;
		String cvcNumber = dataMap.get("cvcNumber") ;
		String expMonth = dataMap.get("expMonth") ;
		String expYear = dataMap.get("expYear") ;
		
		String prod1 = dataMap.get("prod1") ;
		String prod2 = dataMap.get("prod2") ;
		String prod3 = dataMap.get("prod3") ;
		String prod4 = dataMap.get("prod4") ;
		//String prod5 = dataMap.get("prod5") ;
		
		List<String> requiredProdList = Arrays.asList(prod1 , prod2 , prod3 , prod4) ;
				
		/* ******************************************************** */
		
		// 1. Launch Application :->
		WebDriver driver = launchApplication() ;         // Call this method from base class to launch application
		
		/* ******************************************************** */
		
		// 2. Login :->
		
		HomePage homePgObj = new HomePage(driver) ;     // create Obj for HomePage
		homePgObj.clickLoginButton();                   // Click Login Button from Home Page
		driver = homePgObj.returnDriverHomePage() ;     // Take driver obj from Home Page
		
		LoginPage loginPgObj = new LoginPage(driver) ;  // create Obj for LoginPage 
		loginPgObj.login(userName , passWord);          // Call Login Method by Passing username , Password
		driver = loginPgObj.returnDriverLoginPage() ;   // Take driver obj from Login Page
		
		AbstractCompPO absCompObj = new AbstractCompPO(driver) ;  // create Obj for Abstract Comp 
		absCompObj.waitForLogoutButton();                 // Wait until Logout Button is displayed on Home Page
		driver = absCompObj.returnDriverAbsCompPOPage() ; // Take driver obj from Abstract Comp
		
		
		/* ******************************************************** */
		
		// 3. Add Required Products in Cart :->
		
		// 3.1 Scroll Down Web Page on Initial Launch :->
		HomePage homePgAddCartObj = new HomePage(driver) ; // Create another object for Home Page Class
		homePgAddCartObj.scrollDownInitialLaunch();        // Scroll Down Web Page on Initial Launch
		
		// 3.2 Add required Products to Cart :->
		homePgAddCartObj.addProdToCart(requiredProdList);  // Add required Products to Cart by sending required prod list
		driver = homePgAddCartObj.returnDriverHomePage() ; // Take driver obj from Home Page
		
		
		/* ******************************************************** */

		// 4 Cart Verification on View Cart Page :->
				
		// 4.1 Wait for Checkout Button to appear :->
		CartPage cartPgObj = new CartPage(driver) ;
		cartPgObj.waitForCheckoutButton(); 
		
		// 4.2 Call method to take data retrived from Cart Page Table
		List<CartPageData> iterationDataList = cartPgObj.cartTableVerify() ; 
		
		// 4.3 Iterate into that Data to Validate required details :->
		for(int i = 0 ; i < iterationDataList.size() ; i++)
		{			
			SoftAssert sFTObj = new SoftAssert() ;
			
			// Extract Individual values from IterationDataList
			String tableProdName = iterationDataList.get(i).getCartTableProdName() ;
			int singlePrice = iterationDataList.get(i).getsinglePrice() ;
			int quantity =    iterationDataList.get(i).getquantity() ;
			int totalPrice =  iterationDataList.get(i).gettotalPrice() ;
						
			// Validate if Prod Name in table matches with List of Prods given as Input
			sFTObj.assertTrue(requiredProdList.contains(tableProdName)); 
			
			// Validate if Prices are correctly displayed :->
			sFTObj.assertEquals(singlePrice*quantity, totalPrice);
			
			sFTObj.assertAll();
			
		}	
		
		// 4.4 Click on Checkout button :->
		cartPgObj.checkoutButtonClick();
		
		// 4.5 Get driver object back from Cart Page :->
		driver = cartPgObj.returnDriverCartPage() ;
						
		/* ******************************************************** */
		
		// 5. Checkout Page :->
		
		CheckoutPage checkObj = new CheckoutPage(driver) ; // Create Object for Checkout Page
		
		SoftAssert saCheckPGObj = new SoftAssert() ;
		
		// 5.1 Check if Delivery Address is Displayed :->
		saCheckPGObj.assertTrue(checkObj.isDeliveryAddressDisplayed());
		
		// 5.2 Check if Invoice Address is Displayed :->
		saCheckPGObj.assertTrue(checkObj.isInvoiceAddressDisplayed());
		
		// 5.3 Cost Verification :->
		int countedPrice =  checkObj.getCountedPrice() ;      // Get Counted Price
		int totalPrice = checkObj.getTotalPrice() ;           // Get Total Price
		saCheckPGObj.assertEquals(countedPrice, totalPrice);  // Check if both are equal
		
		// 5.4 Click on Place Order :->
		checkObj.placeOrderButtonClick(); // Click on Place Order 
		saCheckPGObj.assertAll(); 
		
		// 5.5 Get driver object back from checkout Page :->
		driver = checkObj.returnDriverCheckoutPage() ;
				
		/* ******************************************************** */
		
		// 6. Payment Page :->
		
		PaymentPage payPgObj = new PaymentPage(driver) ;   // Payment Page Object
		
		// 6.1 Wait until submit button displayed :->
		payPgObj.waitForSubmitButton();
		
		// 6.2. Enter Name on Card :->
		payPgObj.enterNameOnCard(nameOnCard);
		
		// 6.3. Enter Card Number :->
		payPgObj.enterCardNumber(cardNumber);
		
		// 6.4. Enter CVC Number :->
		payPgObj.enterCVCNumber(cvcNumber);
		
		// 6.5. Enter Exp Month :->
		payPgObj.enterExpMonth(expMonth);
		
		// 6.6. Enter Exp Year :->
		payPgObj.enterExpYear(expYear);
		
		// 6.7. Click on Submit Button :->
		payPgObj.clickOnsubmit();
		
		// 6.8 Get driver object back from Payment Page :->
		driver = payPgObj.returnDriverPaymentPage() ;
				
		
		/* ******************************************************** */
		
		// 7. Confirmation Page :->
		
		ConfirmationPage confPgObj = new ConfirmationPage(driver) ;
		SoftAssert sftCnfObj = new SoftAssert() ;
		
		// 7.1. Wait for continue button to display :->
		confPgObj.waitForContinueButton(); 
		
		// 7.2 Verify Success Message title :->
		String msgTitle = confPgObj.getSuccessMsgTitle() ;
		sftCnfObj.assertTrue(msgTitle.contains("PLACED"));
		
		// 7.3 Verify success messgage text :-> 
		String msgText = confPgObj.getSuccessMsgText() ;
		sftCnfObj.assertTrue(msgText.contains("Congratulations"));
				
		// 7.4 click on continue Button :->
		confPgObj.clickOnContinueButton();
		
		// 7.5 Final Validations :->
		driver = confPgObj.returnDriverConfirmationPage() ;  // Take driver obj from Confirmation Page
		
		absCompObj = new AbstractCompPO(driver) ;            // create Obj for Abstract Comp
		absCompObj.clickLogoutButton();                      // click on Logout Button 
		driver =  absCompObj.returnDriverAbsCompPOPage() ;   // Take driver obj from Abstract Comp
		
		homePgObj = new HomePage(driver) ;                  // create Obj for HomePage
		homePgObj.waitForLoginButton();                     // Wait until Login Button is displayed on Home Page
		driver = homePgObj.returnDriverHomePage() ;         // Take driver obj from Home Page
		
		sftCnfObj.assertAll();
		
		/* ******************************************************** */
		
		// Close Application :->
		closeApplication(driver) ;                        // Call method from Base Class to close application
		
	}
	
	/* ******************************************************** */
	/* ******************************************************** */
	
	// 2. Test Case to add multiple products without Login :-> 
	
	@DataProvider
	public Object[][] getMultiProdNonLoginData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//multiProdNonLoginData.json" ;
		
		List<HashMap<String,String>> dataMapList = getJsonReaderUtil(path) ;
		
		return new Object[][] {{dataMapList.get(0)}} ;
	}
	
	@Test(dataProvider = "getMultiProdNonLoginData" , retryAnalyzer = RetryTest.class)
	public void multiProductNonLogin(HashMap<String,String> dataMap) throws IOException, InterruptedException
	{
		// 0. Declare Variables to Hold Test Data :->
		
		String prod1 = dataMap.get("prod1") ;
		String prod2 = dataMap.get("prod2") ;
		String prod3 = dataMap.get("prod3") ;
		String prod4 = dataMap.get("prod4") ;
		String modalMsgText = dataMap.get("cartModalMsg") ;   // retrive expected modal msg text from json
		
		List<String> requiredProdList = Arrays.asList(prod1 , prod2 , prod3 , prod4) ;
				
		/* ******************************************************** */
		
		// 1. Launch Application :->
		WebDriver driver = launchApplication() ;         // Call this method from base class to launch application
		SoftAssert sftObj = new SoftAssert() ;
		
		/* ******************************************************** */
				
		// 2. Add Required Products in Cart :->
		
		// 2.1 Scroll Down Web Page on Initial Launch :->
		HomePage homePgAddCartObj = new HomePage(driver) ; // Create object for Home Page Class
		homePgAddCartObj.scrollDownInitialLaunch();        // Scroll Down Web Page on Initial Launch
		
		// 2.2 Add required Products to Cart :->
		homePgAddCartObj.addProdToCart(requiredProdList);  // Add required Products to Cart by sending required prod list
		driver = homePgAddCartObj.returnDriverHomePage() ; // Take driver obj from Home Page
				
		/* ******************************************************** */

		// 3 Cart Verification on View Cart Page :->
				
		// 3.1 Wait for Checkout Button to appear :->
		CartPage cartPgObj = new CartPage(driver) ;
		cartPgObj.waitForCheckoutButton(); 
		
		// 3.2 Call method to take data retrived from Cart Page Table
		List<CartPageData> iterationDataList = cartPgObj.cartTableVerify() ; 
		
		// 3.3 Iterate into that Data to Validate required details :->
		for(int i = 0 ; i < iterationDataList.size() ; i++)
		{			
			SoftAssert sFTObj = new SoftAssert() ;
			
			// Extract Individual values from IterationDataList
			String tableProdName = iterationDataList.get(i).getCartTableProdName() ;
			int singlePrice = iterationDataList.get(i).getsinglePrice() ;
			int quantity =    iterationDataList.get(i).getquantity() ;
			int totalPrice =  iterationDataList.get(i).gettotalPrice() ;
						
			// Validate if Prod Name in table matches with List of Prods given as Input
			sFTObj.assertTrue(requiredProdList.contains(tableProdName)); 
			
			// Validate if Prices are correctly displayed :->
			sFTObj.assertEquals(singlePrice*quantity, totalPrice);
			
			sFTObj.assertAll();
			
		}	
		
		// 3.4 Click on Checkout button :->
		cartPgObj.checkoutButtonClick();
		
		// 3.5 Get Message on Cart Modal and validate it
		String cartModalMsg = cartPgObj.getModalCartMessage() ;
		sftObj.assertEquals(cartModalMsg, modalMsgText);
				
		// 3.6 Click on Continue Cart Button from Cart Page :->
		cartPgObj.continueCartClick();
								
		/* ******************************************************** */
		
		// 4. Last steps :->
		
		sftObj.assertAll(); 
		closeApplication(driver) ;
		
		/* ******************************************************** */
		
	}
	

}
