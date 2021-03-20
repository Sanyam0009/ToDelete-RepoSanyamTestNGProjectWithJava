package com.sanyam.uipackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver){
		System.out.println("In Login class const before PF");
		PageFactory.initElements(driver, this);
		System.out.println("In Login class const After PF");
	}

	
	@FindBy(how=How.XPATH, using="//input[@name='email']")
	@CacheLookup
	WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.XPATH , using="//div[text()='Login']")
	@CacheLookup
	WebElement login;
	
	
	public void login(String uname,String Password){
	System.out.println("In Login Method of LoginPage class");
	userName.sendKeys(uname);
	password.sendKeys(Password);
	login.click();
	
	}
	
}
