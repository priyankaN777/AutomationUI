package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ElementUtil;

public class OrderSuccesPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By OrderNumber = By.xpath("(//p)[2]");
	private By accountButton = By.xpath("(//a[@class=\"top menu_account\"])[2]");
	
	// 2. page constructor
	public OrderSuccesPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getTitle() {
		String OrderSuccessPageHeaderText = eleUtil.doGetTitle(Constants.Order_PAGE_Header, 10);
		System.out.println("Order Page header is: " + OrderSuccessPageHeaderText);
		return OrderSuccessPageHeaderText;
	}
	
		
	public String getOrderNumber()
	{
		String str =eleUtil.getElement(OrderNumber).getText();
		String[] strArray = str.split(" ");
		System.out.println("Order number is : " +strArray[2]);
		return strArray[2];
		
	}
	
	public AccountsPage navigateToHome()
	{
		eleUtil.doClick(accountButton);
		return new AccountsPage(driver);
	}
}
