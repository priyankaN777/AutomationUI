package com.qa.amazon.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return ExcelUtil.getTestData(Constants.Products_SHEET_NAME);
	}

	
	@Test(priority = 1,dataProvider = "productSelectData")
	public void productHeaderTest(String producttype,String productsubtype,String searchkey,String mainProductName,String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}
	

	@Test(priority = 2,dataProvider = "productSelectData")
	public void productInfoTest(String producttype,String productsubtype,String searchkey,String mainProductName,String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoMap.get("name"), mainProductName);
		softAssert.assertEquals(actProductInfoMap.get("price"), price);
		softAssert.assertAll();
	}
	
	@Test(priority = 3,dataProvider = "productSelectData")
	public void addProductTest(String producttype,String productsubtype,String searchkey,String mainProductName,String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
	    productInfoPage = searchResultPage.selectProduct(mainProductName);
		cartPage =productInfoPage.addToCart();
		Assert.assertEquals(cartPage.getCartHeader(), Constants.Cart_PAGE_Header);
	}
	
	@Test(priority = 3,dataProvider = "productSelectData")
	public void OutofStockTest(String producttype,String productsubtype,String searchkey,String mainProductName, String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
	    productInfoPage = searchResultPage.selectProduct(mainProductName);
	    accountsPage =productInfoPage.navigateToHome();
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}

}
