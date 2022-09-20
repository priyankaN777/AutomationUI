package com.qa.amazon.tests;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ExcelUtil;

public class CartPageTest extends BaseTest {
	

	@BeforeClass
	public void cartSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return ExcelUtil.getTestData(Constants.Products_SHEET_NAME);
	}
	
	@Test(priority = 0,dataProvider = "productSelectData")
	public void addCartTest(String producttype,String productsubtype,String searchkey,String mainProductName, String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
	    productInfoPage = searchResultPage.selectProduct(mainProductName);
		cartPage =productInfoPage.addToCart();
		cartPage.getCartData();
	}

		
	@Test(priority = 1,dependsOnMethods = {"addCartTest"})
	public void getCartTest() {
		cartPage.getCartData();
	}
	
	@Test(priority = 2,dependsOnMethods = {"addCartTest"})
	public void removeOutofStockItems() {
		List<Integer> OutofStockProducts=cartPage.getOutofStockProduct();
		boolean result = true;
		if(OutofStockProducts.size()>=1)
		{
			softAssert.assertTrue(cartPage.removeCartData(OutofStockProducts));
		}
		else
		{
			System.out.println("Cart doesnt contain any Out of Stock Product");
			softAssert.assertTrue(result);
		}
		
		softAssert.assertAll();
					
	}
	
	@Test(priority = 3,dataProvider = "productSelectData")
	public void checkQuantity(String producttype,String productsubtype,String searchkey,String mainProductName, String price, String quantity) {
		searchResultPage = accountsPage.doSearch(searchkey,producttype);
	    productInfoPage = searchResultPage.selectProduct(mainProductName);
		cartPage =productInfoPage.addToCart();
		boolean result = true;
		List<String> cartData =cartPage.getCartData();
		if (cartData.contains(mainProductName))
		{
			softAssert.assertTrue(cartPage.updateItemQuantity(mainProductName,quantity));
		}
		else
		{
			System.out.println(  mainProductName + " is not part of Cart");
			softAssert.assertTrue(result);
		}
				
			softAssert.assertAll();
	}
	
	
	@Test(priority = 4, dependsOnMethods = {"removeOutofStockItems","checkQuantity"})
	public void Checkout() {

		checkoutConfirmationPage=cartPage.checkoutCartData();
		Assert.assertEquals(checkoutConfirmationPage.getCheckoutPageHeader(), Constants.Checkout_PAGE_Header);
				
	}
	
	@Test(priority = 5, dependsOnMethods = {"Checkout"})
	public void confirm() {

		orderSuccesPage =checkoutConfirmationPage.confirmOrder();
		Assert.assertEquals(orderSuccesPage.getTitle(), Constants.Order_PAGE_Header);
				
	}
	
	@Test(priority = 6, dependsOnMethods = {"confirm"})
	public void getOrderNumber() {

		String ordernumber =orderSuccesPage.getOrderNumber();
		System.out.println("Order Number is "+ordernumber);
				
	}
	
	@Test(priority = 7, dependsOnMethods = {"confirm"})
	public void navigateToHome() {

		 accountsPage =orderSuccesPage.navigateToHome();
		 Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
				
	}
	

}
