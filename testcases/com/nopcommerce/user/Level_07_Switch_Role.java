package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjectsNopcommerce.Admin.AdminDashboardPageObject;
import pageObjectsNopcommerce.Admin.AdminLoginPageObject;
import pageObjectsNopcommerce.User.UserAddressPageObject;
import pageObjectsNopcommerce.User.UserCustomerInfoPageObject;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageObjectsNopcommerce.User.UserLoginPageObject;
import pageObjectsNopcommerce.User.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Role extends BaseTest{

	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, confirmPassword , emailAddressAdmin, passwordAdmin;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserRegisterPageObject userRegisterPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashbordPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		firstName = "afc";
		lastName = "auto";
		password = "123456";
		confirmPassword = "123456";
		emailAddress = "lisatran" + getRandomNumber() + "@gmail.com";
		emailAddressAdmin = "admin@yourstore.com";
		passwordAdmin = "admin";
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void Role_01_User_To_Admin() {
		userRegisterPage = userHomePage.clickToRegisterLink();
		userHomePage = userRegisterPage.userRegister(firstName, lastName, emailAddress, password, confirmPassword);
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(emailAddress, password);
		Assert.assertTrue(userHomePage.isLogoutLinkDisplay());
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		userHomePage.openPageURL(driver, GlobalConstants.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashbordPage = adminLoginPage.loginAsAdmin(emailAddressAdmin, passwordAdmin);
		Assert.assertTrue(adminDashbordPage.isDashboardHederDisplay());
		adminLoginPage = adminDashbordPage.clickToLogoutLinkAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageURL(driver, GlobalConstants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(emailAddress, password);
		Assert.assertTrue(userHomePage.isLogoutLinkDisplay());
	}



	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
