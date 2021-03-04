package com.sanyam.frameworkpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static WebDriver driver;
	
		public static WebDriver getDriver(String browserName){
		if (browserName.equalsIgnoreCase("chrome")){
			System.out.println("Opening Chrome Browser");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); 
			driver.get("http://automationpractice.com/index.php");
		}
		else if (browserName.toLowerCase().equals("firefox")){
			System.out.println("Opening firefox Browser");
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://automationpractice.com/index.php");
		}
		return driver;
	}
}
