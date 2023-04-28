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

public class Level_08_Dynamic_Xpath extends BaseTest{

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
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessMessageAtRegister(), "Your registration completed");
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
		Assert.assertTrue(homePage.isLogoutLinkDisplay());
	}

	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isHeaderTextDisplay());
	}

	@Test
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
