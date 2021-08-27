package pages;

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

public class ContentOwnerPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String content_owner_view_buttons;
	
	public void checkContentOwnerModule(ExtentTest content_owner_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		content_owner_module_report.info("Navigating to Content Owner module");
		
		try {
			dashboard.clickDashboardIcon("content-owner");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("owner_list_page.png");
			content_owner_module_report.pass("Landed on Content Owner module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "owner_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("owner_list_page_error.png");
			content_owner_module_report.fail("Error occured while trying to navigate Content Owner List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "owner_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Content Owner List page");
			AssertJUnit.assertEquals("Not landing to Content Owner List Page","Content Owner List Page error");
		}
		
		
		content_owner_module_report.info("Navigating to View Content Owner");
		try {
			clickViewContentOwner();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("owner_view_page.png");
			content_owner_module_report.pass("Navigated to view content owner page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "owner_view_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("owner_view_error.png");
			content_owner_module_report.fail("Error occured while trying to navigate View Content Owner page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "owner_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Content Owner page");
			AssertJUnit.assertEquals("Not landing to View Content Owner Page","Content Owner View Page error");
		}		
		
		content_owner_module_report.info("Checking View Owner page information");
		common_operation.sleep(1000);
		
		content_owner_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		content_owner_module_report.info("Navigated to Home");
	}

	public void clickViewContentOwner() throws NoSuchElementException{
		content_owner_view_buttons = "/html/body/div[1]/div/div/main/div/div[2]/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[2]";
		try {
			WebElement view_owner = common_operation.findElement(content_owner_view_buttons);
			if(view_owner != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
				wait.until(ExpectedConditions.elementToBeClickable(view_owner)).click();
			}
		} catch (NoSuchElementException e) {
			//System.out.println("Cause is 5667");
			throw e;
		}
		
	}
}
