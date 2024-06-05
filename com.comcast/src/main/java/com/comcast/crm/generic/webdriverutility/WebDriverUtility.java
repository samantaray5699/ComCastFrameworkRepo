package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void maximaize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	public void getUrl(WebDriver driver,String url)
	{
		driver.get(url);
	}
	
	public void waitForPageLoad(WebDriver driver)   
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void switchToWindowOnUrl(WebDriver driver,String partialurl)
	{
		Set<String> allwindow = driver.getWindowHandles();
		for(String window:allwindow)//using for loop
		{
			driver.switchTo().window(window);
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains(partialurl))
			{
				break;
			}	
		}	
	}
	public void switchToWindowOnTitel(WebDriver driver,String titel)
	{
		Set<String> allwindow = driver.getWindowHandles();
		Iterator<String>it = allwindow.iterator();
		while(it.hasNext())//using iterator
		{
			String window= it.next();
			driver.switchTo().window(window);
			String actualtitel = driver.getTitle();
			if(actualtitel.contains(titel))
			{
				break;
			}
		}
			
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String name)
	{
		driver.switchTo().frame(name);
	}
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndDismis(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text)
	{
		Select sel = new  Select(element);
		sel.selectByVisibleText(text);	
	}
	public void select(WebElement element,int index)
	{
		Select sel = new  Select(element);
		sel.selectByIndex(index);	
	}
	public void select1(WebElement element,String value)
	{
		Select sel = new  Select(element);
		sel.selectByValue(value);	
	}
	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doublelick(WebDriver driver,WebElement element)
	{
		Actions act= new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	
}
