package com.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.InitializationException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class DriverFactory {

	static String Scenario_Name;
	public static HashMap<String, String> sauceMapper = new HashMap<String, String>();
	public static AndroidDriver<MobileElement> driver;
	public static HashMap<String, AndroidDriver> scnDriverMapper = new HashMap<String, AndroidDriver>();
	
	/*private final static String DEFAULT_BROWSERTYPE = "FIREFOX";
	private final static String DEFAULT_BROWSERVERSION = "";*/
	private final static String DEFAULT_BROWSERENV = "saucelab";
	private final static String DEFAULT_SAUCE_USER = "Satishkamble11";//"reysh_c";//
	private final static String DEFAULT_SAUCE_ACCESSKEY = "b2396076-18c1-41ec-94ab-ab98069975cd";
	
	
	@SuppressWarnings("rawtypes")
	public static AndroidDriver createAndGetDeviceDriver(String snrName) {
		if (driver != null) {
			return driver;
		}
		// Retrieve desired browser configuration from system properties
		String BrowserVersion = System.getProperty("BrowserVersion");
		String platform = System.getProperty("platform");
		String BrowserType = System.getProperty("BrowserType");
		//String WinPlatform=System.getProperty("WinPlatform");
		String testEnv = System.getProperty("BrowserEnv");
		String sauceUsername = System.getenv("SAUCE_USERNAME");
		String saucePassword = System.getenv("SAUCE_ACCESS_KEY");

		
		if (testEnv.equals("saucelab") && (sauceUsername == null || saucePassword == null)) {
			sauceUsername = DEFAULT_SAUCE_USER;
			saucePassword = DEFAULT_SAUCE_ACCESSKEY;
		}
		
/*		if (testEnv.equalsIgnoreCase("saucelab")) {
			DesiredCapabilities capabilities = null;
			if (BrowserType.equalsIgnoreCase("IE")) {
				capabilities = DesiredCapabilities.internetExplorer();
			} else if (BrowserType.equalsIgnoreCase("CHROME")) {
				capabilities = DesiredCapabilities.chrome();
			} else if (BrowserType.equalsIgnoreCase("FIREFOX")) {
				FirefoxProfile ffprofile = new FirefoxProfile();
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
			} else if (BrowserType.equalsIgnoreCase("SAFARI")) {
				capabilities = DesiredCapabilities.safari();
			} else {
				throw new IllegalArgumentException("Unsupported Platform/Browser Configuration " + BrowserType);
			}*/
			//System.out.println(saucePassword+" <--:In driver Factory class :-->"+sauceUsername);
			String URL = "https://" + sauceUsername + ":" + saucePassword + "@ondemand.saucelabs.com:443/wd/hub";
			
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6000");
			cap.setCapability(MobileCapabilityType.UDID, "ee24a865");
			cap.setCapability("appPackage","com.yatra.base");
			cap.setCapability("appActivity","com.yatra.flights.activity.FlightBookingActivity");
			try {
				driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			
			/*
			capabilities.setCapability("platform", "Windows 7");
			capabilities.setCapability("version", BrowserVersion);
			capabilities.setCapability("maxDuration", 10800);
			capabilities.setCapability("avoidProxy", true);
			capabilities.setCapability("autoAcceptAlerts", true);
//			capabilities.setCapability("parent-tunnel", "sauce_admin");
//			capabilities.setCapability("tunnelIdentifier", "OptumSharedTunnel-Prd");
			capabilities.setCapability("recordVideo", true);
			capabilities.setCapability("recordScreenshots", true);
			capabilities.setCapability("idleTimeout", 500);
			capabilities.setCapability("commandTimeout", 600);
			 capabilities.setCapability("screenResolution", "1280x1024");
*/
		/*	if (System.getenv("JOB_NAME") == null)
				capabilities.setCapability("build", "SalesForce_Automation");
			else
				capabilities.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));

			if (!(snrName.isEmpty() || snrName == "")) {
				capabilities.setCapability("name", snrName);
			}*/

			try {
				setDriver(new AndroidDriver(new URL(URL), cap));
				((AndroidDriver) driver).setFileDetector(new LocalFileDetector());	//Added to upload the file as attachment for PA on SauceLabs VM
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println(e);
			}

			if (!(snrName.isEmpty() || snrName == "")) {
				Scenario_Name = snrName;
				sauceMapper.put(snrName, "https://saucelabs.com/beta/tests/"
						+ (((RemoteWebDriver) DriverFactory.getDeviceDriver()).getSessionId()).toString());
				scnDriverMapper.put(snrName, driver);
			}

			((AndroidDriver) driver).manage().deleteAllCookies();
			
			return getDeviceDriver();
			
		} /*else {
			// Instantiate local browser
			// WARNING: Selenium WebDriver version used by this framework only supports
			// versions older than 47. Recommend installing version 45 in a non-default
			// directory, then using the webdriver.firefox.bin property to specify
			// the location of that executable

			if (BrowserType.equalsIgnoreCase("FIREFOX")) {
				// System.setProperty("webdriver.firefox.bin", "C:\\FireFox45\\firefox.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

				setDriver(new FirefoxDriver(capabilities));
			} else if (BrowserType.equalsIgnoreCase("CHROME")) {
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				options.setExperimentalOption("useAutomationExtension", false);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.chrome.drive", "chromedriver.exe");

				setDriver(new ChromeDriver(capabilities));

			} else {

				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				// cap = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");

				setDriver(new InternetExplorerDriver(caps));
			}
			if (!(snrName.isEmpty() || snrName == "")) {
				scnDriverMapper.put(snrName, driver);
			}
		}

		// Initialize threadLocal instances of wait objects with new driver instance
		Selenium_Utils.smallWait.set(new WebDriverWait(getDeviceDriver(), Selenium_Utils.SMALL_WAIT_TIME));
		Selenium_Utils.mediumWait.set(new WebDriverWait(getDeviceDriver(), Selenium_Utils.MEDIUM_WAIT_TIME));
		Selenium_Utils.longWait.set(new WebDriverWait(getDeviceDriver(), Selenium_Utils.LONG_WAIT_TIME));

		return getDeviceDriver();
	}*/

	public static AndroidDriver getDeviceDriver() throws InitializationException {
		if (driver != null) {
			return driver;
		}

		throw new InitializationException("Browser Driver Not Initialized");
	}

	
	private static AndroidDriver setDriver(AndroidDriver inputDriver) {
		return driver;
	}

	/**
	 * Closes driver for current thread, sets current thread driver to null.
	 * 
	 * @return true if driver shut down, otherwise false.
	 */
	public static byte[] closeDeviceDriver(Scenario scn) {
		byte[] imgholder = null;
		AndroidDriver currentDriver = (AndroidDriver) (scnDriverMapper.get((scn.toString())));
		if (currentDriver != null) {
			if (System.getProperty("BrowserEnv").toString().toLowerCase().equalsIgnoreCase("saucelab")) {
//				scn.write("Watch at Sauce:" + sauceMapper.get(scn.toString()));
				System.out.println(scn.toString());;
			}
			imgholder = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
			setDriver(null);
			currentDriver.quit();
		}
		return imgholder;
	}

}

