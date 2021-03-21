package com.sanyam.testproject;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sanyam.frameworkpackage.BrowserFactory;
import com.sanyam.frameworkpackage.ReadExcelFile;
import com.sanyam.uipackage.HomePage;
import com.sanyam.uipackage.LoginPage;

public class HomePageTest extends BrowserFactory {
	HashMap<String, String> datamap;
	HomePage homePage;

	@BeforeClass
	public void Setup() {
		getDriver();
		datamap = ReadExcelFile.testDataCollector("TC_01");
		LoginPage loginPage = new LoginPage();
		homePage = loginPage.login(datamap.get("UserName"), datamap.get("Password"));
/*		or if we do not want to close previous browser by login page and not want to login
		again here then we can directly go for home page object like below line and then line
		No 21,23 and 24 will not be required	*/
		// homePage = new HomePage();
	}

	@Test
	public void veriftLoggedInUSerTest() {
		homePage.userLoggedIn();

	}

	@AfterClass
	public void teardown() {
		System.out.println("Closing Browser");
		driver.quit();
	}
}
