package TestNg;


import org.testng.annotations.Test;

import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgTest extends BaseClass {

	@Test
	public void createorg() throws Throwable
	{
		String orgname= elib.getDataFromExcel("Sheet4", 4, 3)+jlib.getRandomNum();
		HomePage hp= new HomePage(driver);
		hp.getOrganizationlnk().click();
		OrganizationsPage op= new OrganizationsPage(driver);
		op.getCreateorg().click();
		CreatNewOrganizationPage cno= new CreatNewOrganizationPage(driver);
		cno.getOrgnameEdt().sendKeys(orgname);
		cno.getSavebtn();
	}
	
	@Test
	public void createorgWithIndustryType() throws Throwable
	{
		String orgname= elib.getDataFromExcel("Sheet4", 4, 3)+jlib.getRandomNum();
		HomePage hp= new HomePage(driver);
		hp.getOrganizationlnk().click();
		OrganizationsPage op= new OrganizationsPage(driver);
		op.getCreateorg().click();
		CreatNewOrganizationPage cno= new CreatNewOrganizationPage(driver);
		cno.getOrgnameEdt().sendKeys(orgname);
		wlib.select1(cno.getIndustryDD(), "Energy");
		wlib.select1(cno.getTypeDD(), "Partner");
		cno.getSavebtn();
	}
}
