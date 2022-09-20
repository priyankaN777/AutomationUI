package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ElementUtil;

public class RegistrationPage  {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("AccountFrm_firstname");
	private By lastName = By.id("AccountFrm_lastname");
	private By email = By.id("AccountFrm_email");
	private By telephone = By.id("AccountFrm_telephone");
	private By fax = By.id("AccountFrm_fax");
	
	private By company = By.id("AccountFrm_company");
	private By address1 = By.id("AccountFrm_address_1");
	private By address2 = By.id("AccountFrm_address_2");
	private By city = By.id("AccountFrm_city");
	private By state = By.id("AccountFrm_zone_id");
	private By zipcode = By.id("AccountFrm_postcode");
	private By country = By.id("AccountFrm_country_id");
	
	private By loginname = By.id("AccountFrm_loginname");
	private By password = By.id("AccountFrm_password");
	private By confirmpassword = By.id("AccountFrm_confirm");
	private By subscribeYes = By.id("AccountFrm_newsletter1");
	private By subscribeNo = By.id("AccountFrm_newsletter0");
	private By agreeCheckBox = By.id("AccountFrm_agree");
	private By continueButton = By.xpath("//button[@class=\"btn btn-orange pull-right lock-on-click\"]");
	private By logoffButton = By.linkText("Logoff");
	private By sucessMessg = By.xpath("//span[@class=\"maintext\"]");
	private  By registerButton = By.linkText("Login or register");
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}

	public boolean accountRegistration(String firstName, String lastName,String email, 
			String telephone, String fax, String company, String address1,String address2, 
			String city, String state, String zipcode, String country,String loginname, 
			String password, String subscribe) {

		
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, String.valueOf(telephone));
		eleUtil.doSendKeys(this.fax, String.valueOf(fax));
		eleUtil.doSendKeys(this.company, company);
		eleUtil.doSendKeys(this.address1, address1);
		eleUtil.doSendKeys(this.address2, address2);
		eleUtil.doSendKeys(this.city, city);
		eleUtil.doSendKeys(this.zipcode, String.valueOf(zipcode));
		eleUtil.doDropDownSelectByVisibleText(this.country, country);
		eleUtil.doDropDownSelectByVisibleText(this.state, state);
		eleUtil.doSendKeys(this.loginname, loginname);
		eleUtil.doSendKeys(this.password, password);	
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if (subscribe.equalsIgnoreCase("Yes"))
		eleUtil.doClick(this.subscribeYes);
		else
		eleUtil.doClick(this.subscribeNo);
		
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String mesg = eleUtil.waitForElementToBeVisible(sucessMessg,90, 1000).getText();
		System.out.println("mesg :"+mesg);
		
		System.out.println("Constants.REGISTER_AUTHENTICATION_MESSG :"+Constants.REGISTER_AUTHENTICATION_MESSG);
		if (mesg.equals(Constants.REGISTER_AUTHENTICATION_MESSG)) {
			doClickLogOff();
			return true;
				
		}
		return false;
		
	}
	
	public LoginPage doClickLogOff()
	{
		eleUtil.doClick(logoffButton);
		eleUtil.doClick(registerButton);
		return new LoginPage(driver);
	}
	
	
	
	
	

}
