package com.westpac.assignment.stepslibrary;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.westpac.assignment.driver.DriverFactory;
import com.westpac.assignment.uiobjects.PageObjects;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class BusinessSteps {

	private static WebDriver driver = null;
	private static PageObjects page = null;
	private static Properties properties = null;

	/**
	 * Constructor for the class. In this constructor, Instance of PageObjects
	 * class is retrieved using its getIstance method. property file is loaded
	 * in this constructor.
	 */
	public BusinessSteps() {
		page = PageObjects.getInstance();
		properties = new Properties();
		try {
			properties.load(new FileReader("./config/configuration.properties"));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	/**
	 * This method is used for launching the desired browser. Argument to this
	 * method is name of the browser of type String. This method will call
	 * another method getData to check if the property file contains value of
	 * the browser name. If property file contains value of browser name then it
	 * will be overwritten. This method will also set implicit wait for the test
	 * script.
	 */
	@Given("^user launches browser \"(.*)\"$")
	public void openBrowser(String browser) {
		browser = getData(browser);
		driver = DriverFactory.getDriver(browser);
		if (driver != null) {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			fail("Invalid Browser");
		}
	}

	/**
	 * This method is used for navigating to desired URL. Argument to this
	 * method is URL of type String. This method will call another method
	 * getData to check if the property file contains value of the URL. If
	 * property file contains value of URL then it will be overwritten.
	 */
	@And("^user navigates to url \"(.*)\"$")
	public void navigateToUrl(String url) {
		url = getData(url);
		driver.get(url);
	}

	/**
	 * This method is used for mouse hovering on any element. Argument to this
	 * method is name of the element on which hovering of the mouse event need
	 * to be performed. This method will call another method getData to check if
	 * the property file contains name of the element. If property file contains
	 * name of the element then it will be overwritten. This method will get
	 * locator of element on which hovering of the mouse event needs to be
	 * performed.
	 */
	@And("^user hovers on menu \"(.*)\"$")
	public void hoverOnMenu(String menu) {
		menu = getData(menu);
		By locator = page.getLocator(menu);
		WebElement element = driver.findElements(locator).get(0);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * This method is used for clicking on the element which is passed in the
	 * argument. This method will call another method getData to check if the
	 * property file contains name of the element. If property file contains
	 * name of the element then name will be overwritten. This method will first
	 * check if there is any frame present on webpage by calling another method.
	 * If frame is present on the webpage then control will be switched to frame
	 * and element is clicked. Once element is clicked after scrolling to it
	 * ,controlled is passed back to parent frame,
	 */
	@And("^user clicks on the button \"(.*)\"$")
	public void clickButton(String name) {
		name = getData(name);
		By locator = page.getLocator(name);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element.get(0));
		element.get(0).click();
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is used for validating help icon presence on KiwiSaver
	 * Retirement Calculator. This method will call another method getData to
	 * check if the property file contains name of the icon. If property file
	 * contains name of the icon then name will be overwritten. This method will
	 * first check if there is any frame present on webpage by calling another
	 * method. If frame is present on the webpage then control will be switched
	 * to frame.
	 */
	@And("^user validates information icon for \"(.*)\" with info text \"(.*)\"$")
	public void validateInfoIcon(String icon, String infoText) {
		icon = getData(icon);
		String path = "//label[contains(text(),'" + icon
				+ "')]/ancestor::div[@class='field-row']//button[contains(@class,'icon-target')]";
		By locator = By.xpath(path);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}
		if (element == null) {
			fail("Help icon not present");
		} else {
			element.get(0).click();
			String iconMsg = "//label[contains(text(),'" + icon
					+ "')]/ancestor::div[@class='field-row']//div[contains(@class,'field-message')]";
			String message = driver.findElements(By.xpath(iconMsg)).get(0).getText();
			System.out.println(message);
			Assert.assertEquals(getData(infoText), message);
		}
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is used for entering value against web element. Arguments to
	 * this method are name of the element and value to be passed. This method
	 * will call another method getData to check if the property file contains
	 * name of the element and value to be passed. If property file contains
	 * name of the element and value to be passed then it will be overwritten.
	 * If frame is present on the webpage then control will be switched to
	 * frame.
	 */
	@And("^user enters value \"(.*)\" into \"(.*)\"$")
	public void enterText(String value, String name) {
		value = getData(value);
		name = getData(name);
		String path = "//label[contains(text(),'" + name + "')]/ancestor::div[@class='field-row']//input[@type='text']";
		By locator = By.xpath(path);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}
		element.get(0).sendKeys(value);
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is used for selecting the dropdown values. Arguments to this
	 * method are name of the dropdown element and value to be selected. This
	 * method will call another method getData to check if the property file
	 * contains name of the dropdown element and value to be selected. If
	 * property file contains name of the dropdown element and value to be
	 * selected then it will be overwritten. If frame is present on the webpage
	 * then control will be switched to frame on which element is present.
	 */
	@And("^user selects value \"(.*)\" from dropdown \"(.*)\"$")
	public void selectFromDropDown(String value, String name) {
		value = getData(value);
		name = getData(name);
		String path = "//label[contains(text(),'" + name
				+ "')]/ancestor::div[@class='field-row']//div[contains(@class,'well-value')]";
		By locator = By.xpath(path);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}
		element.get(0).click();
		String option = "//label[contains(text(),'" + name
				+ "')]/ancestor::div[@class='field-row']//div[@class='dropdown']//span[contains(text(),'" + value
				+ "')]";
		By locatorOption = By.xpath(option);
		WebElement elementOption = driver.findElements(locatorOption).get(0);
		elementOption.click();
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is used for selecting radio button options. Arguments to this
	 * method are name of the radio button element and value to be selected.
	 * This method will call another method getData to check if the property
	 * file contains name of the name of the radio button element and value to
	 * be selected. If property file contains name of the radio button element
	 * and value to be selected then it will be overwritten. If frame is present
	 * on the webpage then control will be switched to frame on which element is
	 * present.
	 */
	@And("^user select radio option \"(.*)\" for \"(.*)\"$")
	public void selectRadioButton(String option, String name) {
		option = getData(option);
		name = getData(name);
		String path = "//label[contains(text(),'" + name + "')]/ancestor::div[@class='field-row']//span[text()='"
				+ option + "']//ancestor::label[@class='well-label']//input[@type='radio']";

		By locator = By.xpath(path);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}
		element.get(0).click();
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is to verify and display projected amount after retirement If
	 * frame is present on the web page then control will be switched to the
	 * frame on which element is present.
	 */
	@Then("^user can see the projected amount at retirement$")
	public void checkProjectedAmount() {
		String path = "//span[contains(@class,'result-value')]";
		By locator = By.xpath(path);
		List<WebElement> element = driver.findElements(locator);
		WebElement iframe = null;
		if (element.size() == 0) {
			iframe = waitForFrameLoad(driver, 20);
			if (iframe != null) {
				driver.switchTo().frame(0);
				System.out.println("switched to frame");
			}
			element = driver.findElements(locator);
		}
		System.out.println("----------------------------------------------");
		System.out.println("Projected amount at retirement is : " + element.get(0).getText().replace("\n", ""));
		System.out.println("----------------------------------------------");
		if (iframe != null) {
			driver.switchTo().defaultContent();
			System.out.println("switched back to parent frame");
		}
	}

	/**
	 * This method is used to check if the frame is present on web page.
	 * Arguments to this method are driver and the explicit wait timeout unit in
	 * seconds. It will return frame element if frame is found on the webpage
	 * else null element is returned.
	 */
	private WebElement waitForFrameLoad(WebDriver driver, int sec) {
		WebDriverWait dynamicElement = new WebDriverWait(driver, sec);
		WebElement element = dynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
		return element;
	}

	/**
	 * This method is used to property value from the property file. Argument to
	 * this method is name of the property for which value to be returned. If
	 * property value is present the it will be return else value which is
	 * passed in the argument is returned.
	 */
	private String getData(String key) {
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		} else {
			return key;
		}
	}
}
