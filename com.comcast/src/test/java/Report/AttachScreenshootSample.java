package Report;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.ListenerUtility.ListenerImplementationClass;

public class AttachScreenshootSample {

	@Test
	public void Tc01()
	{
		//spark report config
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("Crm test suite result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);//optional by default light theme
		//Add env. info, and create test
		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("browser", "chrome");//we can add multiple
		ExtentTest test = report.createTest("tc01");//as per test case name

		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		TakesScreenshot ts= (TakesScreenshot)driver;
		String src = ts.getScreenshotAs(OutputType.BASE64);

		if("hdfc".equals("hfc"))
		{
			test.log(Status.PASS, "crated");
		}
		else
		{
			test.log(Status.FAIL, "not craeted");
			test.addScreenCaptureFromBase64String(src, "error");
		}
		report.flush();
		driver.close();
	}
}
