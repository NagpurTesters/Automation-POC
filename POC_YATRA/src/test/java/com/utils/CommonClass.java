package com.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalTime;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.gherkin.model.Scenario;

public class CommonClass{
	// String Scenario_Name;
	LocalTime time = LocalTime.now();
	public CommonClass() {
	}

	@AfterClass
	public void afterhook(Scenario scn) throws WebDriverException, IOException {
		try {
			DriverFactory.closeDeviceDriver(scn);
		} catch (Exception e) {
			System.out.println(scn.toString());
		}
	}

	@BeforeClass()
	public void beforehook(Scenario scn) throws MalformedURLException, InterruptedException {
		try {
			DriverFactory.closeDeviceDriver(scn);
			// Scenario_Name = scn.getName();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		DriverFactory.createAndGetDeviceDriver(scn.toString());
		System.out.println("Starting scenario "+scn.toString()+" at "+time);

		//log("Starting scenario "+scn.getName());
		System.out.println("I am in before hook");
		//log("I am in before hook");
	}
}
