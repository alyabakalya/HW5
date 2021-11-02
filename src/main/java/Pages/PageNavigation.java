package Pages;

import org.openqa.selenium.WebDriver;

public class PageNavigation extends BasePage{
	public static WebDriver openPage(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
		return driver;
	}
}
