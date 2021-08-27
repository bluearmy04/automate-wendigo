package pages;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import media.TakeScreenshot;

public class AnalyticsPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	
	public void checkAnalyticsPage(ExtentTest analytics_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		analytics_module_report.info("Navigating to Analytics module");
		
		try {
			dashboard.clickDashboardIcon("analytics");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("analytics_list_page.png");
			analytics_module_report.pass("Landed on Analytics Page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "analytics_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("analytics_list_page_error.png");
			analytics_module_report.fail("Error occured while trying to navigate Analytics page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "analytics_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Analytics page");
			AssertJUnit.assertEquals("Not landing to Analytics Page","Analytics Page error");
		}
		
		analytics_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		analytics_module_report.info("Navigated to Home");
	}
}
