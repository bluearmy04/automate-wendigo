package main;

import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.AssertJUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import common.CommonOperations;
import pages.LoginPage;
import pages.MetaDataPage;
import pages.PackagePage;
import pages.PublishingPage;
import pages.ReportPage;
import pages.UserPages;
import pages.AdCampaignPage;
import pages.AnalyticsPage;
import pages.CastAndCrewPage;
import pages.ContentGroupPage;
import pages.ContentOwnerPage;
import pages.ContentPage;
import pages.CustomerPage;
import pages.DashboardPage;
import pages.GeoRestrictionProfilePage;
import report.GenerateHtmlReport;
import report.SendReportViaEmail;
import media.TakeScreenshot;

public class FullRegression {
	WebDriverWait wait;
	CommonOperations common_operation = new CommonOperations();
	public static boolean is_loggedin = false;
	GenerateHtmlReport extent_report = new GenerateHtmlReport();
	SendReportViaEmail email_report = new SendReportViaEmail();

	@BeforeSuite
	public void beforeTest() {
		common_operation.setBrowser();
		common_operation.setEnvironment();
		common_operation.refreshPage();
		extent_report.setUpReport("FullRegressionTestResult.html");
	}

	@Test(priority = 0)
	public void loginProcess() {
		LoginPage login = new LoginPage(); 
		ExtentTest login_report = extent_report.createTest("Login", "Login Process Automate");
		login.loginToSystem(login_report);
	}

	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 1, groups = {"UserModule"})
	public void userModule() {
		if(is_loggedin) {
			UserPages user = new UserPages();
			ExtentTest user_module_report = extent_report.createTest("User Module", "User Module Check Automate");	
			user.checkUserModule(user_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 2, groups = {"CustomerModule"})
	public void customerModule() {
		if(is_loggedin) {
			CustomerPage customer = new CustomerPage();
			ExtentTest customer_module_report = extent_report.createTest("Customer Module", "Customer Module Check Automate");	
			customer.checkCustomerModule(customer_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 3, groups = {"GeoProfileModule"})
	public void geoProfileModule() {
		if(is_loggedin) {
			GeoRestrictionProfilePage geo_profile = new GeoRestrictionProfilePage();
			ExtentTest geo_profile_module_report = extent_report.createTest("Geo Profile Module", "Geo Profile Module Check Automate");	
			geo_profile.checkCustomerModule(geo_profile_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 4, groups = {"ContentModule"})
	public void contentModule() {
		if(is_loggedin) {
			ContentPage content = new ContentPage();
			ExtentTest content_module_report = extent_report.createTest("Content Module", "Content Module Check Automate");	
			content.checkContentModule(content_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 5, groups = {"LiveTVModule"})
	public void liveTVModule() {
		if(is_loggedin) {
			ContentPage content = new ContentPage();
			ExtentTest live_tv_module_report = extent_report.createTest("Live TV Module", "Live TV Module Check Automate");	
			content.checkLiveTVModule(live_tv_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 6, groups = {"PackageModule"})
	public void publishingModule() {
		if(is_loggedin) {
			PublishingPage publishing = new PublishingPage();
			ExtentTest publishing_module_report = extent_report.createTest("Publishing Module", "Publishing Module Check Automate");	
			publishing.checkWidgetModule(publishing_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 7, groups = {"PublishingModule"})
	public void packageModule() {
		if(is_loggedin) {
			PackagePage packaging = new PackagePage();
			ExtentTest package_module_report = extent_report.createTest("Package Module", "Package Module Check Automate");	
			packaging.checkPackageModule(package_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 8, groups = {"AnalyticsModule"})
	public void analyticsModule() {
		if(is_loggedin) {
			AnalyticsPage anal = new AnalyticsPage();
			ExtentTest anal_module_report = extent_report.createTest("Analytics Module", "Analytics Module Check Automate");	
			anal.checkAnalyticsPage(anal_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 9, groups = {"ReportModule"})
	public void reportModule() {
		if(is_loggedin) {
			ReportPage report = new ReportPage();
			ExtentTest report_module_report = extent_report.createTest("Report Module", "Report Module Check Automate");	
			report.checkReportsPage(report_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 10, groups = {"ContentOwnerModule"})
	public void contentOwnerModule() {
		if(is_loggedin) {
			ContentOwnerPage owner = new ContentOwnerPage();
			ExtentTest owner_module_report = extent_report.createTest("Content Owner Module", "Content Owner Module Check Automate");	
			owner.checkContentOwnerModule(owner_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 11, groups = {"AdCampaignModule"})
	public void adCampaignModule() {
		if(is_loggedin) {
			AdCampaignPage ads = new AdCampaignPage();
			ExtentTest campaign_module_report = extent_report.createTest("Campaign Module", "Ad Campaign Module Check Automate");	
			ads.checkCampaignModule(campaign_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 12, groups = {"CastAndCrewModule"})
	public void castAndCrewModule() {
		if(is_loggedin) {
			CastAndCrewPage cc_module = new CastAndCrewPage();
			ExtentTest cc_module_report = extent_report.createTest("Cast & Crew Module", "Cast & Crew Module Check Automate");	
			cc_module.checkCastAndCrewModule(cc_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 13, groups = {"ContentGroupModule"})
	public void contentGroupModule() {
		if(is_loggedin) {
			ContentGroupPage group_module = new ContentGroupPage();
			ExtentTest group_module_report = extent_report.createTest("Content Group Module", "Content Group Module Check Automate");	
			group_module.checkContentGroupModule(group_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 14, groups = {"MetaDataModule"})
	public void tagModule() {
		if(is_loggedin) {
			MetaDataPage tag_module = new MetaDataPage("tags");
			ExtentTest tag_module_report = extent_report.createTest("Tag Module", "Tag Module Check Automate");	
			tag_module.checkMetaDataModule(tag_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 15, groups = {"MetaDataModule"})
	public void genreModule() {
		if(is_loggedin) {
			MetaDataPage genre_module = new MetaDataPage("genres");
			ExtentTest genre_module_report = extent_report.createTest("Genre Module", "Genre Module Check Automate");	
			genre_module.checkMetaDataModule(genre_module_report);
		}
	}
	
	//@Ignore
	@Test(dependsOnMethods = {"loginProcess"}, priority = 16, groups = {"MetaDataModule"})
	public void categoryModule() {
		if(is_loggedin) {
			MetaDataPage category_module = new MetaDataPage("categories");
			ExtentTest category_module_report = extent_report.createTest("Category Module", "Category Module Check Automate");	
			category_module.checkMetaDataModule(category_module_report);
		}
	}
	
	@AfterSuite
	public void tearDownTest() {
		common_operation.tearDownTest();
		extent_report.flush();
		//email_report.sendEmail();
	}
}
