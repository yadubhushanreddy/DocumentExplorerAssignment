package documentexplorer.OmniusAssignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.omnius.documentexplorer.pages.CollectionsPage;
import com.omnius.documentexplorer.utilities.BasePage;

public class CollectionsTest 
{
	String newCollectionName;
	String collectionNameSubString = "SampleCollection";
	
	CollectionsPage collectionPage;
	
	@Test(priority=3)
	public void checkIfUserAbleToCreateCollection() throws InterruptedException
	{
		collectionPage = new CollectionsPage();
		Thread.sleep(5000);
		newCollectionName = collectionNameSubString + BasePage.randomPath();
		collectionPage.createNewCollection(newCollectionName);
		Assert.assertTrue(collectionPage.isDocumentExplorerDisplayed(), 
				"Document Explorer was not displayed after creating collection");
		Thread.sleep(5000);
	}
	
	@Test(priority=4)
	public void checkCollectionSearchFunctionality() throws InterruptedException
	{
		collectionPage.enterSearchKeyword(newCollectionName);
		Thread.sleep(5000);
		collectionPage.validateCollectionSearch(newCollectionName);
		/*Assert.assertTrue(collectionPage.getResultsRowCountForCollectionSearch()==1, 
				"Returned row count is more than 1");
		Assert.assertTrue(collectionPage.getCollectionNameFromResults().equals(newCollectionName),
				"Returned Collection name is not equal to that of created");*/
		Thread.sleep(5000);
	}
	
	@Test(priority=5)
	public void checkCollectionCreationWithDuplicateName() throws InterruptedException
	{
		collectionPage.createNewCollection(newCollectionName);
		Assert.assertTrue(collectionPage.isErrorMsgDisplayedForDuplicateCollection(), 
				"Error message was not displayed");
		collectionPage.clickCancelbutton();
		Thread.sleep(5000);
		
	}
	
	@Test(priority=6)
	public void checkSortingOnNameColumn() throws InterruptedException
	{
		collectionPage.enterSearchKeyword(collectionNameSubString);
		collectionPage.selectResultsCountToBeDisplayed();
		Thread.sleep(5000);
		collectionPage.checkIfNameColumnIsSorted();
		Thread.sleep(5000);
		collectionPage.enterSearchKeyword("");
	}
	
	@Test(priority=7)
	public void validateTotalResultsDisplayed() throws InterruptedException
	{
		collectionPage.enterSearchKeyword("");
		collectionPage.validateTotalResultsCountUnderCollections();	
	}
	
	@Test(priority=8)
	public void validateIfDocumentsSectionDisplayed() throws InterruptedException
	{
		collectionPage.enterSearchKeyword(newCollectionName);
		Thread.sleep(5000);
		collectionPage.clickOnFirstResult();
		collectionPage.checkIfDocumentsSectionDisplayed();
	}

}
