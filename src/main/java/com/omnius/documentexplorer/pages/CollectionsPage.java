package com.omnius.documentexplorer.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.omnius.documentexplorer.utilities.BasePage;
import com.omnius.documentexplorer.utilities.DriverManager;

public class CollectionsPage extends BasePage
{
	@FindBy(how = How.CLASS_NAME, using = "iconPlus")
	WebElement plus_icon;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Name']")
	WebElement collectionName_txtbox;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Create']")
	WebElement create_btn;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Cancel']")
	WebElement cancel_btn;
	
	@FindBy(how = How.XPATH, using = "//chunk[text()='Document explorer']")
    WebElement documentExplorer_header;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search')]")
	WebElement collectionSearch_txtbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search')]/ancestor::header/following-sibling::main/table//tr")
	List<WebElement> collectionSearchResultsList;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search')]/ancestor::header/following-sibling::main/table//tr//td//block")
	List<WebElement> collectionNamesOfResultslist;
	
	@FindBy(how = How.XPATH, using = "//paragraph[contains(text(),'Something went unexpectedly wrong')]")
	WebElement duplicateCollection_errorMsg;
	
	@FindBy(how = How.XPATH, using = "//icon[contains(@class,'iconSort')]")
	WebElement collectionNameSort_icon;
	
	@FindBy(how = How.XPATH, using = "//displaying//paragraph")
	WebElement totalResults_count;
	
	@FindBy(how = How.XPATH, using = "//page-sizes//select")
	WebElement pageSize_dropdown;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Next']")
	WebElement next_button;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Documents']")
	WebElement documents_section;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Upload']")
	WebElement upload_button;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Documents']/following::chunk[text()='Name']")
	WebElement documentSectionName_column;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Documents']/following::th[contains(@class,'filterable')]/chunk[text()='Processing']")
	WebElement documentSectionProcessing_column;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Documents']/following::th[contains(@class,'filterable')]/chunk[text()='Validation']")
	WebElement documentSectionvalidation_column;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Documents']/following::chunk[text()='Preview']")
	WebElement documentSectionPreview_column;
	//input[contains(@placeholder,'Search')]/ancestor::header/following-sibling::main/table//tr
	
	//input[contains(@placeholder,'Search')]/ancestor::header/following-sibling::main/table//tr//td//block
	
