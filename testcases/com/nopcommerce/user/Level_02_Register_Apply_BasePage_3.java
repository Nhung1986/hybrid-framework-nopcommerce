package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.sql.Driver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Register_Apply_BasePage_3 extends BasePage {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		emailAddress = "lisatran" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getTextElement(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getTextElement(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getTextElement(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getTextElement(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getTextElement(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		senkeyToElement(driver, "//input[@id='Email']", "123456@");
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getTextElement(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getTextElement(driver, "//div[@class ='result']"), "Your registration completed");
		waitForElementClickable(driver, "//a[contains(@class,'register-continue-button')]");
		clickToElement(driver, "//a[contains(@class,'register-continue-button')]");

	}

	@Test
	public void TC_04_Register_Email_Already_exist() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getTextElement(driver, "//div[contains(@class,'message-error')]//li"),
				"The specified email already exists");

	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "1234");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getTextElement(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:" + "\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_ConfirmPassword_Not_Match() {

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123465");
		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getTextElement(driver, "//span[@id='ConfirmPassword-error']"),
				"The password and confirmation password do not match.");
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
