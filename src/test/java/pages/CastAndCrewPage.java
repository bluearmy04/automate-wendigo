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

public class CastAndCrewPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String cc_view_buttons;
	
	public void checkCastAndCrewModule(ExtentTest cc_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		cc_module_report.info("Navigating to Cast & Crew module");
		
		try {
			dashboard.clickDashboardIcon("cast-and-crew");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("cc_list_page.png");
			cc_module_report.pass("Landed on Cast & Crew module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "cc_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("cc_list_page_error.png");
			cc_module_report.fail("Error occured while trying to navigate Cast & Crew List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "cc_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Cast & Crew List page");
			AssertJUnit.assertEquals("Not landing to Cast & Crew List Page","Cast & Crew List Page error");
		}
		
		
		cc_module_report.info("Navigating to View Cast & Crew");
		try {
			clickViewCastAndCrew();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("cc_view_page.png");
			cc_module_report.pass("Navigated to view Cast & Crew page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "cc_view_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("cc_view_error.png");
			cc_module_report.fail("Error occured while trying to navigate View Cast & Crew page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "cc_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Cast & Crew page");
			AssertJUnit.assertEquals("Not landing to View Cast & Crew Page","Cast & Crew View Page error");
		}		
		
		cc_module_report.info("Checking View Cast & Crew page information");
		common_operation.sleep(1000);
		
		cc_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		cc_module_report.info("Navigated to Home");
	}

	public void clickViewCastAndCrew() throws NoSuchElementException{
		cc_view_buttons = "/html/body/div[1]/div/div/main/div[2]/div/div[2]/div[2]/table/tbody/tr[1]/td[1]";
		try {
			WebElement view_cc = common_operation.findElement(cc_view_buttons);
			if(view_cc != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
				wait.until(ExpectedConditions.elementToBeClickable(view_cc)).click();
			}
		} catch (NoSuchElementException e) {
			//System.out.println("Cause is 5667");
			throw e;
		}
			
	}
}
