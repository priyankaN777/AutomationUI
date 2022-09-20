package com.qa.amazon.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("acc page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@DataProvider
	public Object[][] productData() {
		return ExcelUtil.getTestData(Constants.Products_SHEET_NAME);
	}

	@Test(priority = 2, dataProvider = "productData")
	public void searchTest(String producttype,String productsubtype,String searchkey,String mainProductName,String price, String quantity ) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
		Assert.assertTrue(searchResultPage.getProductsListCount() > 0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return ExcelUtil.getTestData(Constants.Products_SHEET_NAME);
	}

	@Test(priority = 3, dataProvider = "productSelectData")
	public void selectProductTest(String producttype,String productsubtype,String searchkey,String mainProductName, String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}

}
