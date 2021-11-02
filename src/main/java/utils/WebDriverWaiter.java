package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import WebDriverManager.WebDriverManager;


public class WebDriverWaiter {

	public WebDriverWaiter waitForElement(WebElement element, long seconds){
		getWebDriverWait(seconds).until(elementIsDisplayed(element));
		return this;
	}

	private WebDriverWait getWebDriverWait(long seconds) {
		return new WebDriverWait(WebDriverManager.getDriver(), seconds);
	}

	private ExpectedCondition<Boolean> elementIsDisplayed(WebElement element) {
		return driver -> element.isDisplayed();
	}

}