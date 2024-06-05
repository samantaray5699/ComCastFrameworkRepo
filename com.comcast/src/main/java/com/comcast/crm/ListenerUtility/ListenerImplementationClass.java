package com.comcast.crm.ListenerUtility;

import java.io.File;
import java.io.IOException;   

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.BaseClass_TestNg.BaseClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class ListenerImplementationClass implements ITestListener , ISuiteListener{

	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//System.out.println("Report configuration");
		//report
		JavaUtility jlib= new JavaUtility();
		String timestamp= jlib.getTimeStamp();
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport1/Report1+"+timestamp+".html");
		spark.config().setDocumentTitle("CRM Test Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "window");
		report.setSystemInfo("browser", "Chrome");
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==========="+result.getMethod().getMethodName()+"======START======");//print name of the test case in console
		test= report.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==========="+result.getMethod().getMethodName()+"======START======");//print name of the test case in console
	}

	@Override
	public void onTestFailure(ITestResult result) {
		JavaUtility jlib= new JavaUtility();
		String testname=result.getName();
		TakesScreenshot ts= (TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src, testname+jlib.getTimeStamp());
		
	}

}



