package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[text()='Login']")
	WebElement btnLogin;

	@FindBy(xpath = "//*[@class='logo-top']")
	WebElement zohoLogo;

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	// This Method will return the Boolean Value for the Logo
	public boolean validateLogo() {
		// JavaScriptExecutorUtility.flash(freecrmLogo, driver);
		return zohoLogo.isDisplayed();
	}

	public LoginPage clickLoginBtn() {
		log("Clicking on the Login Button");
		btnLogin.click();
		log("Login Button Clicked");
		return new LoginPage();

	}

}
