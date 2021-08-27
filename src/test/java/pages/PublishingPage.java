package pages;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import config.PropertiesFile;
import media.TakeScreenshot;

public class PublishingPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	PropertiesFile prop = new PropertiesFile();
	String widget_view_url;
	
	public void checkWidgetModule(ExtentTest publishing_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		publishing_module_report.info("Navigating to Publishing module");

		try {
			dashboard.clickDashboardIcon("publishing-management");
			common_operation.sleep(2000);
			attach_ss.takeSnapShot("widget_list_page.png");
			publishing_module_report.pass("Landed on Publishing module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "widget_list_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("widget_list_page_error.png");
			publishing_module_report.fail("Error while trying to click Publishing module from dashboard", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "widget_list_page_error.png").build());
			Assert.fail("Error while trying to click Publishing module from dashboard");
			AssertJUnit.assertEquals("Could not find Publishing Module Dashboard icon","Redirection to Widget List from Dashbord error");
		}

		publishing_module_report.info("Navigating to View Widget");
		generateViewWidgetURLByID();

		common_operation.driver.get(widget_view_url);
		if(common_operation.driver.getCurrentUrl().contains("not-found")) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("widget_view_error.png");
			publishing_module_report.fail("Error occured while trying to navigate View widget page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "widget_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View widget page");
			AssertJUnit.assertEquals("Not landing to View widget Page","Widget View Page error");
		}
		else {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("widget_view_page.png");
			publishing_module_report.pass("Navigated to view Widget page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "widget_view_page.png").build());
			common_operation.sleep(1000);
			publishing_module_report.info("Checking View Widget page information");
		}

		publishing_module_report.info("Navigating to Home");
		common_operation.goToHome();

		publishing_module_report.info("Navigated to Home");
		common_operation.sleep(1000);
	}

	public void generateViewWidgetURLByID() {
		widget_view_url = common_operation.getBaseUrl();
		//		if(common_operation.getBaseUrl().contains("admin.dev")) {
		//			content_view_url += "/content/vod/view/" + prop.getProperties("dev_test_content_id");
		//		}
		if(widget_view_url.contains("admin.stage")) {
			widget_view_url += "/manage-widget/" + prop.getProperties("stage_test_widget_id");
		}
		if(widget_view_url.contains("admin.bongo-solutions")) {
			widget_view_url += "/manage-widget/" + prop.getProperties("prod_test_widget_id");
		}
		
	}
}
