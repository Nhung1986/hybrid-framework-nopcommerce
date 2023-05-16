package pageObjectsJqueryUploadFile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.Jquery.UploadFile.HomePageUploadUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickStartButton() {
		List<WebElement> startButtons = getListWebElement(driver, HomePageUploadUI.START_BUTTON);
		for (WebElement startButton : startButtons) {
			startButton.click();
			SleepInsecond(1);

		}

	}

	public boolean isLoadedFileNameSucess(String fileName) {
		waitForElementVisible(driver, HomePageUploadUI.FILE_NAME_LOADED, fileName);
		return isElementDisplayed(driver, HomePageUploadUI.FILE_NAME_LOADED, fileName);
	}
	
	public boolean isUploadedFileNameSucess(String fileName) {
		waitForElementVisible(driver, HomePageUploadUI.FILE_NAME_UPLOADED_LINK, fileName);
		return isElementDisplayed(driver, HomePageUploadUI.FILE_NAME_UPLOADED_LINK, fileName);
	}
	
	public boolean isUpLoadedImageSucess(String fileName) {
		waitForElementVisible(driver, HomePageUploadUI.FILE_NAME_UPLOADED_IMAGE, fileName);
		return isImageLoaded(driver, HomePageUploadUI.FILE_NAME_UPLOADED_IMAGE, fileName);
	}

}
