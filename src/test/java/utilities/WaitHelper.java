package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	public WebDriver ldriver;

	public WaitHelper(WebDriver driver) {
		ldriver = driver;
		System.out.println(ldriver);
	}

	public void waitforElement(WebElement element, long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(ldriver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
}
