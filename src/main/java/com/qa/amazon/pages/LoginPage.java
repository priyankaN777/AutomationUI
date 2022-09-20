package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.amazon.utils.ElementUtil;


import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private  By registerButton = By.linkText("Login or register");
	private  By continueButton = By.xpath("//button[@title=\"Continue\"]");
	private  By loginName = By.id("loginFrm_loginname");
	private  By password = By.id("loginFrm_password");
	private  By loginButton = By.xpath("//button[@title=\"Login\"]");
	String loginName1= "loginFrm_loginname";
	String password1= "loginFrm_password";
	
	@Step("navigating to registeration page.....")
	public  RegistrationPage goToRegisterPage() {
		eleUtil.doClick(continueButton);
		return new RegistrationPage(driver);
	}
	
		
	@Step("Login to portal and navigating to Accounts page.....")
	public  AccountsPage doLogin(String loginNameValue, String passwordValue) {
		eleUtil.doSendKeys(loginName, loginNameValue);
		eleUtil.doSendKeys(password, passwordValue);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public By getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(By registerButton) {
		this.registerButton = registerButton;
	}

	public By getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(By continueButton) {
		this.continueButton = continueButton;
	}

}
