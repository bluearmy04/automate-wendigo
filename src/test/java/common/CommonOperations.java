package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import config.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class CommonOperations {
	static PropertiesFile prop = new PropertiesFile();
	public static String project_path = System.getProperty("user.dir");
	public static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		setBrowser();
//		setEnvironment();
//		LoginPage login = new LoginPage();
//		login.setTextInUserName();
//		sleep(5000);
//		tearDownTest();
	}
	public static void setBrowser() {
		String browser_name = prop.getProperties("browser");
		if(hasValue(browser_name) && browser_name.toLowerCase().contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			//System.out.println(browser_name);
		}
		if(hasValue(browser_name) && browser_name.toLowerCase().contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//System.out.println(browser_name);
		}
		if(hasValue(browser_name) && browser_name.toLowerCase().contains("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			//System.out.println(browser_name);
		}
	}
	
	public static void setEnvironment() {
		String env_name = prop.getProperties("environment");
//		if(hasValue(env_name) && env_name.toLowerCase().contains("dev")) {
//			driver.get("https://admin.dev.bongo-solutions.com/login");
//		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("stage")) {
			driver.get("https://admin.stage.bongo-solutions.com/login");
			//System.out.println(env_name);
		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("prod")) {
			driver.get("https://admin.bongo-solutions.com/login");
			//System.out.println(env_name);
		}

	}
	
	public static void tearDownTest() {
		driver.close();
		driver.quit();
	}
	
//	public static WebElement findElement(String expression) {
//		
//		WebElement element = null;
//		try {
//			element = driver.findElement(By.xpath(expression));
//			return element;
//
//		} catch (Exception e) {
//			System.out.println("Message is " + e.getMessage());
//			System.out.println("Cause is " + e.getCause());
//			System.out.println("Error occured while trying to find xpath: " + expression);
//			e.printStackTrace();
//			return element;
//		}
//	}
	
	public static WebElement findElement(String expression) throws NoSuchElementException{
		//System.out.println("test");
		WebElement element = null;

		element = driver.findElement(By.xpath(expression));
		return element;

	}
	
	 public static void sleep(int miliseconds) {
			try {
				Thread.sleep(miliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
	public static boolean hasValue(String s) {
		if(s.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	public static void refreshPage() {
		driver.navigate().refresh();
	}
	
	public String dateTimeFormatter(String format) {
		LocalDateTime myDateObj = LocalDateTime.now(); 
		//DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(format);
		
		String formattedDate = myDateObj.format(myFormatObj);  
		return formattedDate;
	    //System.out.println(formattedDate);
	}
	
	public static void goToHome() {
		String env_name = prop.getProperties("environment");
//		if(hasValue(env_name) && env_name.toLowerCase().contains("dev")) {
//			driver.get("https://admin.dev.bongo-solutions.com/home");
//		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("stage")) {
			driver.get("https://admin.stage.bongo-solutions.com/home");
			//System.out.println(env_name);
		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("prod")) {
			driver.get("https://admin.bongo-solutions.com/home");
			//System.out.println(env_name);
		}	
	}
	
	public static String getBaseUrl() {
		String env_name = prop.getProperties("environment");
		String base_url = "";
//		if(hasValue(env_name) && env_name.toLowerCase().contains("dev")) {
//			return "https://admin.dev.bongo-solutions.com";
//		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("stage")) {
			base_url = "https://admin.stage.bongo-solutions.com";
			//System.out.println(env_name);
		}
		if(hasValue(env_name) && env_name.toLowerCase().contains("prod")) {
			base_url = "https://admin.bongo-solutions.com";
		}
		return base_url;
	}
}
