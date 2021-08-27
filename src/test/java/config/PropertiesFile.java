package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	static Properties prop = new Properties();
	static String project_path = System.getProperty("user.dir");
	
//	public static void main(String[] args) {
//		System.out.println(getProperties("environment"));
//	}
	
	public static String getProperties(String property_name) {
		String prop_value = "";
		InputStream input;
		try {
			input = new FileInputStream(project_path + "/src/test/java/config/config.properties");
			prop.load(input);
			prop_value = prop.getProperty(property_name);
			return prop_value;
		} catch (Exception e) {
			System.out.println("Message is " + e.getMessage());
			System.out.println("Cause is " + e.getCause());
			e.printStackTrace();
			return prop_value;
		}
	}
}
