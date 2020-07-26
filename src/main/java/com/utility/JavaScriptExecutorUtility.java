package com.utility;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorUtility extends TestUtility{
		
	public static WebDriver driver;
	
	public JavaScriptExecutorUtility() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static JavascriptExecutor js = ((JavascriptExecutor) driver);
	
	public static void flash(WebElement element, WebDriver driver) {
       String bgcolor  = element.getCssValue("backgroundColor");
        for (int i = 0; i <  10; i++) {
            changeColor("rgb(0,200,0)", element,driver);//1
            changeColor(bgcolor, element,driver);//2
        }
    }
	
    public static void changeColor(String color, WebElement element, WebDriver driver) {
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

        try {
            Thread.sleep(20);
        }  catch (InterruptedException e) {
        }
     }
	
    public static void drawBorder(WebElement element, WebDriver driver){
    	js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    public static void generateAlert(WebDriver driver, String message){
    	js.executeScript("alert('"+message+"')");

    }
    
    public static void clickElement(WebElement element, WebDriver driver){
    	js.executeScript("arguments[0].click();", element);
    	
    }

}
