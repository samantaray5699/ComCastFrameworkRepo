package Object.pom.repository_implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;

public class CreateOrganizationWithPomUsingGetters {

	@Test
	public void TC01() throws Throwable
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
		//object repository
		LoginPage lp=PageFactory.initElements(driver,LoginPage.class);
		lp.getUsernameEdt().sendKeys(username);
		lp.getPasswordEdt().sendKeys(password);
		lp.getLoginBtn().click();
		HomePage hp= new HomePage(driver);
		hp.getOrganizationlnk();
		CreatNewOrganizationPage co= new CreatNewOrganizationPage(driver);
		co.createorganization(data);
		co.getSavebtn().click();
		OrganizationInfoPage orginfo= new OrganizationInfoPage(driver);
		String actualheader=orginfo.getHeadertxt().getText();
		if(actualheader.contains(data))
		{
			Reporter.log(data+ "is displayed===pass");
		}
		else
		{
			Reporter.log(data+ "is not displayed===fail");
		}

		
		//signout
		Actions action = new Actions(driver);
		action.moveToElement(hp.getAdminstratorimg());
		hp.getSignoutbtn().click();
		driver.quit();


	}
}
