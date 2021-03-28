package com.sanyam.testproject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.sanyam.frameworkpackage.BrowserFactory;

public class HelperClass {
	public  WebDriver driver;
	HelperClass(){
		
	}
	@BeforeClass
	public void initialization(){
		//BrowserFactory bf= new BrowserFactory();
		System.out.println("Inside Helper Class");
		BrowserFactory bf = new BrowserFactory();
		driver = bf.getDriver();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		
	}
}
