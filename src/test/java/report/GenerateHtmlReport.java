package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GenerateHtmlReport {
	ExtentReports extent;
	ExtentSparkReporter spark;
	
	public void setUpReport(String report_name) {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(report_name);
		//System.out.println(report_name);
		extent.attachReporter(spark);
	}
	
	public void flush() {
		//System.out.println("flush to add report");
		extent.flush();
	}
	
	public ExtentTest createTest(String test_name, String test_description) {
		ExtentTest test = extent.createTest(test_name, test_description);
		//System.out.println("Creating test");
		return test;
	}
}
