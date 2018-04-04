package com.westpac.assignment.uiobjects;

import java.util.HashMap;

import org.openqa.selenium.By;

public class PageObjects {

	private HashMap<String, By> locatorsMap = null;
	private static PageObjects page = null;

	/**
	 * Constructor for the class. In this constructor, Object of Hashmap class
	 * in created for storing the name and the locators of the webelement on the
	 * web page In the hashmap, key is the name of the element with type as a
	 * String and value is the locators with type By. If webpage gets
	 * redeveloped then we can add or amend its locators here.
	 */
	private PageObjects() {

		locatorsMap = new HashMap<String, By>();
		locatorsMap.put("KiwiSaver", By.id("ubermenu-section-link-kiwisaver-ps"));
		locatorsMap.put("KiwiSaver calculators", By.id("ubermenu-item-cta-kiwisaver-calculators-ps"));
		locatorsMap.put("Click here to get started.", By.linkText("Click here to get started."));
		locatorsMap.put("View your KiwiSaver retirement projections",
				By.xpath("//span[contains(text(),'View your KiwiSaver retirement projections')]/ancestor::button"));
	}

	/**
	 * This method is used for returning the locators stored in hashmap. This
	 * method will take String argument which contains name of the element for
	 * which locators are to be returned. return type of this method is By
	 */
	public By getLocator(String name) {
		return locatorsMap.get(name);
	}

	/**
	 * This static method is used for create an instance of class. return type
	 * of this method is object of PageObjects class.
	 */
	public static PageObjects getInstance() {
		if (page == null) {
			page = new PageObjects();
		}
		return page;
	}
}
