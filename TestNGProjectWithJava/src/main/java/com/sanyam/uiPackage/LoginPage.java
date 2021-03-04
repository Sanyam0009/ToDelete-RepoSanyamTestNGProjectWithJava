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
	@FindBy(how=How.LINK_TEXT , using="Sign in")
	@CacheLookup
	WebElement SignIN;
	
	@FindBy(how=How.ID , using="email")
	@CacheLookup
	WebElement userName;
	
	@FindBy(how=How.ID , using="passwd")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.ID , using="SubmitLogin")
	@CacheLookup
	WebElement login;
	
	
	public void login(String uname,String Password){
	System.out.println("In Login Method of LoginPage class");
	if (SignIN !=null){
		SignIN.click();
	}
	else
	{
		//code to report error.
	}
	userName.sendKeys(uname);
	password.sendKeys(Password);
	login.click();
	
	}
	
}
