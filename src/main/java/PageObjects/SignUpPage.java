package PageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
	
	    // Global Variables :->
		WebDriver driver ;
		String nameDisplay ;
		String emailDisplay ;

		
		// Constructor :->
		public SignUpPage(WebDriver driver)
		{
			this.driver = driver ;
			PageFactory.initElements(driver, this);
		}
		
		/* ******************************************************** */
		
		// Locators :->
		
		By titleMsgByEmt = By.xpath("//div[@class='login-form']/h2/b") ;  // By Locator for Title Msg Locator
		
		@FindBy(xpath = "//div[@class='login-form']/h2/b")    // Title Msg Locator
		WebElement titleMsgEmt ;
		
		@FindBy(id = "uniform-id_gender1")   // Mr Radio Button
		WebElement mrEmt ;
		
		@FindBy(id = "uniform-id_gender2")   // Mrs Radio Button
		WebElement mrsEmt ;
		
	    @FindBy(xpath = "//input[@id='name']")  // Name Field
	    WebElement nameEmt ;
	    
	    @FindBy(xpath = "//input[@id='email']")  // Email Field
	    WebElement emailEmt ;
		
	    @FindBy(xpath = "//input[@id='password']")  // Password Field
	    WebElement passwordEmt ;
	    
	    @FindBy(id = "days")   // Days dropdown
	    WebElement dayEmt ;
	    
	    @FindBy(id = "months")   // Months dropdown
	    WebElement monthEmt ;
	    
	    @FindBy(id = "years")   // Years dropdown
	    WebElement yearEmt ;
	    
	    @FindBy(xpath = "//input[@id='first_name']")  // First Name
	    WebElement firstNameEmt ;
	    
	    @FindBy(xpath = "//input[@id='last_name']")  // Last Name
	    WebElement lastNameEmt ;
	    
	    @FindBy(xpath = "//input[@id='company']")  // Company
	    WebElement companyEmt ;
	    
	    @FindBy(xpath = "//input[@id='address1']")  // Address
	    WebElement addressEmt ;
	    
	    @FindBy(id = "country")   // Country dropdown
	    WebElement countryEmt ;
	    
	    @FindBy(xpath = "//input[@id='state']")  // state
	    WebElement stateEmt ;
	    
	    @FindBy(xpath = "//input[@id='city']")  // city
	    WebElement cityEmt ;
	    
	    @FindBy(xpath = "//input[@id='zipcode']")  // zipcode
	    WebElement zipcodeEmt ;
	    
	    @FindBy(xpath = "//input[@id='mobile_number']")  // mobile_number
	    WebElement mobileNumEmt ;
	    
	    @FindBy(xpath = "//button[contains(text(),'Create Account')]") //Create Account
	    WebElement createAccEmt ;
	    
	
		/* ******************************************************** */
		
	    // Signup Page Util Methods :->
	    
	    // 1. Wait until Title Message is displayed on Screen :
	    public void waitForTitleMsg()
	    {
	    	WebDriverWait waitSignupPage = new WebDriverWait(driver, Duration.ofMillis(2000));
	    	waitSignupPage.until(ExpectedConditions.visibilityOfElementLocated(titleMsgByEmt)); 
	    }
	    
	    // *********
	    
	    // 2. Extract Title Message and send it to TC :->
	    public String getTitleMsg()
	    {
	    	return titleMsgEmt.getText() ;	    
	    }
	    
	    // *********
	    
	   // 3. Extract Username and send it to TC :->
	    public String getNameText()
	    {
	    	return nameEmt.getAttribute("value") ;	    
	    }
	    
	    // *********
	    
	   // 4. Extract Email and send it to TC :->
	    public String getemailText()
	    {
	    	return emailEmt.getAttribute("value") ;    
	    }
	    
	    // *********
	    	    	    
	    // 5. Fill signup form :->
	    public void fillSignUpForm(HashMap<String,String> userData) throws InterruptedException
	    {
	    	// 1. Extract Data frpm input list :->
	    	
	    	String gender = userData.get("gender") ;
			String username = userData.get("username") ;
			String email = userData.get("email") ;
			String password = userData.get("password") ;
			String day = userData.get("day") ;
			String month = userData.get("month") ;
			String year = userData.get("year") ;
			String firstName = userData.get("firstName") ;
			String lastName = userData.get("lastName") ;
			String company = userData.get("company") ;
			String address = userData.get("address") ;
			String country = userData.get("country") ;
			String state = userData.get("state") ;
			String city = userData.get("city") ;
			String zipcode = userData.get("zipcode") ;
			String mobileNum = userData.get("mobileNum") ;
	    		    		    	
	    	// *********
	    	
	    	// 2. Scroll down before staring :->
	    	JavascriptExecutor js =	(JavascriptExecutor)driver ;
	    	js.executeScript("window.scrollBy(0,150)") ;
	    	
	    	// 3. Enter Gender
	    	if(gender.contains("male"))
	    		mrEmt.click();
	    	else
	    		mrsEmt.click();
	    	
	    	// 4. Enter Password
	    	js.executeScript("window.scrollBy(0,200)") ;
	    	passwordEmt.sendKeys(password);
	    	
	    	// 5. Enter Day :->
	    	Select dayDD = new Select(dayEmt);
	    	dayDD.selectByVisibleText(day);
	    	
	    	// 6. Enter Month :->
	    	Select monthDD = new Select(monthEmt);
	    	monthDD.selectByVisibleText(month);
	    	
	    	// 7. Enter Year :->
	    	Select yearDD = new Select(yearEmt);
	    	yearDD.selectByVisibleText(year);
	    	Thread.sleep(500);
	    	
	    	// 8. Scroll down
	    	js.executeScript("window.scrollBy(0,500)") ;
	    	
	    	// 9. enter First Name
	    	firstNameEmt.sendKeys(firstName);
	    	
	    	// 10. enter Last Name
	    	lastNameEmt.sendKeys(lastName);
	    	
	    	// 10. enter Company
	    	companyEmt.sendKeys(company);
	    	
	    	// 11. enter address
	    	addressEmt.sendKeys(address);
	    	
	    	// 12. Scroll down
	    	js.executeScript("window.scrollBy(0,500)") ;
	    	
	    	// 13. Select Country
	    	Select countryDD = new Select(countryEmt) ;
	    	countryDD.selectByValue(country);
	    	Thread.sleep(500);
	    	
	    	// 14. enter state
	    	stateEmt.sendKeys(state);
	    	
	    	// 15. enter city
	    	cityEmt.sendKeys(city);
	    	
	    	// 16. enter zipcode
	    	zipcodeEmt.sendKeys(zipcode);
	    	
	    	// 17. enter mobile number
	    	mobileNumEmt.sendKeys(mobileNum);
	    	
	    	// 18. Scroll down
	    	js.executeScript("window.scrollBy(0,500)") ;
	    	
	    	// 19. Click on create Button
	    	createAccEmt.click();
	    		    		    	
	    }
	    
	    
	    
	    /* ******************************************************** */
	    
	 // Method to return driver object from Signup Page
		public WebDriver returnDriverSignUpPage()
		{
		   return driver ;
		}
	    
	    
	    /* ******************************************************** */
		
}
