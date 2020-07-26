package com.utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

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
			//	System.setProperty("webdriver.chrome.driver",
			//	System.getProperty("user.dir") + "/src/main/java/com/drivers/chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				log("Browser Selected : Firefox");
			//	System.setProperty("webdriver.gecko.driver",
			//	System.getProperty("user.dir") + "/src/main/java/com/drivers/geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			log("Operating System : Mac");
			if (browser.equals("chrome")) {
				log("Browser Selected : Chrome");
			//	System.setProperty("webdriver.chrome.driver",
			//	System.getProperty("user.dir") + "/src/main/java/com/drivers/chromedriver");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				log("Browser Selected : Firefox");
				//System.setProperty("webdriver.firefox.marionette", "/Users/maddy/Downloads/geckodriver");
				WebDriverManager.firefoxdriver().setup();
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


	public static void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	
}
