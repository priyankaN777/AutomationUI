package com.qa.amazon.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.amazon.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.xpath("//div[@class=\"col-md-12\"]/h1/span");
	private Map<String, String> productInfoMap;
	private By productPriceData = By.xpath("(//label[@class=\"control-label\"]/span)[1]");
	private By addToCartBtn = By.xpath("//a[@class=\"cart\"]");
	private By checkAvailability = By.xpath("(//ul[@class=\"productinfo\"]/li)[1]");
	private By accountButton = By.xpath("(//a[@class=\"top menu_account\"])[2]");

	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	

	public String getProductHeader() {
		String productHeaderText = eleUtil.doGetText(productHeader);
		System.out.println("product header is: " + productHeaderText);
		return productHeaderText;
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductPriceData();
		return productInfoMap;
	}


	private void getProductPriceData() 
	{
		WebElement totalPrice = eleUtil.getElement(productPriceData);
		String price =totalPrice.getText().trim();
		System.out.println("Product Price : "+price);
		productInfoMap.put("price", price);
		
	}
	
	public boolean checkAvailability()
	{
		
		  String availability =eleUtil.getElement(checkAvailability).getText();
		  if (availability.contains("In Stock"))
		    	return true;
		  else if (availability.contains("Out of Stock"))
		    	return false;
			return false;
	}
	
	public CartPage addToCart() {
		boolean availibility =checkAvailability();
         if (availibility==true)
	     eleUtil.doClick(addToCartBtn);
         else
         {
        	 System.out.println("Item is Out of Stock");
             navigateToHome();
         }
         return new CartPage(driver);
	}
	
	public AccountsPage navigateToHome() {
  	     eleUtil.doClick(accountButton);
         return new AccountsPage(driver);
	}
}
