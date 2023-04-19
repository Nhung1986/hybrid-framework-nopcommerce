package pageObjectsNopcommerce.User;

import java.security.PublicKey;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.Nopcommerce.User.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
		senkeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);

	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
		senkeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);

	}

	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAdress);

	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);

	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		senkeyToElement(driver, UserRegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassword);

	}

	public String getSuccessMessageAtRegister() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToRegisterContinueButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_CONTINUE_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_CONTINUE_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getErrorMessageEmailExiting() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_EXITING_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.EMAIL_EXITING_MESSAGE);
	}
	
	public UserHomePageObject userRegister(String firstName, String lastName, String emailAdress, String password, String confirmPassword) {
		inputToFirstNameTextbox(firstName);
		inputToLastNameTextbox(lastName);
		inputToEmailTextbox(emailAdress);
		inputToPasswordTextbox(confirmPassword);
		inputToConfirmPasswordTextbox(confirmPassword);
		return clickToRegisterButton();
	}

}
