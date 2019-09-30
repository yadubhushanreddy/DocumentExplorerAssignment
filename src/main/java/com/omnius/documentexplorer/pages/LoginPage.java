package com.omnius.documentexplorer.pages;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.omnius.documentexplorer.utilities.BasePage;
import com.omnius.documentexplorer.utilities.DriverManager;

public class LoginPage extends BasePage
{
    @FindBy(how = How.ID, using = "username")
    WebElement username_Txtbox;
    
    @FindBy(how = How.ID, using = "password")
    WebElement password_Txtbox;
    
    @FindBy(how = How.NAME, using = "login")
    WebElement login_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[text()='omnius']")
    WebElement omnius_Logo;
    
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Invalid username')]")
    WebElement loginFailure_txt;
    
    @FindBy(how = How.XPATH, using = "//chunk[text()='Document explorer']")
    WebElement documentExplorer_header;
    
    public LoginPage()
    {
    	PageFactory.initElements(DriverManager.getDriver(), this);
    }
    
    public Boolean isOmniUsLogoDisplayed()
    {
    	return isElementDisplayed(omnius_Logo, "Omnius logo");
    }
    
    public void enterUserName(String userName)
    {
    	sendKeys(username_Txtbox, userName, "Username");
    }
    
    public void enterPassword(String password)
    {
    	sendKeys(password_Txtbox, password, "Password");
    }
    
    public void clickLoginBtn()
    {
    	clickOn(login_Btn, "Login");
    }
    
    public Boolean isFailureTextDisplayed()
    {
    	return isElementDisplayed(loginFailure_txt, "Login Failed message");
    }
    
    public Boolean isHomePageDisplayed()
    {
    	return isElementDisplayed(documentExplorer_header, "Document Explorer Page");
    }
    
    public void loginWithInvalidCredentials(String userName, String password)
    {
    	Properties properties = LoginPage.readPropertyFile();
    	navigateToUrl(properties.getProperty("url"));
    	enterUserName(userName);
    	enterPassword(password);
    	clickLoginBtn();
    }
    
    public void loginWithEmptyCredentials(String userName, String password)
    {
    	enterUserName(userName);
    	enterPassword(password);
    	clickLoginBtn();
    }
    
    public void loginWithValidCredentials(String userName, String password)
    {
    	enterUserName(userName);
    	enterPassword(password);
    	clickLoginBtn();
    }
    
    
}
