package com.sanyam.testproject;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sanyam.frameworkpackage.BrowserFactory;
import com.sanyam.frameworkpackage.ReadExcelFile;
import com.sanyam.uipackage.HomePage;
import com.sanyam.uipackage.LoginPage;

public class HomePageTest{
	HashMap<String, String> datamap;
	public WebDriver driver;
	HomePage homePage;
	@BeforeClass
	public void Setup(ITestContext context) {
		BrowserFactory bf = new BrowserFactory();
		driver = bf.getDriver();
		context.setAttribute(this.getClass().getSimpleName(), driver);
		datamap = ReadExcelFile.testDataCollector("TC_01");
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.login(datamap.get("UserName"), datamap.get("Password"));
/*		or if we do not want to close previous browser by login page and not want to login
		again here then we can directly go for home page object like below line and then line
		No 21,23 and 24 will not be required	*/
		// homePage = new HomePage();
	}

	@Test(priority=0)
	public void veriftLoggedInUSerTest() {
		System.out.println("Inside veriftLoggedInUSerTest Test");
		homePage.userLoggedIn();
	}
	
	@Test(priority=1)
	public void contactsButtonTest(){
		System.out.println("Inside contactsButtonTest Test");
		homePage.clickOnContactsButton();
	}

	@AfterClass
	public void teardown() {
		System.out.println("Closing Browser for HomePageTest");
		driver.quit();
	}
}
