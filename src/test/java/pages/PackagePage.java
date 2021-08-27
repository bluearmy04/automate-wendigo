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

public class PackagePage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String package_view_buttons;
	
	public void checkPackageModule(ExtentTest package_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		package_module_report.info("Navigating to Package module");
		
		try {
			dashboard.clickDashboardIcon("package-management");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("package_list_page.png");
			package_module_report.pass("Landed on Package module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "package_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("package_list_page_error.png");
			package_module_report.fail("Error occured while trying to navigate Package List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "package_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Package List page");
			AssertJUnit.assertEquals("Not landing to Package List Page","Package List Page error");
		}
		
		
		package_module_report.info("Navigating to View Package");
		try {
			clickViewPackage();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("package_view_page.png");
			package_module_report.pass("Navigated to view package page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "package_view_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("package_view_error.png");
			package_module_report.fail("Error occured while trying to navigate View Package page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "package_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Package page");
			AssertJUnit.assertEquals("Not landing to View Package Page","Package View Page error");
		}		
		
		package_module_report.info("Checking View Package page information");
		common_operation.sleep(1000);
		
		package_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		package_module_report.info("Navigated to Home");
	}

	public void clickViewPackage() throws NoSuchElementException{
		package_view_buttons = "/html/body/div[1]/div/div/main/div[2]/div/div[2]/table/tbody/tr[2]/th[2]/div";
		try {
			WebElement view_package = common_operation.findElement(package_view_buttons);
			if(view_package != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
				wait.until(ExpectedConditions.elementToBeClickable(view_package)).click();
			}
		} catch (NoSuchElementException e) {
			//System.out.println("Cause is 5667");
			throw e;
		}
			
	}
}
