package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.base.Function;

import common.CommonOperations;
import config.PropertiesFile;
import main.FullRegression;
import media.TakeScreenshot;

public class LoginPage {
	CommonOperations common_operation = new CommonOperations();
	FullRegression set_login_status = new FullRegression();
	PropertiesFile prop = new PropertiesFile();
	public static JavascriptExecutor js;
	TakeScreenshot attach_ss = new TakeScreenshot();
	String xpath_username = "/html/body/div/div/div/main/div/div/div[1]/div/div/div[2]/div[1]/div/input";
	String xpath_password = "/html/body/div/div/div/main/div/div/div[1]/div/div/div[2]/div[2]/div/input";
	String xpath_btn_login = "/html/body/div/div/div/main/div/div/div[1]/div/div/div[2]/div[4]/button";


	public void setTextInUserName() {
		WebElement username_input = common_operation.findElement(xpath_username);
		if(username_input != null) {
			String env_name = prop.getProperties("environment");
			//			if(env_name.toLowerCase().contains("dev")) {
			//				username_input.sendKeys(prop.getProperties("environment"));
			//			}
			if(env_name.toLowerCase().contains("stage")) {
				username_input.sendKeys(prop.getProperties("stage_username"));
			}
			if(env_name.toLowerCase().contains("production")) {
				username_input.sendKeys(prop.getProperties("prod_username"));
			}
		}
	}

	public void setTextInPassword() {
		WebElement password_input = common_operation.findElement(xpath_password);
		if(password_input != null) {
			String env_name = prop.getProperties("environment");
			//			if(env_name.toLowerCase().contains("dev")) {
			//				username_input.sendKeys(prop.getProperties("environment"));
			//			}
			if(env_name.toLowerCase().contains("stage")) {
				password_input.sendKeys(prop.getProperties("stage_password"));
			}
			if(env_name.toLowerCase().contains("production")) {
				password_input.sendKeys(prop.getProperties("prod_password"));
			}
		}
	}


	public boolean clickLogin() {
		WebElement check_username_password_error;
		try {
			check_username_password_error = common_operation.driver.findElement(By.xpath("//p[contains(text(), 'Input should be valid email address') or contains(text(),'Password should not be empty')]"));
		} catch (NoSuchElementException e) {
			//No error occurered while login so setting check_username_password_error as null
			//System.out.println("Making error webelement as null as no error occured");
			check_username_password_error = null;
		}
		
		//check_username_password_error = common_operation.findElement("//p[contains(text(), 'Input should be valid email address') or contains(text(),'Password should not be empty') or contains(text(),'Password is incorrect') or contains(text(),'User is not found')]");
		WebElement login_button = common_operation.findElement(xpath_btn_login);
		if(login_button != null && login_button.isEnabled() && check_username_password_error==null) {
			WebDriverWait wait = new WebDriverWait(common_operation.driver, 5);	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_btn_login))).click();
			//System.out.println("No error");
			return true;
		}
		else {
			//System.out.println("Client sided error");
			return false;
		}
	}

	public boolean check_authentication() {
		js = ((JavascriptExecutor)common_operation.driver);
		String refresh_token = (String) js.executeScript(String.format(
				"return window.localStorage.getItem('%s');", "_REFRESH_TOKEN"));
		String isLoggedIn = (String) js.executeScript(String.format(
				"return window.localStorage.getItem('%s');", "isLoggedIn"));
		//System.out.println(refresh_token + " " + isLoggedIn);
		if(Boolean.parseBoolean(isLoggedIn) && common_operation.hasValue(refresh_token)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void loginToSystem(ExtentTest login_report) {
		login_report.info("Setting username");
		setTextInUserName();
		
		login_report.info("Setting password");
		setTextInPassword();
		
		common_operation.sleep(1000);
		attach_ss.takeSnapShot("set_username_pass.png");
		login_report.info("Waiting for 2 seconds",  MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" +"set_username_pass.png").build());
		
		if(clickLogin()) {
			login_report.info("Login button clicked");
			common_operation.sleep(2000);
			
			if(check_authentication()) {
				set_login_status.is_loggedin = true;
				System.out.println("Login successful");
				attach_ss.takeSnapShot("login_succesful.png");
				login_report.pass("User logged in to the system", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" +"login_succesful.png").build());
			}
			else {
				attach_ss.takeSnapShot("login_failed.png");
				login_report.fail("Login fails due to server side error",MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" +"login_failed.png").build());
				System.out.println("Login fails due to server side error");
				Assert.fail("Login fails due to server side error");
				AssertJUnit.assertEquals("Not getting email or password correct","Valid User and correct password");
			}
		}
		else {
			attach_ss.takeSnapShot("login_failed.png");
			login_report.fail("Either Password or Email is incorrect", MediaEntityBuilder.createScreenCaptureFromPath(common_operation.project_path + "/test-steps-screenshots/" + "login_failed.png").build());
			System.out.println("Either Password or Email is incorrect");
			Assert.fail("Either Password or Email is incorrect");
			AssertJUnit.assertEquals("Getting invalid email or blank password","Correct email format & correct password");
		}
	}
}
