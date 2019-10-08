package documentexplorer.OmniusAssignment;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.omnius.documentexplorer.pages.LoginPage;

import com.omnius.documentexplorer.utilities.TestListener;

@Listeners(TestListener.class)
public class LoginTest 
{
  LoginPage loginPage;
	
  @Test(priority=0)
  public void verifyLoginWithInvalidCredentials() 
  {
	  loginPage = new LoginPage();
	  loginPage.loginWithInvalidCredentials("invalid", "invalid");
	  Assert.assertTrue(loginPage.isOmniUsLogoDisplayed(), "Omnius logo was not displayed");
	  Assert.assertTrue(loginPage.isFailureTextDisplayed(), 
			  "Invalid username or password is not displayed");	  
  }
  
  @Test(priority=1)
  public void verifyLoginWithEmptyCredentials()
  {
	  Assert.assertTrue(loginPage.isOmniUsLogoDisplayed(), "Omnius logo was not displayed");
	  loginPage.loginWithEmptyCredentials("", "");
	  Assert.assertTrue(loginPage.isFailureTextDisplayed(), 
			  "Invalid username or password was not displayed");
  }
  
  @Test(priority=2,groups= {"validLogin"})
  public void verifyLoginWithValidCredentials()
  {
	  loginPage.loginWithEmptyCredentials("testuser", "testuser");
	  Assert.assertTrue(loginPage.isHomePageDisplayed(), 
			  "Document Explorer page was not displayed");
  }
}
