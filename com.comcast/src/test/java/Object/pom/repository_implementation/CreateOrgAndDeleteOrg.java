package Object.pom.repository_implementation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgAndDeleteOrg {

	@Test
	public void TC01() throws Throwable
	{
		// allutility
		PropertiesUtility plib= new PropertiesUtility();
		ExcellUtility elib= new ExcellUtility();
		WebDriverUtility wlib= new   WebDriverUtility();
		JavaUtility jlib= new JavaUtility();
		//property data
		String browser=plib.getDataFromPropertiesFile("browser");
		String url=plib.getDataFromPropertiesFile("url");
		String username=plib.getDataFromPropertiesFile("username");
		String password=plib.getDataFromPropertiesFile("password");

		//exceldata
		String orgname=elib.getDataFromExcel("Sheet1", 10, 2)+jlib.getRandomNum();
		String dropdown=elib.getDataFromExcel("Sheet1", 10, 3);
		

		WebDriver driver= null;
		if (browser.equals("chrome"))
			driver= new ChromeDriver();
		else if(browser.equals("edge"))
			driver= new EdgeDriver();
		wlib.maximaize(driver);
		wlib.getUrl(driver,url);
		wlib.waitForPageLoad(driver);
		//login
		LoginPage lp= new LoginPage(driver);
		lp.login(username, password);
		//homepage
		HomePage hp= new HomePage(driver);
		hp.getOrganizationlnk().click();
		//orgpage
		OrganizationsPage op= new OrganizationsPage(driver);
		op.getCreateorg().click();
		//creteorg page
		CreatNewOrganizationPage cop= new  CreatNewOrganizationPage(driver);
		cop.createorganization(orgname);
		OrganizationInfoPage orginfo = new OrganizationInfoPage(driver);
		String header = orginfo.getHeadertxt().getText();
		//try script execution with verifying  
//		if(header.contains(orgname))
//		{
//			Reporter.log(orgname+ "is displayed===pass");
//		}
//		else
//		{
//			Reporter.log(orgname+ "is not displayed===fail");
//		}
		
		//dltproceess
		//Thread.sleep(3000);
		//wlib.waitForElement(driver, hp.getOrganizationlnk());
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Organizations")));
		hp.getOrganizationlnk().click();
		//driver.findElement(By.linkText("Organizations")).click();
		op.getSearchEDt().sendKeys(orgname);
		wlib.select(op.getSearchDD(),dropdown );
		op.getSearchnowbtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//input[@name='selected_id']")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//a[text()='del']")).click();
		wlib.switchToAlertAndAccept(driver);
		driver.quit();

	}
}
