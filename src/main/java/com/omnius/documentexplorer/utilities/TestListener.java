package com.omnius.documentexplorer.utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener,ISuiteListener
{
	public static ExtentReports extentReport;
	public static ExtentTest logger;

	@Override
	public void onStart(ISuite suite) 
	{
		
		
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		logger = extentReport.createTest(result.getName());
		System.out.println("***********************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentReport.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		try {
			logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"//Screenshots//"+result.getName()+".png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		extentReport.flush();
		try 
		{
			BasePage.takeScreenshot(result.getName());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) 
	{
		String browserName = context.getCurrentXmlTest().getParameter("browsername");
		extentReport = ExtentReportsConfig.configureHtmlReport();
		WebDriver driver = null;
		try 
		{
			driver = DriverFactory.getDriver(browserName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		DriverManager.setDriver(driver);
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		DriverManager.closeDriver();
	}

}
