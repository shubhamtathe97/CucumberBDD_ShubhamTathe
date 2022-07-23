@endtoend
Feature: User can iteract with the different functionality of application

  Background: Navigation to the URL
  Given       User navigated to the landing page url

  @one
  Scenario: URL Redirection Test
  Given     User able to see the landing page 
  And       Landing page must be contain "http://automationpractice.com/index.php"
    
  @two  
  Scenario: Landing Page Title Tset
  Given     Landing page title must be "My Store"
    
  @three 
  Scenario: Product Category Validation Test
  When      Available product category count is 3
  Then      User able to see the productLinkText and verify the associated productLinkUrlText
    | productLinkText | productLinkUrlText|
    | Women    	      | id_category=3 		|
    | Dresses         | id_category=8 	  |
    | T-shirts 		    | id_category=5 		|
    
    
  @four
  Scenario: Landing Page Application Logo Display Test
  When      User on landing page or home page
  Then      User must able to see the landing page logo
  
  @five
  Scenario: Validate Application Logo Height On Landing Page
  When      User able to see the logo on landing page
  And       Landing page logo height must be match
  
  @six
  Scenario: Validate Application Logo Width On Landing Page
  When      User able to see the logo on landing page
  Then      Landing page logo width must be match
  
  @seven
  Scenario: SignIN Page Title Validation Test
  Given     User able to see the landing page 
  And       User Able to See the SignIn button on landing page
  When      User click on SignIn Btn next page title must be "Login - My Store"
  
  @eight
  Scenario: Search product By Any Name and Validate How Many Product Matches With Name
  Given     User able to see the search box
  When      User search for product "Dress"
  Then      User able to see the list of available products
  And       Search product match count must be 5
  
  @nine
  Scenario: Validate Twitter Social Media Handle
  When      User see the twitter logo and click on it
  Then      Twitter account name must be match "Selenium Framework"
  
  @ten
  Scenario: Newslatter Subscription 
  When      User enter mailID in newslatter box and click on button
  Then      User get the "successfully subscribed" massage
  
  
  
  
  
  
  
  
  
  