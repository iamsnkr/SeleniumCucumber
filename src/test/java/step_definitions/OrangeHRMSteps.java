package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMSteps {
    WebDriver driver;
    
	@Given("I launch chrome browser")
	public void i_launch_chrome_browser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}

	@When("I open orange hrm homepage")
	public void i_open_orange_hrm_homepage() {
		
		driver.get("https://www.orangehrm.com/");
		
	}

	@Then("I verify that the logo present on page")
	public void i_verify_that_the_logo_present_on_page() {
	 boolean logoDisplayStatus = driver.findElement(By.xpath("//img[@src='/_resources/themes/orangehrm/dist/images/OrangeHRM_Logo.svg']")).isDisplayed();
	 Assert.assertEquals(true, logoDisplayStatus);
	
	}

	@Then("close_browser")
	public void close_browser() {
		
		System.out.print("Driver Execution is done");
		driver.close();
	}

}
