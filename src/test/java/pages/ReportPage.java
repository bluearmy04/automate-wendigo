package pages;

import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import media.TakeScreenshot;

public class ReportPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	
	public void checkReportsPage(ExtentTest report_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		report_module_report.info("Navigating to Report module");
		
		try {
			dashboard.clickDashboardIcon("report/report-dashboard");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("report_page.png");
			report_module_report.pass("Landed on Reports Page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "report_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("report_page_error.png");
			report_module_report.fail("Error occured while trying to navigate Report page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "report_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Report page");
			AssertJUnit.assertEquals("Not landing to Report Page","Report Page error");
		}
		
		report_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		report_module_report.info("Navigated to Home");
	}
}
