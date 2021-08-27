package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import media.TakeScreenshot;

public class GeoRestrictionProfilePage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String geo_profile_view_buttons;
	
	public void checkCustomerModule(ExtentTest geo_profile_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		geo_profile_module_report.info("Navigating to Geo-Profile module");
		
		try {
			dashboard.clickDashboardIcon("geo-profile");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("geo_profile_list_page.png");
			geo_profile_module_report.pass("Landed on Geo Profile module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "geo_profile_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("geo_profile_list_page_error.png");
			geo_profile_module_report.fail("Error occured while trying to navigate to Geo Profile List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "geo_profile_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate to Geo Profile List page");
			AssertJUnit.assertEquals("Not landing to Geo Profile List Page","Geo Profile List Page error");
		}
		
		
		geo_profile_module_report.info("Navigating to View/Edit Geo-Profile");
		try {
			//this static index number needs to be taken care of
			clickViewGeoProfileWithIndex(3);
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("geo_profile_view_page.png");
			geo_profile_module_report.pass("Navigated to view/edit geo-profile page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "geo_profile_view_page.png").build());
		} catch (Exception e) {
			System.out.println("Message is " + e.getMessage());
			System.out.println("Cause is " + e.getCause());
			e.printStackTrace();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("geo_profile_view_error.png");
			geo_profile_module_report.fail("Error occured while trying to navigate View/Edit Geo Profile page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "geo_profile_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View/Edit Geo Profile page");
			AssertJUnit.assertEquals("Not landing to View Geo Profile Page","Geo Profile View Page error");
		}		
		
		geo_profile_module_report.info("Checking View/Edit Geo Profile page information");
		common_operation.sleep(1000);
		
		geo_profile_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		geo_profile_module_report.info("Navigated to Home");
	}
	
	public void clickViewGeoProfileWithIndex(int index) {
		geo_profile_view_buttons = "//span[text()='Manage Profile']";
		List<WebElement> geo_profile_lists =  common_operation.driver.findElements(By.xpath(geo_profile_view_buttons));
		//System.out.println(webElements.size());
		if(geo_profile_lists.size() > 0) {
			geo_profile_lists.get(index).click();
		}
		
	}
}
