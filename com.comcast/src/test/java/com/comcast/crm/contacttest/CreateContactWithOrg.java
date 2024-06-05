package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrg {
	@Test
	public void TC03() throws Throwable
	{//integration
		//read data from generic lib.
		PropertiesUtility plib= new PropertiesUtility();
		String browser=plib.getDataFromPropertiesFile("browser");
		String url=plib.getDataFromPropertiesFile("url");
		String username=plib.getDataFromPropertiesFile("username");
		String password=plib.getDataFromPropertiesFile("password");

		WebDriverUtility wlib= new WebDriverUtility();
		JavaUtility jlib= new JavaUtility();
		int num=jlib.getRandomNum();

		ExcellUtility elib= new ExcellUtility();
		String org= elib.getDataFromExcel("Sheet4", 4, 3)+num;
		String lastname= elib.getDataFromExcel("Sheet4", 4, 2);

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
		driver.findElement(By.name("accountname")).sendKeys(org);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(org))
		{
			Reporter.log(org+ "is displayed===pass");
		}
		else
		{
			Reporter.log(org+ "is not displayed===fail");
		}

		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		System.out.println(driver.getTitle());
		//switch to new window
		wlib.switchToWindowOnUrl(driver, "Accounts&action");
		driver.findElement(By.name("search_text")).sendKeys(org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+org+"']")).click();//making dynamic xpath
		//switch back to parent window
		wlib.switchToWindowOnUrl(driver,"Contacts&action");
		
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//verifyheader
		String actuallastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actualorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();


		if(actuallastname.contains(lastname)&& actualorgname.trim().equals(org))//trim to remove space
		{
			Reporter.log(lastname+org+"is displayed ====pass");
		}
		else
		{
			Reporter.log(lastname+org+"is displayed ====fail");
		}

		//signout
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
