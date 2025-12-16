package PageObjects;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
	// Global Variables :->
	WebDriver driver ;

	
	// Constructor :->
	public LoginPage(WebDriver driver)
	{
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	/* ******************************************************** */
	
	// Locators :->
	
	By loginButtonByEmt = By.xpath("//button[contains(text(),'Login')]");    // By Locator of Login Button on LoginPage
	
	By signupByEmt =  By.xpath("//button[contains(text(),'Signup')]") ; // By Locator of Signup Button on LoginPage
	
	@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-1']/div/form/input[@name='email']") // Email Text Box on Login Page
	WebElement emailEmt ;
	
	@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-1']/div/form/input[@name='password']") // Password Text Box on Login Page
	WebElement passwordEmt ;
	
	@FindBy(xpath = "//button[contains(text(),'Login')]") // Login Button on Login Page
	WebElement loginButtonEmt ;
	
	@FindBy(xpath = "//button[contains(text(),'Signup')]") // Signup Button on Login Page
	WebElement signupButtonEmt ;
	
	@FindBy(xpath = "//div[@class='signup-form']/form/input[@name='name']")  // Signup name text box
	WebElement nameSignupEmt ;
	
	@FindBy(xpath = "//div[@class='signup-form']/form/input[@name='email']")  // Signup email text box
	WebElement emailSignupEmt ;
	
	@FindBy(xpath = "//input[@value='signup']/following-sibling::p")    // Exisitng user SignUp Error Msg
	WebElement errorMsgEmt ;
	
	@FindBy(xpath = "//button[contains(text(),'Login')]/preceding-sibling::p") // Invalid SignIn Error Msg
	WebElement invalidSignInErrMsgEmt ;
	
	
	/* ******************************************************** */
	/* ******************************************************** */
	
	// Login Page Util Methods :->
	
	// 1. Method to wait until Login Button is displayed on Login Page :->
	public void waitForLoginButton()
	{
	   WebDriverWait waitLoginPage = new WebDriverWait(driver, Duration.ofMillis(2000));
	   waitLoginPage.until(ExpectedConditions.visibilityOfElementLocated(loginButtonByEmt));   // Wait until Login Button is displayed
	}
	
	// 2. Method to Login Steps :->
	public void login(String userName , String passWord)
	{
		waitForLoginButton() ;          // Wait until Login Button is displayed
		emailEmt.sendKeys(userName);    // Enter Username
		passwordEmt.sendKeys(passWord); // Enter Password
		loginButtonEmt.click();         // Login Complete		
	}
	
	// 3. Method to wait until Login Button is displayed on Login Page :->
	public void waitForSignupButton()
	{
		WebDriverWait waitLoginPage = new WebDriverWait(driver, Duration.ofMillis(2000));
		waitLoginPage.until(ExpectedConditions.visibilityOfElementLocated(signupByEmt));   // Wait until Signup Button is displayed
	}
	
	// 4. Method to enter username, email for new signup
	public void signupInitialData(String username , String email)
	{
		nameSignupEmt.sendKeys(username);
		emailSignupEmt.sendKeys(email);
		signupButtonEmt.click();
		
	}
	
	// 5. Method to extract Error Msg for exisitng user signUp
	public String getExisitngSignUpErrorMsg()
	{
		return errorMsgEmt.getText() ;
	}
	
	// 6. Method to extract Error Msg for invalid SignIn
		public String getinvalidSignInErrorMsg()
		{
			return invalidSignInErrMsgEmt.getText() ;
		}
	
	
	
	// Method to return driver object from Login Page
	public WebDriver returnDriverLoginPage()
	{
	   return driver ;
	}
	
	/* ******************************************************** */
	/* ******************************************************** */
	
}
