package com.jquery.datatable;

import org.testng.annotations.Test;


import commons.BaseTest;
import pageObjectsJqueryDatatable.HomePageObject;
import pageObjectsJqueryDatatable.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Datatable_DataGrid extends BaseTest{

	private WebDriver driver;
	private HomePageObject homePage;
	List<String> allCountryValues;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	
	}

	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		Assert.assertTrue(homePage.isAcvtivePaging("10"));
		homePage.openPagingByPageNumber("1");
		Assert.assertTrue(homePage.isAcvtivePaging("1"));
		homePage.openPagingByPageNumber("5");
		Assert.assertTrue(homePage.isAcvtivePaging("5"));
		homePage.openPagingByPageNumber("15");
		Assert.assertTrue(homePage.isAcvtivePaging("15"));
		homePage.openPagingByPageNumber("20");
		Assert.assertTrue(homePage.isAcvtivePaging("20"));
	}

	public void Table_02_Enter_To_Header() {
		homePage.refeshCurrentPage(driver);
		homePage.enterToHeaderTextBoxByLableName("750", "Females");
		homePage.enterToHeaderTextBoxByLableName("Aruba", "Country");
	}

	@Test
	public void Table_03_Enter_To_Header() {
		allCountryValues = homePage.getValueEachRowAtAllPage();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
