package TestNg;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Data_Provider_Amazon_Test {

	@DataProvider
	public Object[][]data() throws Throwable, Throwable
	{
		ExcellUtility elib= new ExcellUtility();
		int count = elib.getRowCountOfSyStemExcel("Product");
		Object[][] obj= new Object[count][2];
		for(int i=0;i<count;i++)
		{	
			obj[i][0]=elib.getDataFromSystemExcel("Product",i+1, 0);
			obj[i][1]=elib.getDataFromSystemExcel("Product",i+1, 1);
		}
		return obj;	        
	}

	@Test(dataProvider = "data")
	public void productTest(String brand,String Productname)
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		WebDriverUtility wlib= new WebDriverUtility();
		wlib.waitForPageLoad(driver);
		wlib.maximaize(driver);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand,Keys.ENTER);
		WebElement priceEle = driver.findElement(By.xpath("//span[text()='"+Productname+"']/../../../..//span[text()='48,999']"));
		String price = priceEle.getText();
		System.out.println(price);
		driver.quit();
	}
}
