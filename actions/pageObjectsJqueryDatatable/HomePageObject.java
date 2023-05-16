package pageObjectsJqueryDatatable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.JqueryDataTable.HomePageUI;
import pageUIs.Jquery.UploadFile.HomePageUploadUI;

public class HomePageObject extends BasePage {
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

	public void enterToTextBoxAtRowByColumnName(String columnName, String rowNumber, String value) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));
		senkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowNumber,
				String.valueOf(columnIndex));
	}

	public void selectDropDownAtRowByColumnName(String columnName, String rowNumber, String value) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowNumber,
				String.valueOf(columnIndex));

	}

	public void checkToCheckBoxAtRowByColumnName(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));
		checkToDefaultCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));

	}

	public void uncheckToCheckBoxAtRowByColumnName(String columnName, String rowNumber) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));
		UncheckToDefaultCheckbox(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber,
				String.valueOf(columnIndex));

	}

	public void clickToIconAtRowByIconTitle(String iconTitle, String rowNumber) {
		waitForElementClickable(driver, HomePageUI.ICON_BY_TITLE_AND_ROW_INDEX, rowNumber, iconTitle);
		clickToElement(driver, HomePageUI.ICON_BY_TITLE_AND_ROW_INDEX, rowNumber, iconTitle);

	}

	public void clickToLoadDataButton() {
		waitForElementClickable(driver, HomePageUI.BUTTON_LOADDATA);
		clickToElement(driver, HomePageUI.BUTTON_LOADDATA);
	}

	

	

}
