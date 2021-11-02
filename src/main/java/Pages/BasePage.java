package Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import WebDriverManager.WebDriverManager;
import utils.WebDriverWaiter;


public class BasePage extends WebDriverWaiter {
	public BasePage() {
		PageFactory.initElements(WebDriverManager.getDriver(), this);
	}

	public BasePage clickElement(WebElement element) {
		element.click();
		return this;
	}

	public String getTextValue(WebElement text) {
		return text.getText();
	}
}
