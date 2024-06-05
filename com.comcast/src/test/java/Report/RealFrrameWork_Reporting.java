package Report;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.ListenerUtility.ListenerImplementationClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
@Listeners(com.comcast.crm.ListenerUtility.ListenerImplementationClass.class)
public class RealFrrameWork_Reporting extends BaseClass {

	
	@Test
	public void createContact() throws IOException, Throwable
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
			ListenerImplementationClass.test.log(Status.PASS,"same");
		else
		{
			ListenerImplementationClass.test.log(Status.FAIL,"not same");	
			Assert.fail();
		}
	}        
}
