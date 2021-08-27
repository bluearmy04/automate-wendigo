package pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import media.TakeScreenshot;

public class UserPages {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String user_view_buttons;
	
	public void checkUserModule(ExtentTest user_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		user_module_report.info("Navigating to User module");
		
		try {
			dashboard.clickDashboardIcon("user");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("user_list_page.png");
			user_module_report.pass("Landed on User module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "user_list_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("user_list_page_error.png");
			user_module_report.fail("Error occured while trying to navigate User list page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "user_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate User list page");
			AssertJUnit.assertEquals("Not landing to User List Page","User List Page error");
		}
		
		
		user_module_report.info("Navigating to View User");
		try {
			//this static index number needs to be taken care of
			clickViewUserWithIndex(3);
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("user_view_page.png");
			user_module_report.pass("Navigated to view user page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "user_view_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("user_view_error.png");
			user_module_report.fail("Error occured while trying to navigate View User page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "user_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View User page");
			AssertJUnit.assertEquals("Not landing to User View Page","User View Page error");
		}		
		
		user_module_report.info("Checking View user page information");
		common_operation.sleep(1000);
		
		user_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		user_module_report.info("Navigated to Home");
	}
	
	public void clickViewUserWithIndex(int index) throws IndexOutOfBoundsException {
		user_view_buttons = "//span[text()='View']";
		try {
			List<WebElement> user_lists =  common_operation.driver.findElements(By.xpath(user_view_buttons));
			//System.out.println(webElements.size());
			if(user_lists.size() > 0) {
				user_lists.get(index).click();
			}
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}	
	}
	
}
