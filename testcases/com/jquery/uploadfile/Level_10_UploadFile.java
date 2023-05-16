package com.jquery.uploadfile;

import org.testng.annotations.Test;


import commons.BaseTest;
import pageObjectsJqueryUploadFile.HomePageObject;
import pageObjectsJqueryUploadFile.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_UploadFile extends BaseTest{

	private WebDriver driver;
	private HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	
	}

	
	public void Upload_01_OneFile_Per_Time() {
		String fileName = "1.jpg";
		homePage.uploadMultipleFiles(driver, fileName);
		Assert.assertTrue(homePage.isLoadedFileNameSucess(fileName));
		homePage.clickStartButton();
		Assert.assertTrue(homePage.isUploadedFileNameSucess(fileName));
		Assert.assertTrue(homePage.isUpLoadedImageSucess(fileName));
	}
	
	@Test
	public void Upload_02_MutiplesFile_Per_Time() {
		String[] fileNames = {"1.jpg", "2.jpg", "3.jpg", "4.jpg"};
		homePage.uploadMultipleFiles(driver, fileNames);
		Assert.assertTrue(homePage.isLoadedFileNameSucess("1.jpg"));
		Assert.assertTrue(homePage.isLoadedFileNameSucess("2.jpg"));
		Assert.assertTrue(homePage.isLoadedFileNameSucess("3.jpg"));
		Assert.assertTrue(homePage.isLoadedFileNameSucess("4.jpg"));
		homePage.clickStartButton();
		Assert.assertTrue(homePage.isUploadedFileNameSucess("1.jpg"));
		Assert.assertTrue(homePage.isUploadedFileNameSucess("2.jpg"));
		Assert.assertTrue(homePage.isUploadedFileNameSucess("3.jpg"));
		Assert.assertTrue(homePage.isUploadedFileNameSucess("4.jpg"));
		Assert.assertTrue(homePage.isUpLoadedImageSucess("1.jpg"));
		Assert.assertTrue(homePage.isUpLoadedImageSucess("2.jpg"));
		Assert.assertTrue(homePage.isUpLoadedImageSucess("3.jpg"));
		Assert.assertTrue(homePage.isUpLoadedImageSucess("4.jpg"));
	}

	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
