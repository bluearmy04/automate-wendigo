package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.NoSuchElementException;

import pages.DashboardPage;

public class test {
	static CommonOperations common_operation = new CommonOperations();
	static DashboardPage dashboard = new DashboardPage();
	public static void main(String[] args){  
		System.out.println("ass");
		common_operation.setBrowser();
		System.out.println("assd");
	
			try {
				dashboard.clickDashboardIcon("asa");
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("catched");
			}
		
	}  

}
