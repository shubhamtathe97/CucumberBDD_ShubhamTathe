package pom.cucumberBDD.framework.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;
import junit.framework.Assert;

public class LandingPageObjects {

	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;


	// Locators
	private By ProListElement       = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li");
	private By LanPageLogoElement   = By.xpath("//div[@id='header_logo']/a/img");
	private By SighInBtnElement     = By.xpath("//a[@class='login']");
	private By SearchBoxElement     = By.xpath("//input[@placeholder='Search']");
	private By SearchProdList       = By.xpath("//div[@class='ac_results']//li");
	private By TwitterLogoElement   = By.xpath("//li[@class='twitter']/a");
	private By TwitterAccElement    = By.xpath("//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wbh5a2']//preceding-sibling::div[1]/child::div[1]/child::div[1]/span/span");
	private By SubscriMsgElement    = By.xpath("//p[@class='alert alert-success']");
	private By NewlatterBoxElment   = By.xpath("//input[@name='email']");
	private By NewlatterBtnElment   = By.xpath("//button[@name='submitNewsletter']");
	private By failSubscriMsgElement= By.xpath("//p[@class='alert alert-danger']");


	// Constructor
	public LandingPageObjects(WebDriver driver, Scenario scn ) {
		this.driver = driver;
		this.scn = scn;

	}

	// Scenario-1 (common used)
	public void VarifyThePage() {

		String ExpLanPageElement=driver.getTitle();
		Assert.assertTrue("Page validation Sucessful", ExpLanPageElement.equals("My Store"));
		scn.log("Page validation successfull. actual Landing page Element is: " + ExpLanPageElement );
		logger.info("Page validation successfull. actual Landing page Element is: " + ExpLanPageElement );
	}

	// Scenario-1
	public void URLValidation(String URLContain) {

		String actual=driver.getCurrentUrl();
		Assert.assertEquals("Landing page URl validation", actual, URLContain);
		scn.log("Page URL validation successfull. Actual URL: " + actual );
		logger.info("Page URL validation successfull. Actual URL: " + actual );
	}

	// Scenario-2
	public void validateLandingPageTitle(String Title)
	{
		String actual =driver.getTitle();
		Assert.assertEquals("Page Title validation",Title,actual);
		scn.log("Page title validation successfull. Actual title: " + actual );
		logger.info("Page title validation successfull. Actual title: " + actual );
	}

	// Scenario-3
	public void ValidateProductListByCount(int ProductCount) {

		List<WebElement> productList=driver.findElements(ProListElement);
		int actualCount=productList.size();

		Assert.assertEquals("Product list count does not match", actualCount, ProductCount);
		scn.log("Product list count is match and count is: " + actualCount );
		logger.info("Product list count is match and count is: " + actualCount );	
	}

	// Scenario-3
	public void ValidateProductListBYName(List<Map<String,String>> proInfo){

		for (int i=0; i<=proInfo.size()-1;i++) {
			ClickOnFooterLinkAndVerify(proInfo, i);
		}
		scn.log("All provided product category list get match");
		logger.info("All provided product category list get match");

	}

