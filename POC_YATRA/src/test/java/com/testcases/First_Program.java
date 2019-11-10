/**
 * 
 */
package com.testcases;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;



/**
 * @author Satish
 *
 */

public class First_Program{
	public static final String USERNAME="Satishkamble11";
	public static final String ACCESSKEY="b2396076-18c1-41ec-94ab-ab98069975cd";
	public static final String URL="https://" + USERNAME + ":" + ACCESSKEY + "@ondemand.saucelabs.com:443/wd/hub";
//	https://Satishkamble11:b2396076-18c1-41ec-94ab-ab98069975cd@ondemand.saucelabs.com:443/wd/hub
	Properties OR;
	String apk="https://github.com/NagpurTesters/Automation-POC/blob/master/Yatra/files/Yatra.apk";
	File appDir=new File("src/com/apk/com/Native/apk");
	File app=new File(appDir,"ApiDemos-debug.apk");
	
	WebDriver driver;
	
	
	public First_Program() {
		try {
			OR=new Properties();
			FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\config\\com\\properties\\OR.properties");
			OR.load(fi);
		} catch (Exception e) {

		}
	}

	
		@Test
		public void test() {
			try {			
				DesiredCapabilities caps = DesiredCapabilities.android();
/*				caps.setCapability("appiumVersion", "1.13.0");
				caps.setCapability("deviceName","Android GoogleAPI Emulator");
				caps.setCapability("deviceOrientation", "portrait");
				caps.setCapability("browserName", "");
				caps.setCapability("platformVersion","9.0");
				caps.setCapability("recordVideo", true);
				caps.setCapability("platformName","Android");
				caps.setCapability("name","Android Automation");*/
				
				caps.setCapability("appiumVersion", "1.13.0");
				caps.setCapability("deviceName","Samsung Galaxy S9 WQHD GoogleAPI Emulator");
				caps.setCapability("deviceOrientation", "portrait");
				caps.setCapability("browserName", "");
				caps.setCapability("platformVersion","9.0");
				caps.setCapability("platformName","Android");
//				caps.setCapability("app","http://github.com/NagpurTesters/Automation-POC/blob/master/Yatra/files/Yatra.apk");
//				caps.setCapability("app","sauce-storage:https://app.testobject.com/#/satishkamble11/yatra/manual/Yatra.apk");
//				caps.setCapability("app", app.getAbsolutePath());
				caps.setCapability("app","sauce-storage:http://github.com/NagpurTesters/Automation-POC/blob/master/Yatra/files/Yatra.apk");
				
				
				try {
//					driver=new RemoteWebDriver(new URL(URL), caps);				
					driver=new AndroidDriver<MobileElement>(new URL(URL),caps);
//					((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
					System.out.println("https://saucelabs.com/beta/tests/" + (((RemoteWebDriver) driver).getSessionId()).toString());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				user_enter_Source_name();
				user_enter_Destination_name();
				user_enter_Depart_date();
				click_Search_Button();
				verify_count_searchresult();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
			
		}
		

		
		public void user_enter_Source_name() {
//			driver.findElement(By.id("LoginIcon")).click();
			driver.findElement(By.id(OR.getProperty("From"))).click();
			driver.findElement(By.id(OR.getProperty("SearchBar"))).clear();
			driver.findElement(By.id(OR.getProperty("SearchBar"))).sendKeys("Delhi");
			WebElement ele=driver.findElement(By.className(OR.getProperty("Source")));
			Wait();
			ele.click();
			
		}

		public void user_enter_Destination_name() {
			driver.findElement(By.id(OR.getProperty("DestinationButton"))).click();
			driver.findElement(By.id(OR.getProperty("SearchBar"))).clear();
			driver.findElement(By.id(OR.getProperty("SearchBar"))).sendKeys("Mumbai");
			WebElement ele=driver.findElement(By.className(OR.getProperty("Destination")));
			Wait();
			ele.click();
		}

		public void user_enter_Depart_date() {
			driver.findElement(By.id(OR.getProperty("DepartButton"))).click();
			driver.findElement(By.id(OR.getProperty("OKButton"))).click();
		}

		public void click_Search_Button() {
			driver.findElement(By.id(OR.getProperty("SearchButton"))).click();
		}

		public void verify_count_searchresult() {
			List<WebElement> list=driver.findElements(By.id(OR.getProperty("List")));
			int count=list.size();
			System.out.println("The respective search result count:-"+count);
		}

		public void Wait() {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
		} 
		
		
		
		
		
		
}
