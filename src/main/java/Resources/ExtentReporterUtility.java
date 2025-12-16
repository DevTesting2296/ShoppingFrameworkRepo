package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	
	ExtentReports report ;
	
	public ExtentReports getReportData()
	{
		String path = System.getProperty("user.dir")+"//reports//index.html" ;
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(path) ;
		sparkReport.config().setReportName("Shopping Test Report");
		sparkReport.config().setDocumentTitle("Shopping Test Results");
		
		report = new ExtentReports() ;
		report.attachReporter(sparkReport);
		report.setSystemInfo("DevTester", "QATest");
		
		return report ;
	}
	
	

}
