package com.omnius.documentexplorer.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsConfig 
{
	public static ExtentReports configureHtmlReport() 
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter
				(System.getProperty("user.dir")+"//TestReport//TestReport.html");
		//htmlReporter.loadXMLConfig("ReportConfig.xml");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "OmniUs");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "Yadu bhushan");
        htmlReporter.config().setDocumentTitle("Omnius Test Report"); 
               // Name of the report
        htmlReporter.config().setReportName("Document Explorer Test Report"); 
               // Dark Theme
        htmlReporter.config().setTheme(Theme.DARK); 
		return extent;
	}
	

}
