package com.omnius.documentexplorer.utilities;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverFactory 
{
	static Properties properties;
	/* Method to initialize driver object based on the browser name passed
	 * in TestNG.
	 */
	public static WebDriver getDriver(String browserName) throws Exception 
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			return getChromeDriver();
		}
		/*else if(browserName.equalsIgnoreCase("IE"))
		{
			return getInternetExplorerDriver();
		}*/
		else
		{
			throw new Exception("Invalid browser");
		}
		/*switch(browserName) 
		{
		case BrowserType.CHROME:
			return getChromeDriver();
		case BrowserType.IE:
			return getInternetExplorerDriver();
		case BrowserType.FIREFOX:
			return new FirefoxDriver();
		default:
			throw new Exception("Invalid browser");
		}*/
	}
	
	//Method to return Chromedriver object
	private static WebDriver getChromeDriver() 
	{
		properties = BasePage.readPropertyFile();
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+properties.getProperty("chromedriverpath"));
		return new ChromeDriver();
	}

	/*private static WebDriver getInternetExplorerDriver() {
		System.setProperty("webdriver.ie.driver", TestUtils.getDriverPath("ie"));
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		return new InternetExplorerDriver(ieCapabilities);
	}*/

}
