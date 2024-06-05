package Listener;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Retry_sampleTest {

	@Test(retryAnalyzer =com.comcast.crm.ListenerUtility.RetryListenerImplementation.class )
	public void createcontact()
	{
		System.out.println("1");
		System.out.println("2");
		Assert.fail();
		System.out.println("3");
		
	}
}
