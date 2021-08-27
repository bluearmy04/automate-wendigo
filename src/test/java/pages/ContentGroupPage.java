package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import media.TakeScreenshot;

public class ContentGroupPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String group_view_buttons;
	
	public void checkContentGroupModule(ExtentTest group_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		group_module_report.info("Navigating to Content Group module");
		
		try {
			dashboard.clickDashboardIcon("content/groups");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("group_list_page.png");
			group_module_report.pass("Landed on Content Group module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "group_list_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("group_list_page_error.png");
			group_module_report.fail("Error occured while trying to navigate Group list page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "group_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Group list page");
			AssertJUnit.assertEquals("Not landing to View Group Page","Group View Page error");
		}
		
		
		group_module_report.info("Navigating to View Group");
		try {
			//this static index number needs to be taken care of
			clickViewUserWithIndex(3);
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("group_view_page.png");
			group_module_report.pass("Navigated to view group page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "group_view_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("group_view_error.png");
			group_module_report.fail("Error occured while trying to navigate View Group page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "group_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Group page");
			AssertJUnit.assertEquals("Not landing to Group View Page","Group View Page error");
		}		
		
		group_module_report.info("Checking View Group page information");
		common_operation.sleep(1000);
		
		group_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		group_module_report.info("Navigated to Home");
	}
	
	public void clickViewUserWithIndex(int index) throws IndexOutOfBoundsException {
		group_view_buttons = "//span[text()='View']";
		try {
			List<WebElement> group_view =  common_operation.driver.findElements(By.xpath(group_view_buttons));
			//System.out.println(group_view.size());
			if(group_view.size() > 0) {
				group_view.get(index).click();
			}
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}	
	}
}
