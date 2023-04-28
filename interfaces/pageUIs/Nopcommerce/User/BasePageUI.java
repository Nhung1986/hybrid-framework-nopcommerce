package pageUIs.Nopcommerce.User;

public class BasePageUI {
	public static final String ADDRESS_LINK = "xpath=//li[contains(@class, 'customer-addresses')]/a";
	public static final String ORDERS_LINK = "xpath=//li[contains(@class, 'customer-orders')]/a";
	public static final String MYPRODUCTREVIWS_LINK = "xpath=//li[contains(@class, 'customer-reviews')]/a";
	public static final String REWARDPOINTS_LINK = "xpath=//li[contains(@class, 'reward-points')]/a";
	public static final String CUSTOMERINFO_LINK = "xpath=//li[contains(@class, 'customer-info')]/a";
	public static final String DYMANIC_PAGE__AT_MY_ACC_PAGE = "xpath=//li[contains(@class, '%s')]/a";	
	public static final String LOGOUT_USER_LINK = "css=a[class='ico-logout']";
	public static final String LOGOUT_ADMIN_LINK = "xpath=//a[text()='Logout']";
}
