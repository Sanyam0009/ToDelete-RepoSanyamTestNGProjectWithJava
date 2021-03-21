package com.sanyam.testproject;

import org.openqa.selenium.support.PageFactory;

import com.sanyam.frameworkpackage.BrowserFactory;

public class ContactPage extends BrowserFactory {

	public ContactPage(){
		PageFactory.initElements(driver, this);
	}
}