	public CollectionsPage()
	{
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public void clickPlusIcon()
	{
		clickOn(plus_icon, "Plus Icon");
	}
	
	public void enterNewCollectionName(String testData)
	{
		sendKeys(collectionName_txtbox, testData, "New Collection name Textbox");
	}
	
	public void clickCreateButton()
	{
		clickOn(create_btn, "Create Button");
	}
	
	public void clickCancelbutton()
	{
		clickOn(cancel_btn, "Cancel Button");
	}
	
	public Boolean isDocumentExplorerDisplayed()
	{
		return isElementDisplayed(documentExplorer_header, "Document explorer page header");
	}
	
	public void enterSearchKeyword(String collectionName)
	{
		sendKeys(collectionSearch_txtbox, collectionName, "Collection search textbox");
	}
	
	public int getResultsRowCountForCollectionSearch()
	{
		return collectionSearchResultsList.size();
	}
	
	public String getCollectionNameFromResults()
	{
		return collectionNamesOfResultslist.get(collectionNamesOfResultslist.size()-1).getText();
	}
	
	public void validateCollectionSearch(String newCollectionName)
	{
		Assert.assertTrue(getResultsRowCountForCollectionSearch() == 1, 
				"Returned row count is more than 1 which is not valid");
		System.out.println("Result matching the search criteria was displayed");
		getExtentTestLogger().log(Status.PASS, "Returned only searched result");
		Assert.assertTrue(getCollectionNameFromResults().equals(newCollectionName), 
				"Returned Collection name is not equal to that of created");
		System.out.println("Returned result collection name "+getCollectionNameFromResults());
		getExtentTestLogger().log(Status.PASS, "Returned Collection name is equal to that of created");
	}
	
	public void createNewCollection(String newCollectionName)
	{
		clickPlusIcon();
		enterNewCollectionName(newCollectionName);
		clickCreateButton();
	}
	
	public Boolean isErrorMsgDisplayedForDuplicateCollection()
	{
		return isElementDisplayed(duplicateCollection_errorMsg, "Error message");
	}
	
	public void clickSortIconOnNameColumn()
	{
		clickOn(collectionNameSort_icon, "Name Column Sort icon");
	}
	
	public void checkIfNameColumnIsSorted() throws InterruptedException
	{
		clickSortIconOnNameColumn();
		System.out.println("Name column sorted in Ascending order");
		waitForSeconds(5);
		String firstResultBeforeSort = collectionNamesOfResultslist.get(0).getText();
		String lastResultBeforeSort = collectionNamesOfResultslist.get(collectionNamesOfResultslist.size()-1).getText();
		getExtentTestLogger().log(Status.PASS, "First result in table before sorting "+firstResultBeforeSort);
		getExtentTestLogger().log(Status.PASS, "Last result in table before sorting "+lastResultBeforeSort);
		clickSortIconOnNameColumn();
		System.out.println("Name column sorted in Descending order");
		waitForSeconds(5);
		String firstResultAfterSort = collectionNamesOfResultslist.get(0).getText();
		String lastResultAfterSort = collectionNamesOfResultslist.get(collectionNamesOfResultslist.size()-1).getText();
		Assert.assertTrue(firstResultBeforeSort.equals(lastResultAfterSort), 
				"Sorting is not proper");
		getExtentTestLogger().log(Status.PASS, 
				"First result before sort is equal to last result after sort");
		Assert.assertTrue(lastResultBeforeSort.equals(firstResultAfterSort), 
				"Sorting is not proper");
		getExtentTestLogger().log(Status.PASS, 
				"First result after sort is equal to last result before sort");
		
		
	}
	
	public int getTotalResultsCount()
	{
		return Integer.valueOf(totalResults_count.getText().split("of ")[1].replace(")", ""));
	}
	
	public void selectResultsCountToBeDisplayed()
	{
		selectFromDropdown(pageSize_dropdown, "100", "PageSize dropdown");
	}
	
	public void validateTotalResultsCountUnderCollections() throws InterruptedException
	{
		selectResultsCountToBeDisplayed();
		waitForSeconds(5);
		int totalResultCount = getTotalResultsCount();
		Assert.assertTrue(totalResultCount==countResultsOnEachPage(), 
				"Total count and results displayed did not match");
		System.out.println("Total result count displayed is same as number of results displayed");
		getExtentTestLogger().log(Status.PASS, 
				"Total results count displayed was "+totalResultCount);
		getExtentTestLogger().log(Status.PASS, "Actual results counted was "+countResultsOnEachPage());
	}
	
	public int countResultsOnEachPage()
	{
		int totalCount = 0;
		int pageCount = 1;
		if(next_button.isEnabled())
		{
		while(next_button.isEnabled())
		{
			totalCount = totalCount + collectionSearchResultsList.size();
			getExtentTestLogger().log(Status.PASS, 
					"Number of results in Page "+pageCount+" is "+totalCount);
			clickOn(next_button, "Next Button");
			pageCount++;
		}
		}
		else if(!next_button.isEnabled())
		{
			totalCount = totalCount + collectionSearchResultsList.size();
			getExtentTestLogger().log(Status.PASS, 
					"Number of results in Page "+pageCount+" is "+totalCount);
		}
		return totalCount;
	}
	
	public void clickOnFirstResult()
	{
		clickOn(collectionNamesOfResultslist.get(0), "First Collection Result");
	}
	
	public Boolean isDocumentLabelDisplayed()
	{
		return isElementDisplayed(documents_section, "Documents Label");
	}
	
	public Boolean isUploadButtonDisplayed()
	{
		return isElementDisplayed(upload_button, "Upload button");
	}
	
	public Boolean isNameColumnDisplayed()
	{
		return isElementDisplayed(documentSectionName_column, "Name Column in Document section");
	}
	
	public Boolean isProcessingColumnDisplayed()
	{
		return isElementDisplayed(documentSectionProcessing_column, 
				"Processing column in Document section");
	}
	
	public Boolean isvalidationColumnDisplayed()
	{
		return isElementDisplayed(documentSectionvalidation_column, 
				"validation column in Document section");
	}
	
	public Boolean isPreviewColumnDisplayed()
	{
		return isElementDisplayed(documentSectionPreview_column, 
				"Preview column in Document section");
	}
	
	public void checkIfDocumentsSectionDisplayed()
	{
		Assert.assertTrue(isDocumentLabelDisplayed(), 
				"Document Label was not displayed");
		Assert.assertTrue(isUploadButtonDisplayed(), 
				"Upload button was not displayed");
		Assert.assertTrue(isNameColumnDisplayed(), 
				"Name Column was not displayed");
		Assert.assertTrue(isProcessingColumnDisplayed(), 
				"Processing Column was not displayed");
		Assert.assertTrue(isvalidationColumnDisplayed(), 
				"Validation Column was not displayed");
		Assert.assertTrue(isPreviewColumnDisplayed(), 
				"Preview Column was not displayed");
		
	}

}
