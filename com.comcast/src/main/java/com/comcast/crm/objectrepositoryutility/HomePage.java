package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement organizationlnk;
	
	public WebElement getOrganizationlnk() {
		return organizationlnk;
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactlnk;
	
	public WebElement getContactlnk() {
		return contactlnk;
	}
	
	@FindBy(linkText = "More")
	private WebElement morelnk;

	public WebElement getMorelnk() {
		return morelnk;
	}
	@FindBy(name = "Campaigns")
	private WebElement campaignslnk;
	
	
	//bussines utility
	public void navigateToCampainslnk()
	{
		Actions act= new Actions(driver);
		act.moveToElement(morelnk).perform();;
		campaignslnk.click();
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstratorimg;
	
	public WebElement getAdminstratorimg() {
		return adminstratorimg;
	}

	@FindBy(linkText="Sign Out")
	private WebElement signoutbtn;
	
	public WebElement getSignoutbtn() {
		return signoutbtn;
	}
	
	@FindBy(linkText = "Products")
	private WebElement productlnk;
	
	
	public WebElement getProductlnk() {
		return productlnk;
	}


	//bussiness lib.
	public void signout()
	{
	Actions action = new Actions(driver);
	action.moveToElement(adminstratorimg).perform();;
	signoutbtn.click();
	
	}
	
	

}
