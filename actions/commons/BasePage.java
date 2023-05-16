package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjectsNopcommerce.Admin.AdminLoginPageObject;
import pageObjectsNopcommerce.User.UserAddressPageObject;
import pageObjectsNopcommerce.User.UserCustomerInfoPageObject;
import pageObjectsNopcommerce.User.UserHomePageObject;
import pageObjectsNopcommerce.User.UserMyProductReviewsPageObject;
import pageObjectsNopcommerce.User.UserOrdersPageObject;
import pageObjectsNopcommerce.User.UserRewardPointsPageObject;
import pageUIs.Jquery.UploadFile.HomePageUploadUI;
import pageUIs.Nopcommerce.User.BasePageUI;

public class BasePage {
	private long longTime = 30;

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageURL(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refeshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPrensence(WebDriver driver) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		return explicitwait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPrensence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPrensence(driver).dismiss();
	}

	public String getTextlAlert(WebDriver driver) {
		return waitAlertPrensence(driver).getText();
	}

	public void senkeyToAlert(WebDriver driver, String textValue) {
		waitAlertPrensence(driver).sendKeys(textValue);
	}

	public void switchToWindowWByID(WebDriver driver, String OrtherID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			if (!id.equals(OrtherID)) {
				driver.switchTo().window(id);
			}
		}
	}

	public void switchToWindowWByPageTitle(WebDriver driver, String ExpectedPageTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(ExpectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParentID(WebDriver driver, String ParentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			if (!id.equals(ParentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(ParentID);
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
		
	}
	public void clickToElement(WebDriver driver, String locatorType, String...dymanicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).click();

	}

	public void senkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement webElement = getWebElement(driver, locatorType);
		webElement.clear();
		webElement.sendKeys(textValue);
	}
	public void senkeyToElement(WebDriver driver, String locatorType, String textValue, String...dymanicValues) {
		WebElement webElement = getWebElement(driver, getDynamicXpath(locatorType, dymanicValues));
		webElement.clear();
		webElement.sendKeys(textValue);
	}

	public String getTextElement(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public String getTextElement(WebDriver driver, String locatorType, String...dymanicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...dymanicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String...dymanicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not support");
		}
		return by;
	}
	
	public String getDynamicXpath(String locatorType, String...xpathDymanic) {
		locatorType = String.format(locatorType,(Object[])xpathDymanic);
		return locatorType;
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedItemText) {
		getWebElement(driver, parentXpath).click();
		SleepInsecond(1);
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		List<WebElement> allItems = explicitwait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItemText)) {
				JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInsecond(1);
				item.click();
				break;
			}
		}
	}

	public void enterAndSelectItemInDropdown(WebDriver driver, String textBoxXpath, String itemXpath,
			String expectedItemText) {
		WebElement webElement = getWebElement(driver, textBoxXpath);
		webElement.clear();
		webElement.sendKeys(expectedItemText);
		SleepInsecond(1);
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		List<WebElement> allItems = explicitwait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(itemXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItemText)) {
				SleepInsecond(1);
				item.click();
				break;
			}
		}
	}

	public void SleepInsecond(long TimeInSecond) {
		try {
			Thread.sleep(TimeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaColorFromGRBA(String grbaValue) {
		return Color.fromString(grbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {

		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String...dymanicValues) {

		return getListWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).size();
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String...dymanicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dymanicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void UncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void UncheckToDefaultCheckbox(WebDriver driver, String locatorType, String...dymanicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dymanicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String...dymanicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType, String...dymanicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	public boolean isElementSelected(WebDriver driver, String locatorType, String...dymanicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void holdMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dymanicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,locatorType), key).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		SleepInsecond(1);
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {

		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {

		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);

		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExcutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExcutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitwait.until(jQueryLoad) && explicitwait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {

		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		return (String) jsExcutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExcutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dymanicValues) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExcutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dymanicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String...dymanicValues) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dymanicValues))));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String...dymanicValues) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dymanicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementInvisible(WebDriver driver, String locatorType, String...dymanicValues) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dymanicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String...dymanicValues) {
		
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dymanicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType, String...dymanicValues) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dymanicValues))));
	}
	
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		
		fullFileName = fullFileName.trim();
		
		getWebElement(driver, HomePageUploadUI.UPLOAD_BUTTON).sendKeys(fullFileName);
	}
	
	public String getDirectorySlash(String folderName) {
		String separator = System.getProperty("file.separator");
		return separator + folderName + separator;
	}
	
	
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getAddressPage(driver);
	}
	
	public UserOrdersPageObject openOrdersPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDERS_LINK);
		clickToElement(driver, BasePageUI.ORDERS_LINK);
		return PageGeneratorManager.getOrdersPage(driver);
	}
	
	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARDPOINTS_LINK);
		clickToElement(driver, BasePageUI.REWARDPOINTS_LINK);
		return PageGeneratorManager.getRewardPointsPage(driver);
	}
	
	public UserMyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MYPRODUCTREVIWS_LINK);
		clickToElement(driver, BasePageUI.MYPRODUCTREVIWS_LINK);
		return PageGeneratorManager.getMyProductReviewsPage(driver);
	}
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMERINFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMERINFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	
	public void openPageAtMyAccoutByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYMANIC_PAGE__AT_MY_ACC_PAGE, pageName);
		clickToElement(driver, BasePageUI.DYMANIC_PAGE__AT_MY_ACC_PAGE, pageName);
	}
	
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_USER_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_USER_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_ADMIN_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_ADMIN_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
		
	
	}
}
