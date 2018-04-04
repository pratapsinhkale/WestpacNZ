package com.westpac.assignment.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverChrome {
	private static WebDriver driver = null;

	private DriverChrome() {

	}

	/**
	 * This static method is used for setting up the Chrome driver. This method
	 * will first set the system property for Chrome driver and then initialize
	 * the chrome driver. Method returns the initialized driver if the driver is
	 * not initialized in any earlier steps. If any driver is initialized earlier
	 * then this method will return the same driver.
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
}
