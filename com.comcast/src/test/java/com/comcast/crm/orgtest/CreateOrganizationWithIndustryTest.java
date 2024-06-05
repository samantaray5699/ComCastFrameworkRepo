package com.comcast.crm.orgtest;


import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustryTest {
	@Test
	public void TC02() throws Throwable
	{
		//read data from generic lib.
		PropertiesUtility plib= new PropertiesUtility();
		String browser=plib.getDataFromPropertiesFile("browser");
		String url=plib.getDataFromPropertiesFile("url");
		String username=plib.getDataFromPropertiesFile("username");
		String password=plib.getDataFromPropertiesFile("password");

	
		JavaUtility jlib = new JavaUtility();
		ExcellUtility elib= new ExcellUtility();
		WebDriverUtility wlib= new WebDriverUtility();
		String data= elib.getDataFromExcel("Sheet1", 1, 2)+jlib.getRandomNum();
		String industry = elib.getDataFromExcel("Sheet1", 4, 3);
		String type= elib.getDataFromExcel("Sheet1", 4, 4);
		

		WebDriver driver=null;
		if(browser.equals("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		else
			driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		wlib.waitForPageLoad(driver);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(data);
		WebElement ind_dropdown = driver.findElement(By.name("industry"));
		Select select1= new Select(ind_dropdown);
		select1.selectByVisibleText(industry);

		WebElement typedropdown = driver.findElement(By.name("accounttype"));
		Select select2= new Select(typedropdown);
		select2.selectByVisibleText(type);
		// save		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify header msg
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(data))
		{
			Reporter.log(data+ "is displayed===pass");
		}
		else
		{
			Reporter.log(data+ "is not displayed===fail");
		}
		//verify org name , industry,type info
		String actualorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String actualindustry = driver.findElement(By.id("dtlview_Industry")).getText();
		String actualtype = driver.findElement(By.id("dtlview_Type")).getText();

		if(actualorgname.equals(data) && actualindustry.equals(industry)&& actualtype.equals(type))
		{
			Reporter.log(data+industry+type+ "is displayed in info===pass");
		}
		else
		{
			Reporter.log(data+industry+type+ "is not displayed in info===fail");
		}

		//signout
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
