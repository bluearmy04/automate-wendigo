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

public class AdCampaignPage {
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	String ad_campaign_view_buttons;
	
	public void checkCampaignModule(ExtentTest campaign_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		campaign_module_report.info("Navigating to Ad Campaign module");
		
		try {
			dashboard.clickDashboardIcon("ad-campaigns");
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("campaign_list_page.png");
			campaign_module_report.pass("Landed on Campaign module", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "campaign_list_page.png").build());
		} catch (Exception e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("campaign_list_page_error.png");
			campaign_module_report.fail("Error occured while trying to navigate Campaign List page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "campaign_list_page_error.png").build());
			Assert.fail("Error occured while trying to navigate Campaign List page");
			AssertJUnit.assertEquals("Not landing to Campaign List Page","Campaign List Page error");
		}
		
		
		campaign_module_report.info("Navigating to View Campaign");
		try {
			clickViewCampaign();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("campaign_view_page.png");
			campaign_module_report.pass("Navigated to view Campaign page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "campaign_view_page.png").build());
		} catch (NoSuchElementException e) {
			common_operation.sleep(1000);
			attach_ss.takeSnapShot("campaign_view_error.png");
			campaign_module_report.fail("Error occured while trying to navigate View Campaign page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "campaign_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View Campaign page");
			AssertJUnit.assertEquals("Not landing to View Campaign Page","Campaign View Page error");
		}		
		
		campaign_module_report.info("Checking View Campaign page information");
		common_operation.sleep(1000);
		
		campaign_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		campaign_module_report.info("Navigated to Home");
	}

	private void clickViewCampaign() throws NoSuchElementException{
		ad_campaign_view_buttons = "/html/body/div[1]/div/div/main/div[2]/div/div[2]/table/tbody/tr[1]/th/div";
		try {
			WebElement view_campaign = common_operation.findElement(ad_campaign_view_buttons);
			if(view_campaign != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
				wait.until(ExpectedConditions.elementToBeClickable(view_campaign)).click();
			}
		} catch (NoSuchElementException e) {
			throw e;
		}
		
	}
}
