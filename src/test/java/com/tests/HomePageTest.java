package com.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import com.base.TestBase;
import com.pages.HomePage;

public class HomePageTest extends TestBase {

 
	
	HomePage objHomePage;
	public HomePageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Test
	public void logoTest() {
		
		objHomePage.validateLogo();
		
		
	}
	
	//@Test
	public void loginTest() throws Exception
	{
		objHomePage=new HomePage();
		objHomePage.clickLoginBtn();
		
	}
	
	
}
