package pageUIs.JqueryDataTable;

public class HomePageUI {
	public static final String PAGINATION_BY_PAGE_NUMBER = "xpath=//li[@class ='qgrd-pagination-page']//a[text()='%s']";
	public static final String PAGINATION_ACTIVE_BY_PAGE_NUMBER = "xpath=//li[@class ='qgrd-pagination-page']//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String TEXTBOX_BY_LABLE_NAME = "xpath=//div[@class ='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGE = "xpath=//li[@class ='qgrd-pagination-page']";
	public static final String PAGINATION_PAGE_BY_INDEX = "xpath=//li[@class ='qgrd-pagination-page']['%s']/a";
	public static final String TOTAL_ROW_ON_ONE_PAGE = "xpath=//tbody/tr";
	public static final String ALL_ROW_COUNTRY_ONE_PAGE = "xpath=//tbody/tr/td[@data-key ='country']";
}
