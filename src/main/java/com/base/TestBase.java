package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.TestUtility;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent_report;
	public static ExtentTest extent_test;
	public ITestResult result;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	/*		
	####################################################################################
	##############################
	 # FunctionName : static Block
	 # No of Parameter : 0
	 # Description  : For Generating the extent Report one time for specific one run
	 # Parameters and its data-type : Calendar, Date Format, Extent Reference
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent_report = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/reports/Report_"
				+ formater.format(calendar.getTime()) + ".html", true);
	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : TestBase , Constructor of Class
	 # No of Parameter : 0
	 # Description  : For Loading the properties file on run time
	 # Parameters and its data-type : File
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/config/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : initialization 
	 # No of Parameter : 0
	 # Description  : For Selecting the browser and loading the URL
	 # Parameters and its data-type : Properties File
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	public static void initialization() throws IOException {

		TestUtility.selectBrowser(prop.getProperty("browser"));
		TestUtility.getUrl(prop.getProperty("url"));

	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : getresult 
	 # No of Parameter : 1
	 # Description  : For Getting the result of Test Cases and based on it capturing the Screenshot and attaching it to report
	 # Parameters and its data-type : ITestResult Result
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extent_test.log(LogStatus.PASS, result.getName() + " test Passed");
			String screen = captureScreen(result.getName() + "_Passed");
			extent_test.log(LogStatus.PASS, extent_test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extent_test.log(LogStatus.SKIP,
					result.getName() + " Test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extent_test.log(LogStatus.ERROR, result.getName() + " test Failed " + result.getThrowable());
			String screen = captureScreen(result.getName() + "_Failed");
			extent_test.log(LogStatus.FAIL, extent_test.addScreenCapture(screen));
		} 
	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : captureScreen 
	 # No of Parameter : 1
	 # Description  : For Capturing the ScreenShot based on the file name parameter
	 # Parameters and its data-type : String File Name
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	public String captureScreen(String fileName) {
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/screenshots/";
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

	/*		
	####################################################################################
	##############################
	 # FunctionName : beforeMethod 
	 # No of Parameter : 1
	 # Description  : It will run before any test cases and start the preparation of Extent Report
	 # Parameters and its data-type : Result
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	@BeforeMethod()
	public void beforeMethod(Method result) throws IOException {
		extent_test = extent_report.startTest(result.getName());
		Log.startTestCase(result.getName());
		//log(result.getName() + " test Started");
		initialization();
	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : afterMethod 
	 # No of Parameter : 0
	 # Description  : It will run after the test cases and will close the Driver
	 # Parameters and its data-type : Result
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	@AfterMethod()
	public void afterMethod(ITestResult result) {
		Log.endTestCase(result.getName() +" test ended");
		log(result.getName()+" test Ended");
		getresult(result);
		driver.close();
	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : tearDown 
	 # No of Parameter : 0
	 # Description  : It will run after the class and will quit the driver and flush the report
	 # Parameters and its data-type : Result
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		log("Browser Closed");
		extent_report.endTest(extent_test);
		extent_report.flush();

	}

	/*		
	####################################################################################
	##############################
	 # FunctionName : log 
	 # No of Parameter : 1
	 # Description  : It will the operation perform through out the test cases and print the logs on console , log file and extent report
	 # Parameters and its data-type : Result
	 # Developed on : 03/12/2018
	 # Developed By : Madhur Bharadwaj
	 ####################################################################################
	 #############################	
	*/
	
	public static void log(String data) {
		Log.info(data);
		Reporter.log(data);
		extent_test.log(LogStatus.INFO, data);
	}

}
