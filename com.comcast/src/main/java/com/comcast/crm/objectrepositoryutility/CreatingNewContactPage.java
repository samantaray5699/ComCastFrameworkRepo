package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	public CreatingNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	@FindBy(name="support_start_date")
	private WebElement startdateEdt;
	public WebElement getStartdateEdt() {
		return startdateEdt;
	}
	@FindBy(name="support_end_date")
	private WebElement enddateEdt;
	public WebElement getEnddateEdt() {
		return enddateEdt;
	}
	@FindBy(xpath="(//img[@alt='Select'])[1]")
	private WebElement searchorgBtn;
	public WebElement getSearchorgBtn() {
		return searchorgBtn;
	}
}
