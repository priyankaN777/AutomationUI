package com.qa.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.amazon.utils.ElementUtil;

public class CheckoutConfirmationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By pageHeader = By.xpath("//span[@class='maintext']");
	private By confirmButton = By.id("checkout_btn");

	// 2. page constructor
	public CheckoutConfirmationPage(WebDriver driver) {
	    this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getCheckoutPageHeader() {
		String CheckoutPageHeaderText = eleUtil.doGetText(pageHeader);
		System.out.println("Checkout Confirmation Page header is: " + CheckoutPageHeaderText);
		return CheckoutPageHeaderText;
	}
	
	public OrderSuccesPage confirmOrder()
	{
		eleUtil.doClick(confirmButton);
		return new OrderSuccesPage(driver);
	}
	
	
}
