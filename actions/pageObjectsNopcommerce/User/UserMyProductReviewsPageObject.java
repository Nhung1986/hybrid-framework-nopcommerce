package pageObjectsNopcommerce.User;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.Nopcommerce.User.UserCustomerInfoPageUI;
import pageUIs.Nopcommerce.User.UserHomePageUI;


public class UserMyProductReviewsPageObject extends BasePage{
	private WebDriver driver;
	public UserMyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
