package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageObjectsNopcommerce.User.UserLoginPageObject;
import pageObjectsNopcommerce.User.UserRegisterPageObject;

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

public class Level_05_Page_Generator_Manager extends BaseTest{

	private WebDriver driver;
	private String validEmail, firstName, lastName, password, invalidEmail, notFoundEmail, invalidPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		firstName = "nhung";
		lastName = "tran";
		password = "123456";
		invalidPassword = "654123";
		validEmail = "lisatran" + getRandomNumber() + "@gmail.com";
		invalidEmail = "dfffh@fffh@";
		notFoundEmail = "lisatran" + getRandomNumber() + "@gmail.vn";
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessMessageAtRegister(), "Your registration completed");
		registerPage.clickToRegisterContinueButton();
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickToLoginLink();
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailAddressTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailAddressTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailNotFound(), "Login was unsuccessful. Please correct the errors and try again."+ "\n"+ "No customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageEmailExistingEmptyPassword(), "Login was unsuccessful. Please correct the errors and try again."+ "\n"+ "The credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(invalidPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageEmailExistingIncorrectPassword(), "Login was unsuccessful. Please correct the errors and try again."+ "\n"+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_06_Existing_Email_Correct_Password() {
		loginPage = homePage.clickToLoginLink();
	    loginPage.inputToEmailTextbox(validEmail);
	    loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
		Assert.assertTrue(homePage.isLogoutLinkDisplay());
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
