package TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporterUtility;

public class ListenerTest implements ITestListener {

	// Extent Report Object :->
	ExtentReporterUtility reportClassObj = new ExtentReporterUtility() ;
	ExtentReports report = reportClassObj.getReportData() ;
	
	// ThreadLocal class :->
	ExtentTest test ;	
	ThreadLocal<ExtentTest> thLocalObj = new ThreadLocal<ExtentTest>() ;
	
	WebDriver driver ;
	String ssPath ;
	
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		String testCaseName = result.getMethod().getMethodName() ;
		test = report.createTest(testCaseName) ;
		thLocalObj.set(test);
	}

	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		
		thLocalObj.get().log(Status.PASS, "Test Case Executed Successfully") ;
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		// 1. Get failure logs
		thLocalObj.get().fail(result.getThrowable()) ;
		
	    try 
	    {
	        // 1. Get actual test instance
	        Object testObject = result.getInstance();

	        // 2. Get driver object of failed TC
	        WebDriver driver = (WebDriver) result
	                .getTestClass()
	                .getRealClass()
	                .getField("driver")
	                .get(testObject);
            
	        // 3. Cast test instance obj in base test to get access to SS and Close methods
	        BaseTest baseTest = (BaseTest) testObject;
	        
	        // 4. Get name of failed TC
	        String failedTcName = result.getMethod().getMethodName() ;
	        
	        // 5. Call SS Method from base class using obj created in step 5
	        String screenshotPath =
	                baseTest.getScreenShotUtil(driver, failedTcName);
	        
	        // 6. Call ASCFP Method to attach SS in report
	        thLocalObj.get().addScreenCaptureFromPath(screenshotPath , failedTcName);
	        
	        // 7. Close browser after any TC fails
	        baseTest.closeApplication(driver);

	       // String screenshotPath =
	               // baseTest.getScreenShotUtil(driver, result.getMethod().getMethodName());

	      //  test.addScreenCaptureFromPath(screenshotPath);

	    } 
	    
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
				
		
	}

	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
		thLocalObj.get().log(Status.SKIP, "Test Case Skipped from Execution") ;
	}

	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		report.flush(); 		
	}
	
	
	
	
	

}
