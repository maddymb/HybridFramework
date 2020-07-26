/**
 * This class  contains generic function that's specific to Selenium WebDriver and Test set on AUT
 * 
 * @author HCL
 * @since March 2017
 */

package com.utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.TestBase;

public class TestEngine extends TestBase{
			
	public TestEngine() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

				public static void implicitWait(WebDriver driver,int timeUnitSeconds){
						driver.manage().timeouts().implicitlyWait(timeUnitSeconds, TimeUnit.SECONDS);		
				}
				
				public static void maximizeWindow(WebDriver driver){
						driver.manage().window().maximize();
				}
				
				public static String getAppUrl(WebDriver driver){
							return driver.getCurrentUrl();
				}
				
				public static String getPageSource(WebDriver driver){
							return driver.getPageSource();
				}
				
				public static void deleteCookie(WebDriver driver){
							driver.manage().deleteAllCookies();
				}
				
				public static String getTitleOfWebPage(WebDriver driver){
							return driver.getTitle();
				}
				
				public static void navigateBack(WebDriver driver){
							driver.navigate().back();
				}
				
				public static void navigateForward(WebDriver driver){
							driver.navigate().forward();
				}
				
				public static void quitBrowser(WebDriver driver){
							driver.quit();
				}
				
				/*public static void explicitWaitForElementToAppear(WebDriver driver, String xpathOfElement){
                    int nTime =1;                               
                    boolean isExistElement = false;
                    while(nTime<360){                                                                                            
                                   	int driver.findElements(By.xpath(xpathOfElement));
                                                  if(isExistElement == true){
                                                                 nTime = 360;
                                                                 System.out.println("Element Aappeared");
                                                                 Utility.fnLogging("Element Aappeared");
                                                  }
                                                  nTime++;
                    			}
				}*/
				
				public static boolean isElementExist(WebDriver driver, String xpathElement){
					
					boolean isElementExist=false;
						if(driver.findElements(By.xpath(xpathElement)).size()> 0){
								System.out.println("Element is Present");
								//Utility.fnLogging("Element is Present : "+xpathElement);
								isElementExist = true;
						}else{
								System.out.println("Element is Absent");
							//	Utility.fnLogging("Element is Absent : "+xpathElement);
								isElementExist=false;
						}
						return isElementExist;
			}
			
				
				
				
				public static boolean explicitWaitForElementToAppear(WebDriver driver, String attributeLocator, String locator){
					int nTime =1;
					boolean explicitWaitForElementToAppear=false;
					while(nTime<100){
						switch(attributeLocator)	{
						case "id":
							if(driver.findElements(By.id(locator)).size()> 0){
									System.out.println("Element is Present");
								//	Utility.fnLogging("Element is Present : "+locator);
									nTime=360;
									explicitWaitForElementToAppear = true;
								}
						case "class":
							if(driver.findElements(By.className(locator)).size()> 0){
									System.out.println("Element is Present");
								//	Utility.fnLogging("Element is Present : "+locator);
									nTime=360;
									explicitWaitForElementToAppear = true;
								}
							
						case "xpath":
							if(driver.findElements(By.xpath(locator)).size()> 0){
									System.out.println("Element is Present");
								//	Utility.fnLogging("Element is Present : "+locator);
									nTime=360;
									explicitWaitForElementToAppear = true;
								}
							
							}
									
						}
					nTime++;
					return explicitWaitForElementToAppear;
				}

				
			    
			   
			
				
				
}
