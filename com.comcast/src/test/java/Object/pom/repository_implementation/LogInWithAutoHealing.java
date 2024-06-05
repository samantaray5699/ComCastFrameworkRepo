package Object.pom.repository_implementation;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LogInWithAutoHealing {

	@FindBy(name="user_name")
	WebElement usernameEdt;

	@FindBy(name="user_password")
	 WebElement passwordEdt;
	//autohealing
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath = "(//input[@value='Login'])[2]")})
	WebElement loginBtn;

	@Test
	public void login() throws IOException
	{
		PropertiesUtility plib= new PropertiesUtility();
		String browser=plib.getDataFromPropertiesFile("browser");
		String url=plib.getDataFromPropertiesFile("url");
		String username=plib.getDataFromPropertiesFile("username");
		String password=plib.getDataFromPropertiesFile("password");
		
		WebDriver driver= null;
		if(browser.equals("chrome"))
			driver= new ChromeDriver();
		else if(browser.equals("edge"))
			driver= new EdgeDriver();

		driver.get(url);
		driver.manage().window().maximize();
		WebDriverUtility wlib= new WebDriverUtility();
		wlib.waitForPageLoad(driver);
		LogInWithAutoHealing l=PageFactory.initElements(driver,LogInWithAutoHealing.class );
		l.usernameEdt.sendKeys(username);
		l.passwordEdt.sendKeys(password);
		l.loginBtn.click();


	}
}
