package com.qa.amazon.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.amazon.utils.Constants;
import com.qa.amazon.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By welcomuserText = By.xpath("//div[@class='menu_text']");
	private By logoutLink = By.xpath("(//a[@href=\"https://automationteststore.com/index.php?rt=account/logout\"])[1]");
	private By searchField = By.xpath("//input[@id=\"filter_keyword\"]");
	private By searchButton = By.xpath("//div[@title=\"Go\"]");
	private By productTypeList = By.xpath("//ul[@id=\"search-category\"]/li/a");
	

	// 2. page constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	
	
	public boolean isLogoutLinkExist() {
		eleUtil.doClick(welcomuserText);
		boolean result = eleUtil.doIsDisplayed(logoutLink);
		return result;
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doMoveToElement(welcomuserText);
			eleUtil.doClick(logoutLink);
		}
	}
	
		
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(searchField);
	}
	
	public void selectProductType(String productType) {
		System.out.println("Product type : " + productType);
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(productTypeList, 10, 2000);
		
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equals(productType)) 
			{
				e.click();
				break;
			}
			else
			{
				searchList.get(1).click();
			}
		}
		
	}
	
	public SearchResultsPage doSearch(String productName, String productType) {
		System.out.println("searching the product: " + productName);
		eleUtil.doClick(searchField);
		selectProductType(productType);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
	
	
}