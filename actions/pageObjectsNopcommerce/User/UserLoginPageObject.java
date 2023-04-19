package pageObjectsNopcommerce.User;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.Nopcommerce.User.UserLoginPageUI;
import pageUIs.Nopcommerce.User.UserRegisterPageUI;

public class UserLoginPageObject extends BasePage{

	private WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtEmailAddressTextbox() {
		waitForElementClickable(driver, UserLoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String emailAddress) {
		
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public String getErrorMessageAtEmailNotFound() {
		waitForElementClickable(driver, UserLoginPageUI.EMAIL_NOT_FOUND_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_NOT_FOUND_ERROR_MESSAGE);
	}

	public String getErrorMessageEmailExistingEmptyPassword() {
		waitForElementClickable(driver, UserLoginPageUI.EMAIL_EXISTING_EMPTY_PASWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_EXISTING_EMPTY_PASWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageEmailExistingIncorrectPassword() {
		waitForElementClickable(driver, UserLoginPageUI.EMAIL_EXISTING_INCORRECT_PASWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_EXISTING_INCORRECT_PASWORD_ERROR_MESSAGE);
	}
	
	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
		
	}
}
