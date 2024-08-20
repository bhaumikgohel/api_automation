package Report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListeners implements ITestListener{
	
	ExtentSparkReporter ui;
	ExtentReports report;
	ExtentTest test;

	 public void onStart(ITestContext context) {
		    ui = new ExtentSparkReporter("D:\\Automation\\api\\src\\test\\java\\Report\\ExecuteReport\\index.html");
		    
		    ui.config().setDocumentTitle("API AUTOMATION TESTING");
		    ui.config().setReportName("REST ASSURED NAME");
		    ui.config().setTheme(Theme.DARK);
		    
		    report = new ExtentReports();
		    report.attachReporter(ui);
		    
		    report.setSystemInfo("Methods", "POST,PUT, GET, DELETE");
		    report.setSystemInfo("URL", "https://reqres.in/");
		    report.setSystemInfo("Executeby", "Bhaumik");
		 }
	
	public void onTestSuccess(ITestResult result) {
	   
		test = report.createTest(result.getName());
		test.log(Status.PASS, "Testcase is Pass");
		
		
	  }
	
	 public void onTestFailure(ITestResult result) {
		   test = report.createTest(result.getName());
		   test.log(Status.FAIL, "Test Case Fail");
		   test.log(Status.FAIL, "Test Case Fail due to " + result.getThrowable());
		  }
	 public void onTestSkipped(ITestResult result) {
		    test = report.createTest(result.getName());
		    test.log(Status.SKIP, "Testcase is Skip");
		  }
	 public void onFinish(ITestContext context) {
		    report.flush();
		   
		  }
}
