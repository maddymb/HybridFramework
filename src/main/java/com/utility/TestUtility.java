package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.base.TestBase;
import com.constants.Constants;
import com.listeners.Listeners;

public class TestUtility extends TestBase {

	public TestUtility() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	WebElement element;
	public static EventFiringWebDriver e_driver;
	public static Listeners eventListener;

	public static void selectBrowser(String browser) throws IOException {
		if (System.getProperty("os.name").contains("Window")) {
			log("Operating System : Windows");
			if (browser.equals("chrome")) {
				log("Browser Selected : Chrome");
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/java/com/drivers/chromedriver.exe");
				driver = new ChromeDriver();

			} else if (browser.equals("firefox")) {
				log("Browser Selected : Firefox");
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "/src/main/java/com/drivers/geckodriver.exe");
				driver = new FirefoxDriver();

			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			log("Operating System : Mac");
			if (browser.equals("chrome")) {
				log("Browser Selected : Chrome");
				System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/java/com/drivers/chromedriver");
				driver = new ChromeDriver();

			} else if (browser.equals("firefox")) {
				log("Browser Selected : Firefox");
				System.out.println(System.getProperty("user.dir"));
				// System.setProperty("webdriver.firefox.marionette",
				// System.getProperty("user.dir") + "/src/main/java/com/drivers/geckodriver");
				System.setProperty("webdriver.firefox.marionette", "/Users/maddy/Downloads/geckodriver");

				driver = new FirefoxDriver();

			}
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new Listeners();
		e_driver.register(eventListener);
		driver = e_driver;
	}

	public static void getUrl(String url) {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMLPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
		log("Navigated To Url " + url);

	}

	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			Log.error("Class Utils | Method getTestCaseName | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static String getProperty(String strVal) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/java/com/freecrm/config/config.properties";
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(path);
		prop.load(fs);
		System.out.println(prop.getProperty(strVal));
		return prop.getProperty(strVal);
	}

	public static void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public static void sendKeys(WebElement element, String keys) {

		element.clear();
		element.sendKeys(keys);

	}

	public static String getText(WebDriver driver, String attributeLocator, String locator) {
		String getText = "";

		switch (attributeLocator) {
		case "id":
			try {
				if (isElementExist(driver, locator)) {
					getText = driver.findElement(By.id(locator)).getText().trim();
					// System.out.println("Text Value of Object :-" +value);
				}

			} catch (Throwable t) {
				System.out.println("The Exception for getText is : " + t.getMessage());
				// Utility.logging("The Exception for getText is : " + t.getMessage());
				// ReportEvent.testStepReport("", "fail", "The Exception for getText is : " +
				// t.getMessage());
				// softAssert.fail("The Exception for getText is : " + t.getMessage());
			}
		}
		return getText;

	}

	public static boolean isElementExist(WebDriver driver, String xpathElement) {

		boolean isElementExist = false;
		if (driver.findElements(By.xpath(xpathElement)).size() > 0) {

			System.out.println("Element is Present");
			// Utility.fnLogging("Element is Present : "+xpathElement);
			isElementExist = true;
		} else {
			System.out.println("Element is Absent");
			// Utility.fnLogging("Element is Absent : "+xpathElement);
			isElementExist = false;
		}
		return isElementExist;
	}

	public static boolean sendKeys(WebDriver driver, String attributeLocator, String locator, String valueToEnter) {

		boolean sendKeys = false;

		switch (attributeLocator) {
		case "id":
			try {
				if (isElementExist(driver, locator) == true) {
					driver.findElement(By.id(locator)).sendKeys(valueToEnter);
					// driver.findElement(By.xpath(locator)).sendKeys(valueToEnter);
					System.out.println("Value entered into the text box :-" + valueToEnter);
					sendKeys = true;
				}

			} catch (Throwable t) {
				System.out.println("The Exception for sendKeys is : " + t.getMessage());
				// Utility.logging("The Exception for sendKeys is : " + t.getMessage());
				// ReportEvent.testStepReport("", "fail", "The Exception for sendKeys is : " +
				// t.getMessage());
				// softAssert.fail("The Exception for sendKeys is : " + t.getMessage());
				sendKeys = false;
			}
		}
		return sendKeys;

	}

	public static boolean click(WebDriver driver, String attributeLocator, String locator) {
		boolean click = false;
		switch (attributeLocator) {
		case "id":
			try {
				if (isElementExist(driver, locator) == true) {
					driver.findElement(By.id(locator)).click();
					click = true;
				}
			} catch (Throwable t) {
				System.out.println("The Exception for click is : " + t.getMessage());
				// Utility.logging("The Exception for click is : " + t.getMessage());
				// ReportEvent.testStepReport("", "fail", "The Exception for click is : " +
				// t.getMessage());
				// softAssert.fail("The Exception for click is : " + t.getMessage());
				click = false;
			}

		case "class":
			try {
				if (isElementExist(driver, locator) == true) {
					driver.findElement(By.className(locator)).click();
					click = true;
				}
			} catch (Throwable t) {
				System.out.println("The Exception for click is : " + t.getMessage());
				// Utility.logging("The Exception for click is : " + t.getMessage());
				// ReportEvent.testStepReport("", "fail", "The Exception for click is : " +
				// t.getMessage());
				// softAssert.fail("The Exception for click is : " + t.getMessage());
				click = false;
			}

		case "xpath":
			try {
				if (isElementExist(driver, locator) == true) {
					driver.findElement(By.xpath(locator)).click();
					click = true;
				}
			} catch (Throwable t) {
				System.out.println("The Exception for click is : " + t.getMessage());
				// Utility.logging("The Exception for click is : " + t.getMessage());
				// ReportEvent.testStepReport("", "fail", "The Exception for click is : " +
				// t.getMessage());
				// softAssert.fail("The Exception for click is : " + t.getMessage());
				click = false;
			}

		}
		return click;
	}

	public static String fnReadPropFile(String strVal) throws IOException {

		// try{

		String path = System.getProperty("user.dir") + "\\config.properties";
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(path);
		// System.out.println(System.getProperty("user.dir"));
		prop.load(fs);
		System.out.println(prop.getProperty(strVal));
		return prop.getProperty(strVal);
		/*
		 * }catch(Throwable t){ System.out.println(t.getMessage()); } return strVal;
		 */
	}

	// Getting the Test Case name, as it will going to use in so many places
	// The main use is to get the TestCase row from the Test Data Excel sheet

	// sTestCaseName = this.toString();
	// From above method we get long test case name including package and class name
	// etc.
	// The below method will refine your test case name, exactly the name use have
	// used
	// sTestCaseName = Utils.getTestCaseName(this.toString());

	// Start printing the logs and printing the Test Case name
	// Log.startTestCase(sTestCaseName);

	public static void mouseHoverAction(WebElement mainElement, String subElement) {

		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();
		if (subElement.equals("Accessories")) {
			action.moveToElement(driver.findElement(By.linkText("Accessories")));
			Log.info("Accessories link is found under Product Category");
		}
		if (subElement.equals("iMacs")) {
			action.moveToElement(driver.findElement(By.linkText("iMacs")));
			Log.info("iMacs link is found under Product Category");
		}
		if (subElement.equals("iPads")) {
			action.moveToElement(driver.findElement(By.linkText("iPads")));
			Log.info("iPads link is found under Product Category");
		}
		if (subElement.equals("iPhones")) {
			action.moveToElement(driver.findElement(By.linkText("iPhones")));
			Log.info("iPhones link is found under Product Category");
		}
		action.click();
		action.perform();
		Log.info("Click action is performed on the selected Product Type");
	}

	public void waitForPageToLoad(WebElement id) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(id));
	}

	public void waitForElementToDisAppear(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
	}

	public WebElement waitForElement(WebElement arg) {
		waitForPageToLoad(arg);
		WebElement el = arg;
		return el;
	}

	public void setText(By locator, String value) {
		element = driver.findElement(locator);
		element.sendKeys(value);
	}

	public String getText(By locator) {
		element = driver.findElement(locator);
		String value = element.getText();
		return value;
	}

	public void clear(By locator) {
		element = driver.findElement(locator);
		element.clear();
	}

	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

}
