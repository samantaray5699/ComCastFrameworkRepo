package Report;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Sample {
	
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
		
		test.log(Status.INFO, "log in");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "Create contact");
		if("hdfc".equals("hdfc"))
		{
			test.log(Status.PASS, "crated");
		}
		else
			test.log(Status.FAIL, "not craeted");
		report.flush();
		
	}

}
