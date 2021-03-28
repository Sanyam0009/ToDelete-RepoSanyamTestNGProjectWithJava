package com.sanyam.uipackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.sanyam.frameworkpackage.BrowserFactory;

public class ContactPage extends BrowserFactory {
	WebDriver driver;
	public ContactPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
