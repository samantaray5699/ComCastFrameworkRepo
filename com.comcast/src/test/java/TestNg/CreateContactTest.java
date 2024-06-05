package TestNg;

import java.io.IOException;
import java.time.Duration;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test
	public void CreateContctTest() throws Throwable
	{
		String lastname= elib.getDataFromExcel("Sheet4", 1, 2)+jlib.getRandomNum();

		HomePage hp= new HomePage(driver);
		hp.getContactlnk().click();
		ContactPage cp= new ContactPage(driver);
		cp.getCreatenewcontactBtn().click();
		CreatingNewContactPage cnp= new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(lastname);
		cnp.getSavebtn().click();
		String actuallastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actuallastname.contains(lastname))

		{
			Reporter.log(lastname+"is displayed ====pass");
		}
		else
		{
			Reporter.log(lastname+"is displayed ====fail");
		}	
	}
	@Test
	public void CreateContctWithSupportDateTest() throws Throwable, Throwable
	{

		String lastname= elib.getDataFromExcel("Sheet4", 1, 2)+jlib.getRandomNum();
		String curdate = jlib.getSystemDateYYYYDDMM();
		String dyndate = jlib.getRequiredDateYYYYDDMM(30);
		HomePage hp= new HomePage(driver);
		hp.getContactlnk().click();
		ContactPage cp= new ContactPage(driver);
		cp.getCreatenewcontactBtn().click();
		CreatingNewContactPage cnp= new CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(lastname);
		cnp.getStartdateEdt().clear();
		cnp.getStartdateEdt().sendKeys(curdate);
		cnp.getEnddateEdt().clear();
		cnp.getEnddateEdt().sendKeys(dyndate);
		cnp.getSavebtn().click();


	}

	@Test
	public void CreateContctWithOrgTest()  throws IOException, Throwable
	{

		String lastname= elib.getDataFromExcel("Sheet4", 1, 2)+jlib.getRandomNum();
		String orgname= elib.getDataFromExcel("Sheet4", 4, 3)+jlib.getRandomNum();
		String dropdown= elib.getDataFromExcel("Sheet1",10,3);//not working for dropdown
		
		HomePage hp= new HomePage(driver);
		hp.getOrganizationlnk().click();
		OrganizationsPage op= new OrganizationsPage(driver);
	    op.getCreateorg().click();
	    CreatNewOrganizationPage cno= new CreatNewOrganizationPage(driver);
	    cno.getOrgnameEdt().sendKeys(orgname);
	    cno.getSavebtn().click();
	    String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(orgname))
		{
			System.out.println(orgname+ "is  displayed===pass");
		}
		else
		{
			System.out.println(orgname+ "is not displayed===fail");
		}
		//Thread.sleep(3000);
		hp.getContactlnk().click();
		ContactPage cp= new ContactPage(driver);
		cp.getCreatenewcontactBtn().click();
		CreatingNewContactPage cnp= new  CreatingNewContactPage(driver);
		cnp.getLastnameEdt().sendKeys(lastname);
		cnp.getSearchorgBtn().click();
		wlib.switchToWindowOnUrl(driver, "Accounts&action=");
		//OrganizationsPage op1= new OrganizationsPage(driver);
		op.getSearchEDt().sendKeys(orgname);
		op.getSearchnowbtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		wlib.switchToWindowOnUrl(driver,"Contacts&action=EditView&return");
		cnp.getSavebtn();
		
		
		
		
	    
	}
}
