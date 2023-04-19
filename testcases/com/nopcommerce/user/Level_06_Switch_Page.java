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

public class Level_06_Switch_Page extends BaseTest{

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
		addressPage = customerInfoPage.openAddressPage(driver);
		myProductReviewsPage = addressPage.openMyProductReviewsPage(driver);
		addressPage = myProductReviewsPage.openAddressPage(driver);
		rewardPointsPage = addressPage.openRewardPointsPage(driver);
		myProductReviewsPage = rewardPointsPage.openMyProductReviewsPage(driver);
		customerInfoPage = myProductReviewsPage.openCustomerInfoPage(driver);
		rewardPointsPage = customerInfoPage.openRewardPointsPage(driver);
		ordersPage = rewardPointsPage.openOrdersPage(driver);
		rewardPointsPage = ordersPage.openRewardPointsPage(driver);

	}

	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
