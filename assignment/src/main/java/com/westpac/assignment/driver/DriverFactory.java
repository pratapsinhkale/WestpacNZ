package com.westpac.assignment.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	private DriverFactory() {

	}

	/**
	 * This static method is used for calling getDriver methods of corresponding
	 * browser types by validating browser type. Argument to this method is name
	 * with type String. This contains the name of the browser for which we need
	 * to retrieve the driver. This method will return the corresponding
	 * webdriver retrieved from getDriver methods of different browser types. If
	 * browser name validation is unsuccessful then null WebDriver is returned.
	 */
	public static WebDriver getDriver(String name) {
		if (name.equalsIgnoreCase("chrome")) {
			return DriverChrome.getDriver();
		} else if (name.equalsIgnoreCase("iexplorer")) {
			return DriverIE.getDriver();
		} else {
			return null;
		}
	}
}
