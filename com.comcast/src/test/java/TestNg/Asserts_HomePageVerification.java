package TestNg;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.BaseClass_TestNg.BaseClass;

public class Asserts_HomePageVerification extends BaseClass {

	@Test
	public void homePageVerificationTest()
	{
		String actualresult = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		Assert.assertEquals(actualresult ,"Homepage");
		
		
		
	}
	
	@Test
	public void logoVerificationTest()
	{
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isDisplayed();
		SoftAssert ast= new SoftAssert();
		ast.assertTrue(status);
		ast.assertAll();
	}
}
