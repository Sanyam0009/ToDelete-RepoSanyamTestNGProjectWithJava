package com.sanyam.SeleniumProject;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.sanyam.frameWorkPackage.BrowserFactory;
import com.sanyam.frameWorkPackage.ExtentManager;
import com.sanyam.frameWorkPackage.ReadExcelFile;
import com.sanyam.frameWorkPackage.TestListners;
import com.sanyam.uiPackage.LoginPage;
import com.sanyam.uiPackage.RegisterUser;

public class TestDemo {
	// method
	// Class
	// test
	// Suite
	public WebDriver driver = null;
	public HashMap<String, String> datamap;

	@BeforeTest
	public void openBrowserUrl() {
		this.driver = BrowserFactory.getDriver("firefox");

	}

	@BeforeTest
	public void initAll() throws IOException {
		// ReadExcelFile excelFactory = new ReadExcelFile(); // made the method
		// "testDataCollector" static so no need to create an obj as can be
		// accessed by class name
		datamap = ReadExcelFile.testDataCollector("TC_01");
	}

	@Test
	public void loginMercury() {
		String email_Id = datamap.get("Email_Id");
		String uname = datamap.get("UserName");
		String Pwd = datamap.get("Password");

		System.out.println("Excel data" + uname + " " + Pwd + " " + email_Id);
		System.out.println("Register and Login");
		RegisterUser RegisterUserObj = PageFactory.initElements(driver, RegisterUser.class);
		// RegisterUser RegisterUserObj = new RegisterUser(driver); // In this
		// case we will have to keep PageFactory.initElement in constructor
		LoginPage LoginPageObject = RegisterUserObj.register(email_Id);
		LoginPageObject.login(uname, Pwd);
	}

	@Test
	public void testSuccess() {

		System.out.println("Inside Pass Report");
	}
	 
	@Test
	public void testCase2() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("Inside TC 2");
		TestListners.customOnTestFailure(driver,"System should display Sanyam","System is Displaying Sakshi","Test to take custome SC");
		String path2 = ExtentManager.getScreenshot(driver, "Fail2");
		softAssert.fail("Test Fail 2"  + "-" + path2);
		
		softAssert.fail("Test Fail 3" );
		softAssert.assertAll();
		
	}

	/*	@Test
	public void testCase3() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("Inside TC 3");
		softAssert.fail("Test Fail 1");
		softAssert.fail("Test Fail 2" );
		softAssert.fail("Test Fail 3" );
		softAssert.assertAll();
		System.out.println("After Fail");
	}*/

	@Test
	public void testSkip() {
		System.out.println("Inside Skip Report");
		throw new SkipException("Testig Skip test");
	}

	@AfterTest
	public void aftertest() {
		System.out.println("Closing Browser");
		// driver.close();
	}

}
