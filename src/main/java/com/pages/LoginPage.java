package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;
import com.utility.TestUtility;


public class LoginPage extends TestBase {

	@FindBy(name="lid")
	WebElement username;
	
	@FindBy(name="pwd")
	WebElement password;
	
	@FindBy(xpath="signin_submit")
	WebElement loginButton;
	
	
	
	
	
	//Constructor of the Class
	public LoginPage() {	
		PageFactory.initElements(driver, this); // or we can write PageFactory.initElements(driver, LoginPage.class);
	}
	
	//This Method will Return the Title of Web Page
	public String validateTitle() {
		return driver.getTitle();	
	}
	
	
	
	
	//This Method will perform the Login Action
	public HomePage login(String uname,String pass) {
			
		log("UserName Entered "+uname);
		
		log("Password Entered "+pass);
		return new HomePage();
	}
	
	
}
