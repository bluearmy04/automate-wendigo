package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonOperations;

public class DashboardPage {
	CommonOperations common_operation = new CommonOperations();
	String module_page_link_xpath;
	String dashboard_return_xpath;

	public void clickDashboardIcon(String item_name) throws NoSuchElementException{
		module_page_link_xpath = "//a[@href='/" + item_name +"']";
		try {
			WebElement module_page_link = common_operation.findElement(module_page_link_xpath);
			if(module_page_link != null) {
				WebDriverWait wait = new WebDriverWait(common_operation.driver, 2);	
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(module_page_link_xpath))).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("mafi de de yaar");
			throw e;
		}

	}
	
	public void returnToDashboard() {
		dashboard_return_xpath = "//path[@d='M3 13h8V3H3v10zm0 8h8v-6H3v6zm10 0h8V11h-8v10zm0-18v6h8V3h-8z']";
		WebElement dashboard_icon_link = common_operation.findElement(dashboard_return_xpath);
		if(dashboard_icon_link != null) {
			WebDriverWait wait = new WebDriverWait(common_operation.driver, 2);	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dashboard_return_xpath))).click();
		}
	}
}
