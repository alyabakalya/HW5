package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.WebDriverWaiter;


public class BasketPage extends BasePage {

	@FindBy(xpath = "//div[@class = 'product-summary__description-name']")
	WebElement productForValidation;

	@FindBy(xpath = "//button[@id = 'onetrust-accept-btn-handler']")
	WebElement acceptCookiesButton;

	@FindBy(xpath = "//span[@class = 'button__text '][text() = 'Naar Kruidvat.nl']")
	WebElement continueForTheNetherlandsButton;

	public String getTextOfProductDescription() {
		waitForElement(productForValidation, 10);
		return getTextValue(productForValidation);
	}

	public void clickOnAcceptCookiesButton() {
		waitForElement(acceptCookiesButton, 10);
		clickElement(acceptCookiesButton);
	}

	public void clickOnContinueButton() {
		clickElement(continueForTheNetherlandsButton);
	}
}
