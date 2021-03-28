package com.sanyam.uipackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sanyam.frameworkpackage.BrowserFactory;
import com.sanyam.testproject.HelperClass;

public class LoginPage{
	private WebDriver driver;
	public LoginPage(WebDriver driver){
		System.out.println("In Login class const before PF");
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("In Login class const After PF");
	}

	
	@FindBy(how=How.XPATH, using="//input[@name='email']")
	WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(how=How.XPATH , using="//div[text()='Login']")
	WebElement login;
	
	
	public HomePage login(String uname,String Password){
		System.out.println("In Login Method of LoginPage class");
		userName.sendKeys(uname);
		password.sendKeys(Password);
		login.click();
		return new HomePage(driver);
	}
	
}
