package pom.cucumberBDD.framework.stepdefs;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pom.cucumberBDD.framework.core.WebDriverFactory;
import pom.cucumberBDD.framework.stepdefs.StepDefs;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.cucumberBDD.framework.pageobjects.LandingPageObjects;

public class StepDefs {


	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	WebDriver driver;
	String base_url = "http://automationpractice.com/";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn; 

	LandingPageObjects landingPageObjects;

	// *********** Cucumber Hooks Start ******************** //

	@Before
	public void setUp(Scenario scn) throws Exception{
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");

		landingPageObjects = new LandingPageObjects(driver, scn);

	}

	@After(order=1)
	public void cleanUp(){

		WebDriverFactory.quitDriver();	
	}

	@After(order=2)
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	// *********** Cucumber Hooks ends ******************** //

	// Background - Keyword form feature file

	@Given("User navigated to the landing page url")
	public void user_navigated_to_the_landing_page_url() {

		WebDriverFactory.navigateToTheUrl(base_url);
		scn.log("Browser navigated to URL: " + base_url);

	}

	// *************** First Scenario step defs start **************** //

	@Given("User able to see the landing page")
	public void user_able_to_see_the_landing_page() {

		landingPageObjects.VarifyThePage();
	}

	@Given("Landing page must be contain {string}")
	public void landing_page_must_be_contain(String URL) {

		landingPageObjects.URLValidation(URL);
	}

	// *************** First Scenario step defs end **************** //

	// *************** Second Scenario step defs start **************** //

	@Given("Landing page title must be {string}")
	public void landing_page_title_must_be(String pageTitle) {

		landingPageObjects.validateLandingPageTitle(pageTitle);
	}

	// *************** Second Scenario step defs end **************** //

	// *************** Third Scenario step defs start **************** //

	@When("Available product category count is {int}")
	public void available_product_category_count_is(Integer ProductCount) {

		landingPageObjects.ValidateProductListByCount(ProductCount);
	}


	@Then("User able to see the productLinkText and verify the associated productLinkUrlText")
	public void user_able_to_see_the_product_link_text_and_verify_the_associated_product_link_url_text(List<Map<String,String>> proInfo) {

		landingPageObjects.ValidateProductListBYName(proInfo);
	}

	// *************** Third Scenario step defs end **************** //

	// *************** Forth Scenario step defs start **************** //

	@When("User on landing page or home page")
	public void user_on_landing_page_or_home_page() {

		landingPageObjects.VarifyThePage();
	}

	@Then("User must able to see the landing page logo")
	public void user_must_able_to_see_the_landing_page_logo() {

		landingPageObjects.ValidateLandingPageLogo();
	}

	// *************** Forth Scenario step defs end **************** //

	// *************** Fifth Scenario step defs start **************** //

	@When("User able to see the logo on landing page")
	public void user_able_to_see_the_logo_on_landing_page() {

		landingPageObjects.ValidateLandingPageLogo();
	}

	@When("Landing page logo height must be match")
	public void landing_page_logo_height_must_be_match() {

		landingPageObjects.ValidateLandingPageLogoHeight();
	}

	// *************** Fifth Scenario step defs end **************** //

	// *************** Six Scenario step defs start **************** //

	@Then("Landing page logo width must be match")
	public void landing_page_logo_width_must_be_match() {

		landingPageObjects.ValidateLandingPageLogoWidth();
	}

	// *************** Six Scenario step defs end **************** //

	// *************** Seventh Scenario step defs start **************** //

	@Given("User Able to See the SignIn button on landing page")
	public void user_able_to_see_the_sign_in_button_on_landing_page() {

		landingPageObjects.ValidateSignInBtnDisplayOrNOT();
	}

	@When("User click on SignIn Btn next page title must be {string}")
	public void user_click_on_sign_in_btn_next_page_title_must_be(String SignInPageTitle) {

		landingPageObjects.ValidateSignInPageTitle(SignInPageTitle);
	}

	// *************** Seventh Scenario step defs end **************** //

	// *************** Eight Scenario step defs start **************** //


	@Given("User able to see the search box")
	public void user_able_to_see_the_search_box() {
	   
		landingPageObjects.ValidateSearchBox();
	}

	@When("User search for product {string}")
	public void user_search_for_product(String ProductName) {
		
		landingPageObjects.SearchForGivenProduct(ProductName);
	}
	
	@Then("User able to see the list of available products")
	public void user_able_to_see_the_list_of_available_products() {
		
		landingPageObjects.SearchProductList();
	
	}
	
	@Then("Search product match count must be {int}")
	public void search_product_match_count_must_be(Integer MatchProCount) {
	    
		landingPageObjects.ValidateProductListCount(MatchProCount);
	
	}

	// *************** Eight Scenario step defs end **************** //
	
	// *************** Nine Scenario step defs start **************** //


	@When("User see the twitter logo and click on it")
	public void user_see_the_twitter_logo_and_click_on_it() {
	    
		
		landingPageObjects.ValidateTwitterLogoClickOnIt();
	}


	@Then("Twitter account name must be match {string}")
	public void twitter_account_name_must_be_match(String AccountName) {
	    
		landingPageObjects.ValidateTwitterAccName(AccountName);
	}


	// *************** Nine Scenario step defs end **************** //
	
	// *************** Ten Scenario step defs start **************** //


	@When("User enter mailID in newslatter box and click on button")
	public void user_enter_mail_id_in_newslatter_box_and_click_on_button() {
	   
		landingPageObjects.ValidatenewslatterBoxAndClickOpraction();
	}

	
	@Then("User get the {string} massage")
	public void user_get_the_massage(String Massage) {
	    
		landingPageObjects.ValidateSuccessfullysubscribedMsg(Massage);
	}
	
	// *************** Ten Scenario step defs end **************** //



}
