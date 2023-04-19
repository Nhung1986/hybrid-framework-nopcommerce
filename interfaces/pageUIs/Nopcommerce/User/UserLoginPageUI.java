package pageUIs.Nopcommerce.User;

public class UserLoginPageUI {
	public static final String LOGIN_BUTTON = "//button[contains(@class,'login-button')]";
	
	public static final String EMAIL_INVALID_ERROR_MESSAGE = "//span[@id='Email-error']";
	public static final String EMAIL_NOT_FOUND_ERROR_MESSAGE = "//div[contains(@class,'validation-summary-errors')]";
	public static final String EMAIL_EXISTING_EMPTY_PASWORD_ERROR_MESSAGE = "//div[contains(@class,'validation-summary-errors')]";
	public static final String EMAIL_EXISTING_INCORRECT_PASWORD_ERROR_MESSAGE = "//div[contains(@class,'validation-summary-errors')]";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String REGISTER_CONTINUE_BUTTON = "//a[contains(@class,'register-continue-button')]";
}
