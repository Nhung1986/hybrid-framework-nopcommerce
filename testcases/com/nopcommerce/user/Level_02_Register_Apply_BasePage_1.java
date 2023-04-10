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

public class Level_02_Register_Apply_BasePage_1 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress ; 
	BasePage basePage;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		basePage = new BasePage();
		emailAddress = "lisatran" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		basePage.senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		basePage.senkeyToElement(driver, "//input[@id='Email']", "123456@");
		basePage.senkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		basePage.senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		basePage.senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.senkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		
		Assert.assertEquals(basePage.getTextElement(driver, "//div[@class ='result']"), "Your registration completed");
		basePage.waitForElementClickable(driver, "//a[contains(@class,'register-continue-button')]");
		basePage.clickToElement(driver, "//a[contains(@class,'register-continue-button')]");
	
	}

	@Test
	public void TC_04_Register_Email_Already_exist() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		basePage.senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		basePage.senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.senkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		Assert.assertEquals(basePage.getTextElement(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
		
		
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		basePage.senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		basePage.senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.senkeyToElement(driver, "//input[@id='Password']", "1234");
		basePage.senkeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"), "Password must meet the following rules:" + "\n"+ "must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_ConfirmPassword_Not_Match() {
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.senkeyToElement(driver, "//input[@id='FirstName']", "Lisa");
		basePage.senkeyToElement(driver, "//input[@id='LastName']", "Tran");
		basePage.senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.senkeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123465");
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");	
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
	}

}
