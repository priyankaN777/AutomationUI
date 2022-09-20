package com.qa.amazon.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest { 

	@BeforeClass
	public void setupRegistration() {
		 loginPage = new LoginPage(driver);
		 registrationPage = loginPage.goToRegisterPage();
	}
	
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "uiautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	public String getRandomLoginname() {
		Random randomGenerator = new Random();
		String loginname = "automationstore"+randomGenerator.nextInt(1000);
		return loginname;
	}

	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@BeforeMethod
	public void navigateToRegistration() {
		      
				registrationPage = loginPage.goToRegisterPage();
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String rownum,String firstName, String lastName, String email,
			String telephone, String fax, String company, String address1,String address2, 
			String city, String state, String zipcode, String country, String loginname,
			String password, String subscribe) {
		
		 email = getRandomEmail();
		 loginname = getRandomLoginname();
		 		
		Boolean result = registrationPage.accountRegistration( firstName,lastName, email ,telephone, fax,
				company,address1,address2,city,state,zipcode,country,loginname,password,subscribe);
			
		int rno=Integer.parseInt(rownum);
		if(result==true)
			ExcelUtil.setCellValue(Constants.REGISTER_SHEET_NAME, loginname, rno, 13);
			Assert.assertTrue(result);


	}
	
	
}
