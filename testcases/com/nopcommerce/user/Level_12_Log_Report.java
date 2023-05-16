package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjectsNopcommerce.User.UserAddressPageObject;
import pageObjectsNopcommerce.User.UserCustomerInfoPageObject;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageObjectsNopcommerce.User.UserLoginPageObject;
import pageObjectsNopcommerce.User.UserMyProductReviewsPageObject;
import pageObjectsNopcommerce.User.UserOrdersPageObject;
import pageObjectsNopcommerce.User.UserRegisterPageObject;
import pageObjectsNopcommerce.User.UserRewardPointsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.sql.Driver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Log_Report extends BaseTest{

	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserAddressPageObject addressPage;
	private UserOrdersPageObject ordersPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
	private UserRewardPointsPageObject rewardPointsPage;
	private UserCustomerInfoPageObject customerInfoPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		firstName = "nhung";
		lastName = "tran";
		password = "123456";
		emailAddress = "lisatran" + getRandomNumber() + "@gmail.com";
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01 - Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 02 - Input value to firstname textbox " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);
		
		log.info("Register - Step 03 - Input value to lastname textbox " + lastName);
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("Register - Step 04 - Input value to emailAddress textbox " + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - Step 05 - Input value to password textbox " + password);
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - Step 06 - Input value to confirm password textbox " + password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 07 - Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 09 - Verify message success");
		verifyEquals(registerPage.getSuccessMessageAtRegister(), "fail Your registration completed");
	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01 - Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 02 - Input value to Email textbox " + emailAddress);
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Login - Step 03 - Input value to password textbox " + password);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04 - Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login - Step 05 - Verify MyAccount link display");
		verifyTrue(homePage.isMyAccountLinkDisplay());
		
		log.info("Login - Step 06 - Verify Logout link display");
		verifyFailed(homePage.isLogoutLinkDisplay());
	}

	//@Test
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		verifyTrue(customerInfoPage.isHeaderTextDisplay());
	}

	//@Test
	public void User_04_Switch_Page() {
		customerInfoPage.openPageAtMyAccoutByName(driver, "customer-addresses");
		addressPage = PageGeneratorManager.getAddressPage(driver);
		addressPage.openPageAtMyAccoutByName(driver, "customer-reviews");
		myProductReviewsPage = PageGeneratorManager.getMyProductReviewsPage(driver);
		myProductReviewsPage.openPageAtMyAccoutByName(driver, "customer-addresses");
		addressPage = PageGeneratorManager.getAddressPage(driver);
		addressPage.openPageAtMyAccoutByName(driver, "reward-points");
		rewardPointsPage = PageGeneratorManager.getRewardPointsPage(driver);
		rewardPointsPage.openPageAtMyAccoutByName(driver, "customer-reviews");
		myProductReviewsPage = PageGeneratorManager.getMyProductReviewsPage(driver);
		myProductReviewsPage.openPageAtMyAccoutByName(driver, "customer-info");
		customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
		customerInfoPage.openPageAtMyAccoutByName(driver, "reward-points");
		rewardPointsPage = PageGeneratorManager.getRewardPointsPage(driver);
		rewardPointsPage.openPageAtMyAccoutByName(driver, "customer-orders");
		ordersPage = PageGeneratorManager.getOrdersPage(driver);
		ordersPage.openPageAtMyAccoutByName(driver, "reward-points");
		rewardPointsPage = PageGeneratorManager.getRewardPointsPage(driver);

	}

	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
