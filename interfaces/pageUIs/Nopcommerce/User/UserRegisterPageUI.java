package pageUIs.Nopcommerce.User;

public class UserRegisterPageUI {
	public static final String FIRSTNAME_ERROR_MESSAGE = "xpath=//span[@id='FirstName-error']"; 
	public static final String LASTNAME_ERROR_MESSAGE = "xpath=//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "xpath=//span[@id='Password-error']";
	public static final String CONFIRMPASSWORD_ERROR_MESSAGE = "xpath=//span[@id='ConfirmPassword-error']";
	public static final String REGISTER_BUTTON = "xpath=//button[@id='register-button']";
	public static final String FIRSTNAME_TEXTBOX = "xpath=//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "xpath=//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "id=Email";
	public static final String PASSWORD_TEXTBOX = "id=Password";
	public static final String CONFIRMPASSWORD_TEXTBOX = "xpath=//input[@id='ConfirmPassword']";
	public static final String REGISTER_SUCCESS_MESSAGE = "xpath=//div[@class ='result']";
	public static final String EMAIL_EXITING_MESSAGE = "xpath=//div[contains(@class,'message-error')]//li";
	public static final String PASSWORD_NOT_MATCH_MESSAGE = "xpath=//span[@id='ConfirmPassword-error']";
	public static final String REGISTER_CONTINUE_BUTTON = "xpath=//a[contains(@class,'register-continue-button')]";
}
