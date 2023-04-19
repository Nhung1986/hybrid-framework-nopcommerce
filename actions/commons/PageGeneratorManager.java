package commons;

import org.openqa.selenium.WebDriver;

import pageObjectsNopcommerce.Admin.AdminDashboardPageObject;
import pageObjectsNopcommerce.Admin.AdminLoginPageObject;
import pageObjectsNopcommerce.User.UserAddressPageObject;
import pageObjectsNopcommerce.User.UserCustomerInfoPageObject;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageObjectsNopcommerce.User.UserLoginPageObject;
import pageObjectsNopcommerce.User.UserMyProductReviewsPageObject;
import pageObjectsNopcommerce.User.UserOrdersPageObject;
import pageObjectsNopcommerce.User.UserRegisterPageObject;
import pageObjectsNopcommerce.User.UserRewardPointsPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	
	public static UserAddressPageObject getAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	
	public static UserOrdersPageObject getOrdersPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}
	
	public static UserRewardPointsPageObject getRewardPointsPage(WebDriver driver) {
		return new UserRewardPointsPageObject(driver);
	}
	
	public static UserMyProductReviewsPageObject getMyProductReviewsPage(WebDriver driver) {
		return new UserMyProductReviewsPageObject(driver);
	}
	
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

}
