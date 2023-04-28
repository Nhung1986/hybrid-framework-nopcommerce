package pageObjectsJqueryDatatable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.JqueryDataTable.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void openPagingByPageNumber(String numberPage) {
		waitForElementClickable(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, numberPage);
		clickToElement(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, numberPage);
	}
	public void enterToHeaderTextBoxByLableName(String textValues, String lableName) {
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_LABLE_NAME, lableName);
		senkeyToElement(driver, HomePageUI.TEXTBOX_BY_LABLE_NAME, textValues, lableName);
		pressKeyToElement(driver, HomePageUI.TEXTBOX_BY_LABLE_NAME, Keys.ENTER, lableName);
	}
	public boolean isAcvtivePaging(String numberPage) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_ACTIVE_BY_PAGE_NUMBER, numberPage);
		return isElementDisplayed(driver, HomePageUI.PAGINATION_ACTIVE_BY_PAGE_NUMBER, numberPage);
	}
	
	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGE);
		System.out.println("Total page:  " + totalPage);
		List<String> allRowValues = new ArrayList<String>(); 
		for (int index = 1; index <= totalPage; index++) {
			waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_COUNTRY_ONE_PAGE);
			for (WebElement rowElement : allRowElementEachPage) {
				allRowValues.add(rowElement.getText());
			}
		}
		for (String string : allRowValues) {
			System.out.println(string);
		}
		return allRowValues;
		
	}

}
