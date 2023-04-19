package pageObjectsNopcommerce.Admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageUIs.Nopcommerce.Admin.AdminLoginPageUI;
import pageUIs.Nopcommerce.User.UserLoginPageUI;
import pageUIs.Nopcommerce.User.UserRegisterPageUI;

public class AdminLoginPageObject extends BasePage{

	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
		
	}
	public String getErrorMessageAtEmailAddressTextbox() {
		waitForElementClickable(driver, UserLoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String emailAddress) {
		
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	
	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();	
	}
}
