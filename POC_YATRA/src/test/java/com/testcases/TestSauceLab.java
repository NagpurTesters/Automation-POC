package com.testcases;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestSauceLab {
	public static final String USERNAME="Satishkamble11";
	public static final String ACCESSKEY="b2396076-18c1-41ec-94ab-ab98069975cd";
	public static final String URL="https://" + USERNAME + ":" + ACCESSKEY + "@ondemand.saucelabs.com:443/wd/hub";
	public static String id;
	static Properties OR;
	
	public TestSauceLab() {
		try {
			OR=new Properties();
			FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\config\\com\\properties\\OR.properties");
			OR.load(fi);
		} catch (Exception e) {
		}
	}
	
	@Test
	public static void sampleTest() throws MalformedURLException{
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ANdroid Emulator");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
//		cap.setCapability(MobileCapabilityType.UDID, "ee24a865");
		cap.setCapability("recordVideo", true);
		cap.setCapability(MobileCapabilityType.APP, "https://github.com/NagpurTesters/Automation-POC/tree/master/Yatra/files/Yatra.apk");
		cap.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.15.1");
		cap.setCapability("appPackage","com.yatra.base");
		cap.setCapability("appActivity","com.yatra.flights.activity.FlightBookingActivity");
		AndroidDriver<MobileElement> driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		id=((RemoteWebDriver)driver).getSessionId().toString();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		
		driver.findElement(By.id(OR.getProperty("From"))).click();
		driver.findElement(By.id(OR.getProperty("SearchBar"))).clear();
		driver.findElement(By.id(OR.getProperty("SearchBar"))).sendKeys("Delhi");
		WebElement ele=driver.findElement(By.className(OR.getProperty("Source")));
		ele.click();
		
		
		
	}
	
	

}
