package pageObjectsNopcommerce.User;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.Nopcommerce.User.UserCustomerInfoPageUI;
import pageUIs.Nopcommerce.User.UserHomePageUI;


public class UserCustomerInfoPageObject extends BasePage{
	private WebDriver driver;
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isHeaderTextDisplay() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.CUSTOMERINFO_HEADER);
		return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMERINFO_HEADER);
	}
}
