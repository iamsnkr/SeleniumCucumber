package step_definitions;

import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import nopcommerce.pages.AddCustomerPage;
import nopcommerce.pages.LoginPage;
import nopcommerce.pages.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	protected final static Logger logger = LogManager.getLogger(BaseClass.class);

	public static String randomString() {

		return RandomStringUtils.randomAlphabetic(5);

	}

	public static ResourceBundle get_browser() {
		ResourceBundle browser = ResourceBundle.getBundle("config"); // Loads config.properties file
		return browser;

	}

}
