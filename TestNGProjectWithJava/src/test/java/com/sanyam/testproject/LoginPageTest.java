package com.sanyam.testproject;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sanyam.frameworkpackage.BrowserFactory;
import com.sanyam.frameworkpackage.ExtentManager;
import com.sanyam.frameworkpackage.ReadExcelFile;
import com.sanyam.frameworkpackage.TestListners;
import com.sanyam.uipackage.LoginPage;

public class LoginPageTest {
	// method>> Class >> test >> Suite
	public HashMap<String, String> datamap;
	public WebDriver driver;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {
		// ReadExcelFile excelFactory = new ReadExcelFile(); // made the method
		// "testDataCollector" static so no need to create an obj as can be
		// accessed by class name
		BrowserFactory bf = new BrowserFactory();
		driver = bf.getDriver();
		context.setAttribute(this.getClass().getSimpleName(), driver);
		datamap = ReadExcelFile.testDataCollector("TC_01");

	}

	@Test(priority = 0)
	public void loginFreeCRM() {
		System.out.println("Inside loginFreeCRM Test");
		String email_Id = datamap.get("Email_Id");
		String uname = datamap.get("UserName");
		String Pwd = datamap.get("Password");
		// RegisterUser RegisterUserObj = PageFactory.initElements(driver,
		// RegisterUser.class//(POM class name));
		// or
		// RegisterUser RegisterUserObj = new RegisterUser(driver); // In this
		// case we will have to keep PageFactory.initElement(driver, this); in
		// constructor
		LoginPage LoginPageObject = new LoginPage(driver);
		LoginPageObject.login(uname, Pwd);
	}

	@Test(priority = 1)
	public void testSuccess() {
		System.out.println("Inside testSuccess Test");
	}

	@Test(priority = 2)
	public void testCase2() {
		System.out.println("Inside testCase2");
		SoftAssert softAssert = new SoftAssert();
		TestListners.customOnTestFailure(driver, "System should display Sanyam", "System is Displaying Sakshi",
				"Test to take custome SC");
		String path2 = ExtentManager.getScreenshot(driver, "Fail2");
		softAssert.fail("Test Fail 1" + "-" + path2); // path2 Just to check if
														// we can pass path or
														// not

		softAssert.fail("Test Fail 2");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void testCase3() {
		System.out.println("Inside testCase3 Test");
		Assert.fail("Failed to check Failure Scenario");
	}

	@Test(priority = 4)
	public void testSkip() {
		System.out.println("Inside testSkip Test");
		throw new SkipException("Testig Skip test");
	}

	@AfterClass
	public void teardown() {
		System.out.println("Closing Browser for Login Test");
		driver.quit();
	}

}
