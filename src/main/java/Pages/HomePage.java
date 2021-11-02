package Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	@FindBy(xpath = "//i[@class='icon icon-basket']")
	WebElement basketButton;

	public void clickOnBasketButton() {
		clickElement(basketButton);
	}
}
