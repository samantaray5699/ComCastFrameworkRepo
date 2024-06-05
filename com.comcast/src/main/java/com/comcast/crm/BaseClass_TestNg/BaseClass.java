package com.comcast.crm.BaseClass_TestNg;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {


	public WebDriver driver;
	public static WebDriver sdriver;
	public DatabaseUtility dlib= new DatabaseUtility();
	public PropertiesUtility plib= new PropertiesUtility();
	public WebDriverUtility wlib= new WebDriverUtility();
	public JavaUtility jlib= new JavaUtility();
	public ExcellUtility elib= new ExcellUtility();

	
	//we need to declar all object class and repostiory public becz we may use it out side the package
	@BeforeSuite
	public void configBs() throws IOException
	{
		System.out.println("connect to DB");
		dlib.getdirectConnection();

		
	}

	@BeforeClass
	public void configBc() throws Throwable
	{
		System.out.println("lunch beowser");
		String browser = plib.getDataFromPropertiesFile("browser");
		//	String browser= test.getParameter("browser");
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
		sdriver= driver;
	
	}
	@BeforeMethod
	public void configBm() throws Throwable
	{
		System.out.println("log in");
		String url=plib.getDataFromPropertiesFile("url");
		String username=plib.getDataFromPropertiesFile("username");
		String password=plib.getDataFromPropertiesFile("password");

		LoginPage lp= new LoginPage(driver);
		lp.login(url, username, password);

	}

	@AfterMethod
	public void configAm()
	{
		System.out.println("log out");
		HomePage hp= new HomePage(driver);
		hp.signout();
	}
	@AfterClass
	public void configAc()
	{
		System.out.println("Close browser");
		driver.quit();
	}
	@AfterSuite
	public void configAs()
	{
		System.out.println("close Db connection");
		dlib.closeConnection();

		
	}
}
