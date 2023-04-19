package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	private WebElement getWebElement(WebDriver driver, String xPathLocator) {
		return driver.findElement(getXpath(xPathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xPathLocator) {
		return driver.findElements(getXpath(xPathLocator));
	}

	public void clickToElement(WebDriver driver, String xPathLocator) {
		getWebElement(driver, xPathLocator).click();

	}

	public void senkeyToElement(WebDriver driver, String xPathLocator, String textValue) {
		WebElement webElement = getWebElement(driver, xPathLocator);
		webElement.clear();
		webElement.sendKeys(textValue);
	}

	public String getTextElement(WebDriver driver, String xPathLocator) {
		return getWebElement(driver, xPathLocator).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xPathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xPathLocator));
		select.selectByValue(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String xPathLocator) {
		Select select = new Select(getWebElement(driver, xPathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xPathLocator) {
		Select select = new Select(getWebElement(driver, xPathLocator));
		return select.isMultiple();
	}

	private By getXpath(String xPathLocator) {
		return By.xpath(xPathLocator);
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedItemText) {
		getWebElement(driver, parentXpath).click();
		SleepInsecond(1);
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		List<WebElement> allItems = explicitwait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childXpath)));
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
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(itemXpath)));
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

	public String getElementAttribute(WebDriver driver, String xPathLocator, String attributeName) {
		return getWebElement(driver, xPathLocator).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xPathLocator, String propertyName) {
		return getWebElement(driver, xPathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromGRBA(String grbaValue) {
		return Color.fromString(grbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xPathLocator) {

		return getListWebElement(driver, xPathLocator).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xPathLocator) {
		WebElement element = getWebElement(driver, xPathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void UncheckToDefaultCheckbox(WebDriver driver, String xPathLocator) {
		WebElement element = getWebElement(driver, xPathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xPathLocator) {
		return getWebElement(driver, xPathLocator).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xPathLocator) {
		return getWebElement(driver, xPathLocator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xPathLocator) {
		return getWebElement(driver, xPathLocator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xPathLocator) {
		driver.switchTo().frame(getWebElement(driver, xPathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void holdMouseToElement(WebDriver driver, String xPathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xPathLocator)).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xPathLocator) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xPathLocator);
		String originalStyle = element.getAttribute("style");
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		SleepInsecond(1);
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xPathLocator) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].click();", getWebElement(driver, xPathLocator));
	}

	public void scrollToElement(WebDriver driver, String xPathLocator) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xPathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xPathLocator, String attributeRemove) {

		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xPathLocator));
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

	public String getElementValidationMessage(WebDriver driver, String xPathLocator) {

		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		return (String) jsExcutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xPathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xPathLocator) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExcutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xPathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String xPathLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getXpath(xPathLocator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xPathLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpath(xPathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xPathLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getXpath(xPathLocator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xPathLocator) {
		
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		
		explicitwait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xPathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xPathLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTime);
		explicitwait.until(ExpectedConditions.elementToBeClickable(getXpath(xPathLocator)));
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
