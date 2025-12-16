package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractCompPO {
	
	// Global Variables :->
		WebDriver driver ;
		
	// Constructor :->
	public AbstractCompPO(WebDriver driver)
	{
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	/* ******************************************************** */
	
	// Locators :->
	
	By logoutButtonByEmt = By.xpath("//ul[@class='nav navbar-nav']/li[4]/a[contains(text(),'Logout')]");  // By Locator for Logout Button
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[4]/a[contains(text(),'Logout')]") // Locator for Logout Button
	WebElement logoutButtonEmt ;
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[10]/a[contains(text(),'Logged in')]") // Locator for Logged in user name
	WebElement loggedInUserNameEmt ;
		
	 /* ******************************************************** */
	
	 // Abstract Component Util Methods :->
	
	// 1. Method to wait until Logout Button is displayed :->
	public void waitForLogoutButton() 
	{
		WebDriverWait waitAbsPOPage = new WebDriverWait(driver, Duration.ofMillis(4000));
		waitAbsPOPage.until(ExpectedConditions.visibilityOfElementLocated(logoutButtonByEmt));   // Wait until Logout Button is displayed
	}	
	
	// 2. Method to click on Logout Button :->
	public void clickLogoutButton()
	{
		logoutButtonEmt.click();
	}
	
	// 3. Method to return logged in user name :->
	public String getLoggedInUserName()
	{
		return loggedInUserNameEmt.getText().split("as ")[1] ;
	}
	
	
	
	// Method to return driver object from Abstract Components 
		public WebDriver returnDriverAbsCompPOPage()
		{
		   return driver ;
		}
	
	/* ******************************************************** */
	
	

}
