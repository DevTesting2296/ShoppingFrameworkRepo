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
import PageObjects.AccountCreatePage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.SignUpPage;
import TestComponents.BaseTest;
import TestComponents.RetryTest;

public class LoginTest extends BaseTest {
	
	/* ******************************************************** */
	/* ******************************************************** */
	
	// 1. Test Case to Sign up new User
	
	@DataProvider
	public Object[][] getSignupData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//signUpNewUserData.json" ;
		
		List<HashMap<String , String>> map =  getJsonReaderUtil(path) ;		
		
		return new Object[][] {{map.get(0)} , {map.get(1)}};
	}
	
	@Test(dataProvider = "getSignupData")
	public void signUpNewUser(HashMap<String,String> map) throws IOException, InterruptedException
	{
		String gender = map.get("gender") ;
		String username = map.get("username") ;
		String email = map.get("email") ;
		String password = map.get("password") ;
		String day = map.get("day") ;
		String month = map.get("month") ;
		String year = map.get("year") ;
		String firstName = map.get("firstName") ;
		String lastName = map.get("lastName") ;
		String company = map.get("company") ;
		String address = map.get("address") ;
		String country = map.get("country") ;
		String state = map.get("state") ;
		String city = map.get("city") ;
		String zipcode = map.get("zipcode") ;
		String mobileNum = map.get("mobileNum") ;
	
		//List<String> userSignUpData =  
		// Arrays.asList(gender , username , email , password , day , month , year , firstName , lastName , company , address , country , state , city , zipcode , mobileNum) ;
			
        /* ******************************************************** */
		
		// 1. Launch Application :->
		 WebDriver driver = launchApplication() ;    // Call this method from base class to launch application
		
		/* ******************************************************** */
		
		// 2. Go to Login Page :->
			
		HomePage homePgObj = new HomePage(driver) ;     // create Obj for HomePage
		homePgObj.clickLoginButton();                   // Click Login Button from Home Page
		driver = homePgObj.returnDriverHomePage() ;     // Take driver obj from Home Page
		
		/* ******************************************************** */
		
		// 3. Select Sign up option :->
		
		LoginPage loginPgObj = new LoginPage(driver) ;  // create Obj for LoginPage 
		loginPgObj.waitForSignupButton();               // Wait until Signup Button is displayed
		loginPgObj.signupInitialData(username, email);  // Call method to enter username , email on login page
		driver = loginPgObj.returnDriverLoginPage() ;   // // Take driver obj from Login Page
		
		/* ******************************************************** */
		
		// 4. Sign Up process :->
		
		SignUpPage signupObj = new SignUpPage(driver) ;
		SoftAssert sfaObj = new SoftAssert() ;
		
		signupObj.waitForTitleMsg();                          // Wait until Title Msg is displayed 
		String titleMsg = signupObj.getTitleMsg() ;           // get Title Msg from Sign up page
		sfaObj.assertTrue(titleMsg.contains("ACCOUNT INFO")); // verify if Title Msg is as expected
		String nameDisplay = signupObj.getNameText() ;        // get displayed userName from Sign up page
		sfaObj.assertEquals(nameDisplay, username);           // Verify if username passed by us is displayed on Screen
		String emailDisplay = signupObj.getemailText() ;      // get displayed email from Sign up page
		sfaObj.assertEquals(emailDisplay, email);             // Verify if email passed by us is displayed on Screen
		
		signupObj.fillSignUpForm(map);                        // Call signup method by sending user data to it
		driver = signupObj.returnDriverSignUpPage() ;         // Take driver back from Sign Up Page
						
		/* ******************************************************** */
		
		// 5. Validations on account create Page :->
		
		AccountCreatePage accCrObj = new AccountCreatePage(driver) ;    // create obj for acc create pg
		accCrObj.waitForContinueButton();                               // Wait until Continue is displayed
		
		String signUpTitleMsg = accCrObj.getsignUpTitleMsg() ;          // Get Title Msg
		sfaObj.assertTrue(signUpTitleMsg.contains("CREATED"));          // Validate title Msg
		
		String signUpMsg = accCrObj.getsignUpMsg() ;                    // Get Signup Msg
		sfaObj.assertTrue(signUpMsg.contains("Congratulations"));       // Validate Signup Msg
		
		accCrObj.clickContinueButton();                                 // Click on Continue Button
		driver = accCrObj.returnDriverAccountCreatePage() ;             // Take driver back from acc create pg
		
		/* ******************************************************** */
		
		// 6. Validations on Navigation Bar :->
		 
		AbstractCompPO absObj = new AbstractCompPO(driver) ;             // create obj for abs comp
		String displayedUserName = absObj.getLoggedInUserName() ;        // Call method to get username in nav bar
		sfaObj.assertEquals(displayedUserName, username);                // validate if displayed username is correct
		
		absObj.clickLogoutButton();                                      // click on Logout Button 
		driver =  absObj.returnDriverAbsCompPOPage() ;                   // Take driver obj from Abstract Comp
		
		homePgObj = new HomePage(driver) ;                  // create Obj for HomePage
		homePgObj.waitForLoginButton();                     // Wait until Login Button is displayed on Home Page
		driver = homePgObj.returnDriverHomePage() ;         // Take driver obj from Home Page
		
		sfaObj.assertAll();
		
		/* ******************************************************** */
		
		// 7. Close Application :->
		closeApplication(driver) ;                        // Call method from Base Class to close application
		
	}
	
	/* ******************************************************** */
	/* ******************************************************** */
	
	 // 2. Test Case to try Signup with existing User email :-> Negative Scenario 
		
		@DataProvider
		public Object[][] getExisitngSignupData() throws IOException
		{
			String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//signUpExisitngData.json" ;			
			List<HashMap<String , String>> map =  getJsonReaderUtil(path) ;					
			return new Object[][] {{map.get(0)} , {map.get(1)}};
		}
	    
	    @Test(dataProvider = "getExisitngSignupData")
	    public void signUpExisitngUser(HashMap<String,String>mapData) throws IOException
	    {
	    	
	    	String username = mapData.get("username") ;
	    	String email = mapData.get("email") ;
	    	
	    	/* ******************************************************** */
			
			// 1. Launch Application :->
			 WebDriver driver = launchApplication() ;    // Call this method from base class to launch application
			 SoftAssert sftObj = new SoftAssert() ;
			
			/* ******************************************************** */
			
			// 2. Go to Login Page :->
				
			HomePage homePgObj = new HomePage(driver) ;     // create Obj for HomePage
			homePgObj.clickLoginButton();                   // Click Login Button from Home Page
			driver = homePgObj.returnDriverHomePage() ;     // Take driver obj from Home Page
			
			/* ******************************************************** */
			
			// 3. Select Sign up option  :->
			
			LoginPage loginPgObj = new LoginPage(driver) ;  // create Obj for LoginPage 
			loginPgObj.waitForSignupButton();               // Wait until Signup Button is displayed
			loginPgObj.signupInitialData(username, email);  // Call method to enter username , email on login page
			
			/* ******************************************************** */
			
			// 4. Validate Error Msg
			
			String signUpErrMsg = loginPgObj.getExisitngSignUpErrorMsg() ;  // Get Sign Up error Msg
			sftObj.assertTrue(signUpErrMsg.contains("already exist"));      // Validate Error Msg
					
			sftObj.assertAll();	
			closeApplication(driver) ;                         // Call method from Base Class to close application
			
			/* ******************************************************** */
	    	    	
	    }
	  
	    /* ******************************************************** */
		/* ******************************************************** */
	    		
		 // 3. Test Case to Sign In with valid details 
			
			@DataProvider
			public Object[][] getvalidSignInData() throws IOException
			{
				String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//signInValidData.json" ;			
				List<HashMap<String,String>> map =  getJsonReaderUtil(path) ;					
				return new Object[][] {{map.get(0)} , {map.get(1)}};
			}
			
		    @Test(dataProvider = "getvalidSignInData")
		    public void signInValidData(HashMap<String,String>mapData) throws IOException		    		   
		    {
		    	String email = mapData.get("email") ;
		    	String username = mapData.get("username") ;
		    	String password = mapData.get("password") ;
		    	
		    	/* ******************************************************** */
				
				// 1. Launch Application :->
				WebDriver driver = launchApplication() ;         // Call this method from base class to launch application
				SoftAssert sftObj = new SoftAssert() ;
				
				/* ******************************************************** */
				
				// 2. Login :->
				
				HomePage homePgObj = new HomePage(driver) ;     // create Obj for HomePage
				homePgObj.clickLoginButton();                   // Click Login Button from Home Page
				driver = homePgObj.returnDriverHomePage() ;     // Take driver obj from Home Page
				
				LoginPage loginPgObj = new LoginPage(driver) ;  // create Obj for LoginPage 
				loginPgObj.login(email , password);          // Call Login Method by Passing username , Password
				driver = loginPgObj.returnDriverLoginPage() ;   // Take driver obj from Login Page
												
				/* ******************************************************** */
				
				// 3. Validations on Navigation Bar :->
				 
				AbstractCompPO absObj = new AbstractCompPO(driver) ;           // create obj for abs comp
				absObj.waitForLogoutButton();                                  // Wait until Logout Button is displayed on Home Page
				
				String displayedUserName = absObj.getLoggedInUserName() ;      // Call method to get username in nav bar
				sftObj.assertEquals(displayedUserName, username);              // validate if displayed username is correct
				
				absObj.clickLogoutButton();                                    // click on Logout Button 
				driver =  absObj.returnDriverAbsCompPOPage() ;                 // Take driver obj from Abstract Comp
				
				homePgObj = new HomePage(driver) ;                  // create Obj for HomePage
				homePgObj.waitForLoginButton();                     // Wait until Login Button is displayed on Home Page
				driver = homePgObj.returnDriverHomePage() ;         // Take driver obj from Home Page
				
				/* ******************************************************** */
				
				// 4. Last Steps :-> 
				
				sftObj.assertAll();
				closeApplication(driver) ;                         // Call method from Base Class to close application
				
				/* ******************************************************** */
				
		    }
		
		/* ******************************************************** */
		/* ******************************************************** */
		    
		 // 4. Test Case to Sign In with invalid details : Negative Scenario
			
		 	@DataProvider
		 	public Object[][] getInvalidSignInData() throws IOException
		 	{
		 		String path = System.getProperty("user.dir")+"//src//main//java//JSONDataFiles//signInInvalidData.json" ;			
		 		List<HashMap<String,String>> map =  getJsonReaderUtil(path) ;					
		 		return new Object[][] {{map.get(0)} , {map.get(1)}};
		 	}  
		 	
		    @Test(dataProvider = "getInvalidSignInData")
		    public void signInInvalidData(HashMap<String,String> mapData) throws IOException
		    {
		    	String email = mapData.get("email") ;		    	
		    	String password = mapData.get("password") ;
		    	
		    	/* ******************************************************** */
				
				// 1. Launch Application :->
				WebDriver driver = launchApplication() ;         // Call this method from base class to launch application
				SoftAssert sftObj = new SoftAssert() ;
				
				/* ******************************************************** */
				
				// 2. Login :->
				
				HomePage homePgObj = new HomePage(driver) ;     // create Obj for HomePage
				homePgObj.clickLoginButton();                   // Click Login Button from Home Page
				driver = homePgObj.returnDriverHomePage() ;     // Take driver obj from Home Page
				
				LoginPage loginPgObj = new LoginPage(driver) ;  // create Obj for LoginPage 
				loginPgObj.login(email , password);             // Call Login Method by Passing username , Password
		    	
				String invalidSignInErrMsg = loginPgObj.getinvalidSignInErrorMsg() ;  // Get error Msg for invalid sign in
				sftObj.assertTrue(invalidSignInErrMsg.contains("incorrect"));         // Validate Error Msg
		    	
				/* ******************************************************** */
				
                // 3. Last Steps :-> 
				
				sftObj.assertAll();
				closeApplication(driver) ;                         // Call method from Base Class to close application
				
				/* ******************************************************** */
		    }
		    
		    
		/* ******************************************************** */
		/* ******************************************************** */    
		    
		    
		    
		    
		    

}
