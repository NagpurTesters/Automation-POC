package POC.Yatra.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OnlineBookingPage {
	AppiumDriver<MobileElement> driver;
	DesiredCapabilities cap=new DesiredCapabilities();
	
	
	public void setdesiredCapability(){
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("udid", "ee24a865");
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion","9");
		cap.setCapability("appPackage","com.yatra.base");
		cap.setCapability("appActivity","com.yatra.flights.activity.FlightBookingActivity");
	}
	
	public void Open_yatra_application() {
		try {
			setdesiredCapability();
			driver=new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("--Application started--");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void click_on_flight_icon() {
		driver.findElement(By.id("com.yatra.base:id/iv_lob_icon")).click();
	}
	
	public void Wait() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	} 

	public void user_enter_Source_name() {
		driver.findElement(By.id("com.yatra.base:id/org_cityname_textview")).click();
		driver.findElement(By.id("com.yatra.base:id/edit_location_search_in_actionbar")).clear();
		driver.findElement(By.id("com.yatra.base:id/edit_location_search_in_actionbar")).sendKeys("Delhi");
		WebElement ele=driver.findElement(By.className("com.yatra.base:id/location_city_textview"));
		Wait();
		ele.click();
		
	}

	public void user_enter_Destination_name() {
		driver.findElement(By.id("com.yatra.base:id/dest_cityname_textview")).click();
		driver.findElement(By.id("com.yatra.base:id/edit_location_search_in_actionbar")).clear();
		driver.findElement(By.id("com.yatra.base:id/edit_location_search_in_actionbar")).sendKeys("Mumbai");
		WebElement ele=driver.findElement(By.className("com.yatra.base:id/location_code_textview"));
		Wait();
		ele.click();
	}

	public void user_enter_Depart_date() {
		driver.findElement(By.id("com.yatra.base:id/depart_month_textview")).click();
		driver.findElement(By.id("com.yatra.base:id/btn_ok")).click();
	}

	public void click_Search_Button() {
		driver.findElement(By.id("com.yatra.base:id/search_button")).click();
	}

	public void verify_count_searchresult() {
		List<MobileElement> list=driver.findElements(By.id("com.yatra.base:id/ll_lay_top_view"));
		int count=list.size();
		System.out.println("The respective search result count:-"+count);
	}


}
