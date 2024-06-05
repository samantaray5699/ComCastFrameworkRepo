package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createorg;

	public WebElement getCreateorg() {
		return createorg;
	}
	
	@FindBy(name="search_text")
	private WebElement searchEDt;
	
	public WebElement getSearchEDt() {
		return searchEDt;
	}

	@FindBy(name="search_field")
	private WebElement searchDD;

	public WebElement getSearchDD() {
		return searchDD;
	}
	
	@FindAll({@FindBy(name="search"),@FindBy(name="submit")})
	private WebElement serchnowbtn;
	public WebElement getSearchnowbtn() {
		return serchnowbtn;
	}
}
