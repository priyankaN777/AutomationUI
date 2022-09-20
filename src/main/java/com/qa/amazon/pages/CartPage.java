package com.qa.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.amazon.utils.ElementUtil;

public class CartPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. page constructor
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private  By tableItemRows = By.xpath("(//table[@class=\"table table-striped table-bordered\"])[1]/tbody/tr");
	String beforeXpath = "(//table[@class=\"table table-striped table-bordered\"])[1]/tbody/tr[";
	String midXpath  = "]/td[";
	String afterXpath  = "]";
	
	private By completeXpath;
	private By CartPageHeader = By.xpath("//span[@class=\"maintext\"]");
	private By updateButton = By.id("cart_update");
	private By CheckoutButton = By.id("cart_checkout1");

	
	public String getCartHeader() {
		String cartHeaderText = eleUtil.doGetText(CartPageHeader);
		System.out.println("Cart Page header is: " + cartHeaderText);
		return cartHeaderText;
	}
	
	public List<String> getCartData()
	{
		int rowCount = eleUtil.getElements(tableItemRows).size();
		System.out.println("Row Count :"+rowCount);	
		List<String> cartProductNames = new ArrayList<String>();
		for (int i=2; i<=rowCount; i++)
		{
			for (int j=1; j<=7; j++)
			{
		      String compXpath= beforeXpath + i + midXpath + j + afterXpath;
		      completeXpath = By.xpath(compXpath);
		      String str =eleUtil.getElement(completeXpath).getText();
		      System.out.print("   " +str);
		      
		      //Retrieving only product names in  list
		      String productnameXpath= beforeXpath + i + midXpath + j + afterXpath;
		      completeXpath = By.xpath(productnameXpath);
		      String str1 =eleUtil.getElement(completeXpath).getText();
		      cartProductNames.add(str1);
		      
			}
			 System.out.println();
		}
		return cartProductNames;
				
	}
	
	public boolean removeCartData(List<Integer> OutofStockList)
	{
		
		for(int i : OutofStockList)
		{
				      
			    	  String extendedXpath ="/a";
			    	  String deletebuttonXpath= beforeXpath + i + midXpath + 7 + afterXpath + extendedXpath;
				      completeXpath = By.xpath(deletebuttonXpath);
				      eleUtil.doClick(completeXpath);
				      System.out.println(+i +"th Product removed from cart");
			      			     
		}
		return true;
		
		
	}
	
	public List<Integer> getOutofStockProduct()
	{
		int rowCount = eleUtil.getElements(tableItemRows).size();
		List<Integer> OutofStockList = new ArrayList<Integer>();
		for (int i=2; i<=rowCount; i++)
	    {
			  String productnameXpath= beforeXpath + i + midXpath + 2 + afterXpath;
		      completeXpath = By.xpath(productnameXpath);
		      String str =eleUtil.getElement(completeXpath).getText();
		      System.out.println("String str :"+str);
		      if(str.contains("***"))
		      {
		    	  OutofStockList.add(i);
		    	 
		      }
		      
	    }
		
		return OutofStockList;
	
	}

	

	public boolean updateItemQuantity(String mainProductName, String quantity) {
		int rowCount = eleUtil.getElements(tableItemRows).size();
		boolean flag = false;
		for (int i=2; i<=rowCount; i++)
		{
			  String extendedXpath ="/div/input";
	    	  String deletebuttonXpath= beforeXpath + i + midXpath + 5 + afterXpath + extendedXpath;
		      completeXpath = By.xpath(deletebuttonXpath);
		      String str =eleUtil.getElement(completeXpath).getAttribute("value");
		      System.out.println("Quantity before update :" +str);
		      eleUtil.getElement(completeXpath).clear();
		      eleUtil.getElement(completeXpath).sendKeys(quantity);
		      eleUtil.doClick(updateButton);
		      flag = true;
		      String str1 =eleUtil.getElement(completeXpath).getAttribute("value");
		      System.out.println("Quantity after update :" +str1);
			
		}
		return flag;
		
	}

	public CheckoutConfirmationPage checkoutCartData() {
		eleUtil.doClick(CheckoutButton);
		return new CheckoutConfirmationPage(driver);
		}

	
	
	

}
