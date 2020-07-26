package com.tests;

import org.testng.annotations.Test;
import java.io.IOException;

import com.base.TestBase;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage  objLoginPage;
	HomePage objHomePage;
	public LoginPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Test
	public void loginTest() throws Exception
	{
		objHomePage= new HomePage();
		objLoginPage=objHomePage.clickLoginBtn();
		objLoginPage.login("madhur", "ankajsbd");
		
		
		
	}
	
	
}
