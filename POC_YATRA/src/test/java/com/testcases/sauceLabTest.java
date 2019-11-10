package com.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class sauceLabTest {
	static WebDriver driver;
	static String URL="https://Satishkamble11:b2396076-18c1-41ec-94ab-ab98069975cd@ondemand.saucelabs.com:443/wd/hub";
 public static void main(String args[]) throws MalformedURLException{
	 	/*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	 	capabilities.setCapability("platform", "Windows 7");
	 	capabilities.setCapability("browsername", "chrome");
	 	capabilities.setCapability("version", "60");
//		capabilities.setCapability("parent-tunnel", "sauce_admin");
//		capabilities.setCapability("tunnelIdentifier", "OptumSharedTunnel-Prd");
		capabilities.setCapability("recordVideo", true);
	 	*/ 	
		MutableCapabilities sauceCaps = new MutableCapabilities();
		sauceCaps.setCapability("username", "Satishkamble11");
		sauceCaps.setCapability("accessKey", "b2396076-18c1-41ec-94ab-ab98069975cd");
		sauceCaps.setCapability("name", "Test Temp");
		 
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("sauce:options", sauceCaps);
		caps.setCapability("platformName", "Windows 10");
		caps.setCapability("browserName", "firefox");
		caps.setCapability("browserVersion", "latest");
		         
		
		
		
		try{
			WebDriver driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), caps);
//			driver= new RemoteWebDriver(new URL(URL), capabilities);
//		((RemoteWebDriver) driver.get()).setFileDetector(new LocalFileDetector());	//Added to upload the file as attachment for PA on SauceLabs VM
			System.out.println("https://saucelabs.com/beta/tests/" + (((RemoteWebDriver) driver).getSessionId()).toString());
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		 }

 	}
 
}
