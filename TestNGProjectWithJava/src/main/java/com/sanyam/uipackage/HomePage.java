package com.sanyam.uipackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.sanyam.frameworkpackage.BrowserFactory;

public class HomePage extends BrowserFactory{
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="user-display")
	WebElement userLogged;
	
	public void userLoggedIn(){
		System.out.println("In HomePageTest");
		Assert.assertEquals(userLogged.getText(), "Sanyam Choudhary","User was not able to login successfully");
	}

}
