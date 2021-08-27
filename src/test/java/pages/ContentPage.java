package pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import config.PropertiesFile;
import media.TakeScreenshot;

public class ContentPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	PropertiesFile prop = new PropertiesFile();
	String content_view_url;
	String live_channel_view_url;

	public void checkContentModule(ExtentTest content_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		content_module_report.info("Navigating to Content module");

		try {
			dashboard.clickDashboardIcon("content/library");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("content_list_page.png");
			content_module_report.pass("Landed on Content module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "content_list_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("content_list_page_error.png");
			content_module_report.fail("Error while trying to click Content module from dashboard", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "content_list_page_error.png").build());
			Assert.fail("Error while trying to click Content module from dashboard");
			AssertJUnit.assertEquals("Could not find Content Module Dashboard icon","Redirection to Content List from Dashbord error");
		}

		content_module_report.info("Navigating to View Content");
		generateViewContentURLByID();

		common_operation.driver.get(content_view_url);
		if(common_operation.driver.getCurrentUrl().contains("not-found")) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("content_view_error.png");
			content_module_report.fail("Error occured while trying to navigate View content page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "content_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Content page");
			AssertJUnit.assertEquals("Not landing to View Content Page","Content View Page error");
		}
		else {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("content_view_page.png");
			content_module_report.pass("Navigated to view content page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "content_view_page.png").build());
			common_operation.sleep(1000);
			content_module_report.info("Checking View content page information");
		}

		content_module_report.info("Navigating to Home");
		common_operation.goToHome();

		content_module_report.info("Navigated to Home");
		common_operation.sleep(1000);
	}

	public void checkLiveTVModule(ExtentTest live_tv_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		live_tv_module_report.info("Navigating to Content module");

		try {
			dashboard.clickDashboardIcon("content/library");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("channel_list_page.png");
			live_tv_module_report.pass("Landed on Content module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "channel_list_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("channel_list_page_error.png");
			live_tv_module_report.fail("Error while trying to click Content module from dashboard", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "channel_list_page_error.png").build());
			Assert.fail("Error while trying to click Content module from dashboard");
			AssertJUnit.assertEquals("Could not find Content Module Dashboard icon","Redirection to Live TV List from Dashbord error");
		}
		
		live_tv_module_report.info("Navigating to View Live TV Channel");
		generateViewLiveChannelURLByID();

		common_operation.driver.get(live_channel_view_url);
		if(common_operation.driver.getCurrentUrl().contains("not-found")) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("channel_view_error.png");
			live_tv_module_report.fail("Error occured while trying to navigate View Live TV page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "channel_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Live TV page");
			AssertJUnit.assertEquals("Not landing to View Live TV Page","Live TV View Page error");
		}
		else {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("channel_view_page.png");
			live_tv_module_report.pass("Navigated to view Live TV page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "channel_view_page.png").build());
			common_operation.sleep(1000);
			live_tv_module_report.info("Checking View Live TV page information");
		}

		live_tv_module_report.info("Navigating to Home");
		common_operation.goToHome();

		live_tv_module_report.info("Navigated to Home");
		common_operation.sleep(1000);

	}


	public void generateViewContentURLByID() {
		content_view_url = common_operation.getBaseUrl();
		//		if(common_operation.getBaseUrl().contains("admin.dev")) {
		//			content_view_url += "/content/vod/view/" + prop.getProperties("dev_test_content_id");
		//		}
		if(content_view_url.contains("admin.stage")) {
			content_view_url += "/content/vod/view/" + prop.getProperties("stage_test_content_id");
		}
		if(content_view_url.contains("admin.bongo-solutions")) {
			content_view_url += "/content/vod/view/" + prop.getProperties("prod_test_content_id");
		}
	}

	public void generateViewLiveChannelURLByID() {
		live_channel_view_url = common_operation.getBaseUrl();
		//		if(common_operation.getBaseUrl().contains("admin.dev")) {
		//			content_view_url += "/content/live-channel/view/" + prop.getProperties("dev_test_live_channel_id");
		//		}
		if(live_channel_view_url.contains("admin.stage")) {
			live_channel_view_url += "/content/live-channel/view/" + prop.getProperties("stage_test_live_channel_id");
		}
		if(live_channel_view_url.contains("admin.bongo-solutions")) {
			live_channel_view_url += "/content/live-channel/view/" + prop.getProperties("prod_test_live_channel_id");
		}
	}
}
