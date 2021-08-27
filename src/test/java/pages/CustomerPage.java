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

public class CustomerPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String customer_view_buttons;
	
	public void checkCustomerModule(ExtentTest customer_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		customer_module_report.info("Navigating to Customer module");
		
		try {
			dashboard.clickDashboardIcon("customers");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("customer_list_page.png");
			customer_module_report.pass("Landed on Customer module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "customer_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("customer_list_page_error.png");
			customer_module_report.fail("Error occured while trying to navigate Customer List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "customer_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Customer List page");
			AssertJUnit.assertEquals("Not landing to Customer List Page","Customer List Page error");
		}
		
		
		customer_module_report.info("Navigating to View Customer");
		try {
			clickViewCustomer();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("customer_view_page.png");
			customer_module_report.pass("Navigated to view customer page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "customer_view_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("customer_view_error.png");
			customer_module_report.fail("Error occured while trying to navigate View Customer page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "customer_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Customer page");
			AssertJUnit.assertEquals("Not landing to View Customer Page","Customer View Page error");
		}		
		
		customer_module_report.info("Checking View Customer page information");
		common_operation.sleep(1000);
		
		customer_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		customer_module_report.info("Navigated to Home");
	}
	
	public void clickViewCustomer() throws NoSuchElementException{
		customer_view_buttons = "/html/body/div[1]/div/div/main/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[3]";
		try {
			WebElement element = common_operation.findElement(customer_view_buttons);
			WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (NoSuchElementException e) {
			throw e;
		}
		
	}
}
