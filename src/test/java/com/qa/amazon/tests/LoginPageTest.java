package com.qa.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.utils.Constants;

public class LoginPageTest extends BaseTest {

	@BeforeClass
	public void setupLoginPageTest() {
		 loginPage = new LoginPage(driver);
	}
	
	@Test
	public void loginTest() {
		
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		System.out.println("Actual title :" + accountsPage.getAccountPageTitle());
		System.out.println("Exp title :" + Constants.ACCOUNTS_PAGE_TITLE);
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
	
}
