package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatNewOrganizationPage {
	public CreatNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(name="accountname")
	 private WebElement orgnameEdt;
	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getSavebtn() {
		return savebtn;
	}
	//business lib.
	public void createorganization(String orgname)
	{
		orgnameEdt.sendKeys(orgname);
		savebtn.click();
	}
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDD;
	public WebElement getIndustryDD() {
		return industryDD;
	}
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDD;
	
	public WebElement getTypeDD() {
		return typeDD;
	}
	
	
}
