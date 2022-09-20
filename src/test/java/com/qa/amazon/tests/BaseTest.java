package com.qa.amazon.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.amazon.factory.DriverFactory;
import com.qa.amazon.pages.AccountsPage;
import com.qa.amazon.pages.CartPage;
import com.qa.amazon.pages.CheckoutConfirmationPage;
import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.pages.OrderSuccesPage;
import com.qa.amazon.pages.ProductInfoPage;
import com.qa.amazon.pages.RegistrationPage;
import com.qa.amazon.pages.SearchResultsPage;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public RegistrationPage registrationPage;
	public SoftAssert softAssert;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public SearchResultsPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public CartPage cartPage;
	public CheckoutConfirmationPage checkoutConfirmationPage;
	public OrderSuccesPage orderSuccesPage;
	
	@BeforeTest
	public void setup() 
	{
		df = new DriverFactory();
		prop = df.init_prop();
		
		driver = df.init_driver(prop);
		softAssert = new SoftAssert();
		loginPage = new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
	  driver.quit();
	}
	
	
	

}
