package com.sanyam.uipackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sanyam.frameworkpackage.BrowserFactory;

public class LoginPage extends BrowserFactory{
	public LoginPage(){
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
	
	
	public HomePage login(String uname,String Password){
		System.out.println("In Login Method of LoginPage class");
		userName.sendKeys(uname);
		password.sendKeys(Password);
		login.click();
		return new HomePage();
	}
	
}
