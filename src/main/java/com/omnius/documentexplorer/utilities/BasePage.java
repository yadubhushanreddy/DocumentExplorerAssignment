package com.omnius.documentexplorer.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BasePage 
{
	WebDriver driver;
	static Properties properties;
	static FileInputStream inputStream;
	
	
	public static Properties readPropertyFile()
	{
		try 
		{
			inputStream = new FileInputStream(System.getProperty("user.dir")+"//resources//config.properties");
		} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		properties = new Properties();
		try 
		{
			properties.load(inputStream);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	
	public void navigateToUrl(String url)
	{
		try
		{
		DriverManager.getDriver().get(url);
		System.out.println("Navigated to "+url);
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Navigating to "+url+" Failed");
		}
	}
	
	/*Method to wait for an element for maximum of 60 seconds before performing
	  an action on it. However, if the element is readily available or becomes
	  available then it continues without waiting. This method is used across
	  all reusable methods below to ensure the script waits for 60 seconds before
	  throwing an exception
	*/
	private WebElement waitForElement(WebElement elementToWaitFor, 
			                          String elementDescription)
	{
		WebElement webElement = null;
		try
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);
			webElement = wait.until(ExpectedConditions.
					          visibilityOf(elementToWaitFor));
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No Such Element Exception occurred while "
					+ "finding "+ elementDescription);	
		}
		catch(ElementNotVisibleException e1)
		{
			System.out.println("Element not Visible Exception while finding "
					+ elementDescription); 
		}
		catch(Exception ee)
		{
			System.out.println("Exception occurred while finding element "
					+ elementDescription);
			System.out.println(ee.getMessage());
			ee.printStackTrace();
		}
		return webElement;
	}
	
	/* Method to send data to any textbox type of element 
	  */
	public void sendKeys(WebElement element, String testData, 
			             String elementDescription)
	{
		try
		{
			waitForElement(element, elementDescription).clear();
			waitForElement(element, elementDescription).sendKeys(testData);
			System.out.println("Sending "+ testData + " to " + elementDescription +
					           " Successful");
			writeToReport(TestListener.logger, "pass", 
					"Sending "+ testData + " to " + elementDescription + " Successful");
		}
		catch(Exception e)
		{
			System.out.println("Sending " + testData +" to " + elementDescription +
					           " Failed");
			writeToReport(TestListener.logger, "fail", 
					"Sending "+ testData + " to " + elementDescription + " Failed");
			System.out.println(e.getMessage());
		}
		
	}
	
	/* Method to click on any element */
	public void clickOn(WebElement element, String elementDescription)
	{
		try
		{
			waitForElement(element, elementDescription).click();
			System.out.println("Clicking " + elementDescription + " Successful");
			writeToReport(TestListener.logger, "pass", "Clicking " + elementDescription + " Successful");
		}
		catch(Exception e)
		{
			System.out.println("Clicking " + elementDescription + " Failed");
	        System.out.println(e.getMessage());
	        writeToReport(TestListener.logger, "fail", "Clicking " + elementDescription + " Failed");
		}
	}
	
	/* Method to check if an element is displayed. Returns true if required 
	 * element is displayed and false if the element is not displayed*/
	public Boolean isElementDisplayed(WebElement element, String elementDescription)
	{
		try
		{
			waitForElement(element, elementDescription).isDisplayed();
			System.out.println(elementDescription + " was Displayed");
			writeToReport(TestListener.logger, "pass", elementDescription + " was Displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println(elementDescription + " was not Displayed");
			writeToReport(TestListener.logger, "fail", elementDescription + " was not Displayed");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void selectFromDropdown(WebElement element, String valueToBeSelected, String elementDescription)
	{
		try
		{
			Select select = new Select(waitForElement(element, elementDescription));
			select.selectByVisibleText(valueToBeSelected);
			System.out.println("Selected "+valueToBeSelected+" from "+elementDescription);
			writeToReport(TestListener.logger, "pass", "Selected "+valueToBeSelected+" from "+elementDescription);
		}
		catch(Exception e)
		{
			System.out.println("Selecting "+valueToBeSelected+" from "+elementDescription+" Failed");
			writeToReport(TestListener.logger, "fail", 
					"Selecting "+valueToBeSelected+" from "+elementDescription+" Failed");
			System.out.println(e.getMessage());
		}
	}
	
	/* Method to log every step into a html report*/
	public void writeToReport(ExtentTest logger,String stepResult, String testStep)
	{
		if(stepResult.equalsIgnoreCase("pass"))
		{
		  logger.log(Status.PASS, testStep);
		}
		else if(stepResult.equalsIgnoreCase("fail"))
		{
			logger.log(Status.FAIL, testStep);
		}
	}
	
	public static ExtentTest getExtentTestLogger()
	{
		return TestListener.logger;
	}
	
	public static void takeScreenshot(String tcName) throws IOException
	{
		try
		{
		TakesScreenshot screenShot = (TakesScreenshot)DriverManager.getDriver();
		File scrFile = screenShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+ "//Screenshots//"+tcName+".png"));
		}
		catch(Exception e)
		{
			System.out.println("Error in taking screenshot");
			System.out.println(e.getMessage());
		}
	}
	
	public void waitForSeconds(int seconds) throws InterruptedException
	{
		Thread.sleep(seconds*1000);
	}
	
	public static String randomPath() 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String random =formatter.format(date);
		return random;
	}
	
	

}
