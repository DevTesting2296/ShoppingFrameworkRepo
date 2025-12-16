package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver ; // Global Driver Object
	
	// 1. Initialize Driver Object :->
	
	public WebDriver initializeDriver() throws IOException
	{
		// 1. Read properties File
		String path =   System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties" ;
		FileInputStream fis = new FileInputStream(path) ;
		Properties prop = new Properties() ;
		prop.load(fis) ;
		
		// 2. Read browser from Command or from properties file
		String browserName =(System.getProperty("browser")!=null)?(System.getProperty("browser")):(prop.getProperty("browser")) ;
		
		// 3. Intitialize driver object as per respective browser name :->
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions() ;
			if(browserName.contains("headless"))
			{
				options.addArguments("headless") ;
			}
			driver = new ChromeDriver(options) ;			
		}
		
		else if(browserName.contains("firefox"))
		{
			driver = new FirefoxDriver() ;
		}
		
		else
		{
			driver = new EdgeDriver() ;
		}
		
		// 4. Return Driver
		return driver ;
	}
	
	/* ******************************************************** */
	
	// 2. Launch Application :->
	
	public WebDriver launchApplication() throws IOException 
	{
		// 1. Read URL from prop file
		String path =   System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties" ;
		FileInputStream fis = new FileInputStream(path) ;
		Properties prop = new Properties() ;
		prop.load(fis) ;
		String urlName = prop.getProperty("url") ;
		
		// 2. Launch Application
		driver = initializeDriver() ; // Call method from same class
		driver.get(urlName);          // Open Required URL
		driver.manage().window().maximize(); 
		
		return driver ;
	}
		
	/* ******************************************************** */

	// 3. Close Application :->
	
	public void closeApplication(WebDriver driver)
	{
		driver.close();
	}
	
	/* ******************************************************** */
	
	// 4. Screenshot Utility :->
	
	public String getScreenShotUtil(WebDriver driver , String testCaseName) throws IOException
	{
		/* *** */
		//System.out.println(">>> Screenshot method invoked for test: " +testCaseName);
		//System.out.println(">>> Driver value inside screenshot method: " +driver);
		
		/* *** */
		File screenshotsDir = new File(System.getProperty("user.dir") + "/Screenshots");
	    if (!screenshotsDir.exists()) {
	        screenshotsDir.mkdirs();
	    }
		
	    /* *** */
	    String path = screenshotsDir + "/" + testCaseName + ".png";
	    
		// String path = System.getProperty("user.dir")+"//Screenshots//"+testCaseName+".png" ;
		TakesScreenshot ts = (TakesScreenshot)driver ;
		File src = ts.getScreenshotAs(OutputType.FILE) ;
		FileUtils.copyFile(src, new File(path));
		
		/* *** */
		//System.out.println(">>> Screenshot saved at: " +path);
						
		return path ;
		
	}
	
	/* ******************************************************** */
	
	// 5. JSON Utility :->
	
	public List<HashMap<String,String>> getJsonReaderUtil(String path) throws IOException
	{
		String jsonString = FileUtils.readFileToString(new File(path), 
				            StandardCharsets.UTF_8) ;
		
		ObjectMapper mapper = new ObjectMapper() ;
		
		List<HashMap<String,String>> jsonData = 
				mapper.readValue
				(jsonString, 
				 new TypeReference<List<HashMap<String,String>>>() 
				 {
				 }
				 ) ;	    
	   return jsonData ;		
	}
	
	
	
	/* ******************************************************** */
	
}