	// Common Method 
	public void ClickOnFooterLinkAndVerify(List<Map<String,String>> proInfo, int index) {
		String productTextHyperLink = proInfo.get(index).get("productLinkText");
		String productUrlText = proInfo.get(index).get("productLinkUrlText");

		WebElement ProListElement2 = driver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[text()='"+productTextHyperLink+"']"));
		wait =new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ProListElement2));
		
		ProListElement2.click();

		wait.until(ExpectedConditions.urlContains(productUrlText));
		if(driver.getCurrentUrl().contains(productUrlText)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.fail();
		}
		driver.navigate().back();
	}

	// Common Method
	public WebElement getElement(By locator) {
		WebElement element = null;		
		{
			element = driver.findElement(locator);
		}

		return element;
	}

	// Common Method
	public boolean doIsDisplayed(By locator) {
		boolean flag = false;
		flag = getElement(locator).isDisplayed();
		return flag;
	}

	// Common Method
	public void doClick(By locator) 
	{
		getElement(locator).click();
	}

	// Scenario-7
	public void ValidateSignInBtnDisplayOrNOT() {

		scn.log("Checking the SignIn button is Avaliable on Landing page or Not");
		logger.info("Checking the SignIn button is Avaliable on Landing page or Not");

		Assert.assertEquals("Application Logo on Landing Page is not Displayed",true, doIsDisplayed(SighInBtnElement));

		scn.log("SignIn button is Avaliable and Successfully Validate");
		logger.info("SignIn button is Avaliable and Successfully Validate");
	}

	// Scenario-7
	public void ValidateSignInPageTitle(String SignInTitle) {

		wait =new WebDriverWait(driver, 20);	
		doClick(SighInBtnElement);

		scn.log("SignIn button is display and Successfully click on it");
		logger.info("SignIn button is display and Successfully click on it");

		wait.until(ExpectedConditions.titleIs(SignInTitle));
		String ActsignInTitle =driver.getTitle();		
		Assert.assertEquals("After click SignIn Title Not Match",SignInTitle, ActsignInTitle);

		scn.log("After click On SignIn Page Title Validate Successfully");
		logger.info("After click On SignIn Page Title Validate Successfully");
	}

	// Scenario-4
	public void ValidateLandingPageLogo() {

		scn.log("Checking the Landing page LOGO is Avaliable or Not");
		logger.info("Checking the Landing page LOGO is Avaliable or Not");

		Assert.assertEquals("Application Logo on Landing Page is not Displayed",true, doIsDisplayed(LanPageLogoElement));

		scn.log("Landing Page LOGO is Successfully Validate");
		logger.info("Landing Page LOGO is Successfully Validate");
	}

	// Common Method
	public String getAttributeValue( String AttributeName, WebDriver driver)
	{
		WebElement attributeValue = driver.findElement(LanPageLogoElement);
		return attributeValue.getAttribute(AttributeName);
	}

	// Scenario-5
	public void ValidateLandingPageLogoHeight() {

		scn.log("Checking the Landing page LOGO Height");
		logger.info("Checking the Landing page LOGO Height");

		String ActualLogoHeight=getAttributeValue("height", driver);
		String ExpLogoHeight   ="99";
		Assert.assertEquals("Apllication Logo height on Landing Page is not matching",ExpLogoHeight, ActualLogoHeight);

		scn.log("Landing Page LOGO Actual Height"+ ":"+ExpLogoHeight+" And Expected is"+ ":"+ ActualLogoHeight +" "+"is Match");
		logger.info("Landing Page LOGO Actual Height"+ ":"+ExpLogoHeight+" And Expected is"+ ":"+ ActualLogoHeight +" "+"is Match");
	}

	// Scenario-6
	public void ValidateLandingPageLogoWidth() {

		scn.log("Checking the Landing page LOGO Width");
		logger.info("Checking the Landing page LOGO Width");

		String ActualLogoWidth=getAttributeValue("width", driver);
		String ExpLogoWidth   ="350";
		Assert.assertEquals("Apllication Logo Width on Landing Page is not matching",ExpLogoWidth, ActualLogoWidth);

		scn.log("Landing Page LOGO Actual Width"+ ":"+ExpLogoWidth+" And Expected is"+ ":"+ ActualLogoWidth +" "+"is Match");
		logger.info("Landing Page LOGO Actual Width"+ ":"+ExpLogoWidth+" And Expected is"+ ":"+ ActualLogoWidth +" "+"is Match");
	}

	// Scenario-8
	public void ValidateSearchBox() {

		Assert.assertEquals("Search Box is not Displayed",true, doIsDisplayed(SearchBoxElement));		
		scn.log("Search Box is Displayed and validate Successfully");
		logger.info("Search Box is Displayed and validate Successfully");
	}

	// Scenario-8
	public void SearchForGivenProduct(String product) {

		getElement(SearchBoxElement).sendKeys(product);
		scn.log(product+":"+"is Search Product Name");
		logger.info(product+":"+"is Search Product Name");

	}

	// Scenario-8
	public void SearchProductList() {

		int exp=7;
		int act=0;
		wait =new WebDriverWait(driver, 20);
		List<WebElement> prolist=driver.findElements(SearchProdList);
		wait.until(ExpectedConditions.visibilityOfAllElements(prolist));
		for(int i=0; i<prolist.size(); i++) {

			act++;
			logger.info(i+1 +". "+ prolist.get(i).getText());
			scn.log(i+1 +". "+ prolist.get(i).getText());
			System.out.println((i+1)+" "+prolist.get(i).getText());			
		}
		Assert.assertEquals("Search product count does not match",exp, act);
		scn.log("After sendKey all product list we are getting is"+":"+act);
		logger.info("After sendKey all product list we are getting is"+":"+act);
	}

	// Scenario-8
	public void ValidateProductListCount(int number) {

		int ActaulMatchCount=0;
		String Searchproduct="Dress";
		wait =new WebDriverWait(driver, 20);
		List<WebElement> prolist=driver.findElements(SearchProdList);
		wait.until(ExpectedConditions.visibilityOfAllElements(prolist));
		for(int i=0; i<prolist.size(); i++) {

			if(prolist.get(i).getText().contains(Searchproduct)) {

				System.out.println((i+1)+" "+prolist.get(i).getText());
				logger.info(i+1 +". "+ prolist.get(i).getText());
        		scn.log(i+1 +". "+ prolist.get(i).getText());
				ActaulMatchCount++;
			}			
		}
		Assert.assertEquals("Search product count does not match",number, ActaulMatchCount);		
		scn.log("Search Product"+" "+Searchproduct+":"+"is match and actal count is"+":"+ActaulMatchCount);
		logger.info("Search Product"+" "+Searchproduct+":"+"is match and actal count is"+":"+ActaulMatchCount);
	}

	// Scenario-9
	public void ValidateTwitterLogoClickOnIt() {

		Assert.assertEquals("Twitter Logo Does Not Match",true, doIsDisplayed(TwitterLogoElement));
		wait =new WebDriverWait(driver, 20);
		scn.log("Twitter Logo Validate Successfully");
		logger.info("Twitter Logo Validate Successfully");

		getElement(TwitterLogoElement).click();

		Set<String> handles=driver.getWindowHandles();
		Iterator<String> it=handles.iterator();
		String parent=it.next();
		String child=it.next();
		driver.switchTo().window(child);

		String Expected="Selenium Framework (@seleniumfrmwrk) / Twitter";
		wait.until(ExpectedConditions.titleIs(Expected));
		String Actual=driver.getTitle();

		Assert.assertEquals("After Click on Twitter logo Title not Match",Expected,Actual );
		scn.log("Sucessfully Click on Twitter Logo Button");
		logger.info("Sucessfully Click on Twitter Logo Button");
	}

	// Scenario-9
	public void ValidateTwitterAccName(String ExpAccName) {

		String ActualAccountName=getElement(TwitterAccElement).getText();
		Assert.assertEquals("Twitter Account Name Does Not Match",ActualAccountName,ExpAccName );
		scn.log("Sucessfully Validate twitter Account Name Actual Result is :"+ActualAccountName);
		logger.info("Sucessfully Validate twitter Account Name Actual Result is :"+ActualAccountName);

	}

	// Common Method
	public String randomStringGenerator()
	{
		Random rand = new Random();
		String randomName = "";

		for (int i = 0; i < 10; i++) 
		{
			char characterRandom = (char)(rand.nextInt(26)+97);
			if (rand.nextBoolean()) {
				characterRandom = Character.toUpperCase(characterRandom);
				randomName = randomName + characterRandom;
			}	
		}	
		return randomName;
	}

	// Scenario-10
	public void ValidatenewslatterBoxAndClickOpraction() {
		String MailId="shubhamtathe44@gmail.com";
		Assert.assertEquals("News Latter Box Does not Display",true, doIsDisplayed(NewlatterBoxElment));
		getElement(NewlatterBoxElment).sendKeys(MailId);
		scn.log("News Latter Box Sucessfully validate");
		logger.info("News Latter Box Sucessfully validate");

		Assert.assertEquals("News Latter Button Does Not Click",true, doIsDisplayed(NewlatterBtnElment));
		doClick(NewlatterBtnElment);
		scn.log("News Latter Button Sucessfully validate");
		logger.info("News Latter Button Sucessfully validate");

	}

	// Scenario-10
	public void ValidateSuccessfullysubscribedMsg(String ExpMsg) {

		String failMSG= " Newsletter : This email address is already registered.";

		try 
		{
			WebElement SubscriSussMag= driver.findElement(SubscriMsgElement);
			SubscriSussMag.getText().contains(ExpMsg);
			logger.info("Validate Sucessfully message for subscription is: "+ SubscriSussMag.getText());
			scn.log("Validate Sucessfully message for subscription is: "+ SubscriSussMag.getText());
		} 
		catch(Exception e)
		{
			WebElement SubscriFailesdMag= driver.findElement(failSubscriMsgElement);
			SubscriFailesdMag.getText().equals(failMSG);
			logger.info("Validate Failed message for subscription is: "+SubscriFailesdMag.getText());
			scn.log("Validate Failed message for subscription is: "+SubscriFailesdMag.getText());
		}
	}
}



