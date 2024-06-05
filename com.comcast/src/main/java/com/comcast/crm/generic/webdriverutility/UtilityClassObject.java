package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	private ThreadLocal<ExtentTest>test= new ThreadLocal<ExtentTest>();
	private ThreadLocal<WebDriver>driver= new ThreadLocal<WebDriver>();
	


}
