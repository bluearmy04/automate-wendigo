package media;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import common.CommonOperations;

public class TakeScreenshot {
	CommonOperations common_operation = new CommonOperations();
	//String project_path = System.getProperty("user.dir");


	public void takeSnapShot(String image_file_name){

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot)common_operation.driver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File DestFile = new File(common_operation.project_path + "/test-steps-screenshots/" + image_file_name);

		//Copy file at destination

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			System.out.println("Message is " + e.getMessage());
			System.out.println("Cause is " + e.getCause());
			e.printStackTrace();
		}

	}

}
