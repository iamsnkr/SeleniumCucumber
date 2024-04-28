package step_definitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import nopcommerce.pages.AddCustomerPage;
import nopcommerce.pages.LoginPage;
import nopcommerce.pages.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() {
		String br = get_browser().getString("browser");
		logger.atLevel(Level.DEBUG);
		logger.info("************* Launching Browser *****************");
		// Launching browser
		if (br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("************* Launching Mozilla Browser *****************");
		}

		else if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("************* Launching Chrome Browser *****************");
		}

		else if (br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("************* Launching Edge Browser *****************");
		}

	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		lp = new LoginPage(driver);
		logger.info("************* Launched Browser *****************");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(url);

		logger.info("************* opened url *****************");

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("************* enters Email and Password *****************");
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("************* Click on Login *****************");
		lp.clickLogin();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String pageTitle) {
		logger.info("************* verify page_title *****************");
		if (driver.getPageSource().contains("Login was")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(pageTitle, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("************* click on Log out link *****************");
		lp.clickLogout();
		Thread.sleep(5000);
	}

	@Then("close browser")
	public void close_browser() {

		System.out.print("Driver Execution is done");
		driver.close();

		logger.info("************* close_browser *****************");
	}

	// Customers ==================================================

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		logger.info("************* navigate to view Dashboad *****************");
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		logger.info("************* click_on_customers_menu *****************");
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		logger.info("************* click_on_customers_menu_item *****************");
		Thread.sleep(3000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		logger.info("************* click_on_add_new_button *****************");
		addCust.clickOnAddnew();
		Thread.sleep(3000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		logger.info("************* view_add_new_customer_page *****************");
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("************* enter customer info *****************");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Kumar");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");

	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("************* click_on_save_button *****************");
		addCust.clickOnSave();
		Thread.sleep(2000);

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		logger.info("************* view_confirmation_message *****************");
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));

	}

	// Search Customers +++++++++++++++++++++++++++++++++++

	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		logger.info("************* enter_customer_e_mail *****************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		logger.info("************* click_on_search_button *****************");
		searchCust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {

		logger.info("************* verify_email_in_the_search_table *****************");
		boolean flag = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");

		Assert.assertEquals(true, flag);

	}

	// steps for searching a customer by Name................
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("********* Searching customer details by Name **************");
		logger.info("********* enter_customer_FirstName **************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		logger.info("********* enter_customer_LastName **************");
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		logger.info("************* verify_Name_in_the_Search_table *****************");
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

}
