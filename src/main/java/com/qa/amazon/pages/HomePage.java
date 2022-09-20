package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.utils.ElementUtil;

import io.qameta.allure.Step;


public class HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. page constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private  By registerButton = By.linkText("Login or register");
	
	@Step("navigating to registeration page.....")
	public LoginPage doClickLogin()
	{
		eleUtil.doClick(registerButton);
		return new LoginPage(driver);
	}
	
}