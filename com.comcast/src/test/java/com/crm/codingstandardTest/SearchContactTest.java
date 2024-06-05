package com.crm.codingstandardTest;
/**
 * @author aditya
 * test class for contact module
 */

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass {

	@Test
	public void searchContact()
	{
		/*Step 1 log inn to app*/
		LoginPage lp= new LoginPage(driver);
		lp.login(null, null, null);
	}
}
