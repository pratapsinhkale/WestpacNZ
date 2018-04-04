package com.westpac.assignment.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverIE {
	private static WebDriver driver = null;

	private DriverIE() {

	}

	/**
	 * This static method is used for setting up the Internet Explored driver.
	 * This method will first set the system property for IE driver and then
	 * initialize the IE driver Method. Returns the initialized driver if the
	 * driver is not initialized in any earlier steps. If any driver is initialized
	 * earlier then this method will return the same driver
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
}
