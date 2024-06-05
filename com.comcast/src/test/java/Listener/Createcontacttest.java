package Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.BaseClass_TestNg.BaseClass;

//@Listeners(com.comcast.crm.ListenerUtility.ListenerImplementationClass.class)//fully qualified class
public class Createcontacttest extends BaseClass {

	@Test
	public void createcontact()
	{
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		Assert.fail();
	}
	@Test
	public void createcontactwithpn()
	{
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
	}
}
