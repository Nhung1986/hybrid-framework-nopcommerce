package pageObjectsNopcommerce.Admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.Nopcommerce.Admin.AdminDashboardPageUI;
import pageUIs.Nopcommerce.User.UserHomePageUI;


public class AdminDashboardPageObject extends BasePage{
	private WebDriver driver;
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHederDisplay() {
		waitForElementClickable(driver, AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
	}
	
}
