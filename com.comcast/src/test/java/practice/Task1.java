package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Task1 {
	
	@DataProvider
	public Object[][]data() throws IOException, Throwable
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data00 = wb.getSheet("product").getRow(1).getCell(0).toString();
		String data01=wb.getSheet("Product").getRow(1).getCell(1).toString();
		String data10=wb.getSheet("Product").getRow(2).getCell(0).toString();
		String data11=wb.getSheet("Product").getRow(2).getCell(1).toString();
		
		String data20=wb.getSheet("Product").getRow(3).getCell(0).toString();
		String data21=wb.getSheet("Product").getRow(3).getCell(1).toString();
		
		
		
		Object[][]obj=new Object[3][2];
		
			obj[0][0]=data00;
			obj[0][1]=data01;
			
			obj[1][0]=data10;
			obj[1][1]=data11;
			
			obj[2][0]=data20;
			obj[2][1]=data21;
		
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
