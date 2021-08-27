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
import config.PropertiesFile;
import media.TakeScreenshot;

public class MetaDataPage {
	String name_of_metadata;
	CommonOperations common_operation = new CommonOperations();
	DashboardPage dashboard = new DashboardPage();
	TakeScreenshot attach_ss = new TakeScreenshot();
	PropertiesFile prop = new PropertiesFile();
	String metadata_view_buttons;
	String metadata_view_url;
	
	public MetaDataPage(String name_of_metadata) {
		this.name_of_metadata = name_of_metadata;
	}
	
	public void checkMetaDataModule(ExtentTest metadata_module_report) {
		common_operation.goToHome();
		common_operation.sleep(1000);
		
		metadata_module_report.info("Navigating to " + name_of_metadata + " metadata module");
		common_operation.driver.get(common_operation.getBaseUrl() + "/metadata/" + name_of_metadata);
		common_operation.sleep(1000);
		attach_ss.takeSnapShot(name_of_metadata + "_list_page.png");
		metadata_module_report.pass("Navigated to "+name_of_metadata+" list page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + name_of_metadata +"_list_page.png").build());
		
		metadata_module_report.info("Navigating to View "+ name_of_metadata);
		generateMetadataURLByID();
		//System.out.println("met:"+ metadata_view_url);
		common_operation.driver.get(metadata_view_url);
		if(common_operation.driver.getCurrentUrl().contains("not-found")){
			common_operation.sleep(1000);
			attach_ss.takeSnapShot(name_of_metadata + "_view_error.png");
			metadata_module_report.fail("Error occured while trying to navigate View "+name_of_metadata+" page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + name_of_metadata +"_view_error.png").build());
			Assert.fail("Error occured while trying to navigate View "+name_of_metadata+" page");
			AssertJUnit.assertEquals("Not landing to View "+name_of_metadata+" Page", name_of_metadata+ " View Page error");
		}
		else{
			//clickViewMetaData();
			common_operation.sleep(1000);
			attach_ss.takeSnapShot(name_of_metadata + "_view_page.png");
			metadata_module_report.pass("Navigated to view "+name_of_metadata+" metadata page", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + name_of_metadata +"_view_page.png").build());
		}		
		
		metadata_module_report.info("Checking View "+name_of_metadata+" page information");
		common_operation.sleep(1000);
		
		metadata_module_report.info("Navigating to Home");
		common_operation.goToHome();
		
		metadata_module_report.info("Navigated to Home");
	}

	public void clickViewMetaData() throws NoSuchElementException{
		if(name_of_metadata.toLowerCase().contains("tags")) {
			metadata_view_buttons = "/html/body/div[1]/div/div/main/div[2]/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[2]";
		}
		//this xpath does not work, need to modify it later
		if(name_of_metadata.toLowerCase().contains("genres")) {
			metadata_view_buttons = "/html/body/div[@id='root']/div[@class='App']/div[@class='jss1']/main[@class='jss2']/div[@class='MuiBox-root jss233']/div[@class='MuiBox-root jss246']/div[@class='MuiPaper-root MuiPaper-elevation4 MuiPaper-rounded']/div[@class='MuiBox-root jss278']/div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12']/div[@class='MuiPaper-root MuiPaper-elevation3 MuiPaper-rounded']/div[@class='MuiBox-root jss283']/div[@class='MuiTableContainer-root']/table[@class='MuiTable-root jss281']/tbody[@class='MuiTableBody-root']/tr[@class='MuiTableRow-root jss282'][1]/td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignLeft'][4]/button[@class='MuiButtonBase-root MuiButton-root MuiButton-text jss306 jss307   MuiButton-textPrimary']/span[@class='MuiButton-label']";
		}
		//this xpath does not work, need to modify it later
		if(name_of_metadata.toLowerCase().contains("categories")) {
			metadata_view_buttons = "/html/body/div[1]/div/div/main/div/div[2]/div/div[2]/div/div/div/div/table/tbody/tr[1]";
		}
		try {
			WebElement view_metadata = common_operation.findElement(metadata_view_buttons);
			if(view_metadata != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 10);	
				wait.until(ExpectedConditions.elementToBeClickable(view_metadata)).click();
			}
		} catch (NoSuchElementException e) {
			//System.out.println("Cause is 5667");
			throw e;
		}		
	}
	
	public void generateMetadataURLByID() {
		metadata_view_url = common_operation.getBaseUrl();
		if(metadata_view_url.contains("admin.stage")) {
			if(name_of_metadata.toLowerCase().contains("tags")) {
				metadata_view_url += "/metadata/tags/" + prop.getProperties("stage_tag_id");
			}
			if(name_of_metadata.toLowerCase().contains("genres")) {
				metadata_view_url += "/metadata/genres/" + prop.getProperties("stage_genre_id");
			}
			if(name_of_metadata.toLowerCase().contains("categories")) {
				metadata_view_url += "/metadata/categories/id/" + prop.getProperties("stage_categories_id");
			}
		}
		if(metadata_view_url.contains("admin.bongo-solutions")) {
			if(name_of_metadata.toLowerCase().contains("tags")) {
				metadata_view_url += "/metadata/tags/" + prop.getProperties("prod_tag_id");
			}
			if(name_of_metadata.toLowerCase().contains("genres")) {
				metadata_view_url += "/metadata/genres/" + prop.getProperties("prod_genre_id");
			}
			if(name_of_metadata.toLowerCase().contains("categories")) {
				metadata_view_url += "/metadata/categories/id/" + prop.getProperties("prod_categories_id");
			}
		}
	}
}
