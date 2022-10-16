package im.ui.collection.web;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import im.ui.common.Browser;
import im.ui.exception.StaleOrNoSuchElementException;
import im.ui.exception.WebDriverException;

public class DriverAction extends Base {

	public DriverAction (WebDriver driver) {
		super(driver);
	}
	
	// navigate to
	public void navigateTo(String url) {
		try {
			this.getDriver().navigate().to(url);
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot navigate to URL:" + url);
		}
	}

	// window maximize
	public void windowMaximize() {
		try {
			this.getDriver().manage().window().maximize();
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot maximize the window.");
		}
	}

	// browser close
	public void closeBrowser(final Browser.CLOSE_TYPE closeType) {
		if (this.getDriver() == null)
			throw new WebDriverException("WebDriver not initialized.", closeType.toString());

		try {
			switch (closeType) {
			case QUIT:
				super.getDriver().quit();
				break;
			case CLOSE:
				super.getDriver().close();
				break;
			}
		} catch (Exception e) {
			throw new WebDriverException(e.getMessage(), closeType.toString());
		}

	}

	// take screenshot
	public byte[] takeScreenshot() {
		byte[] screenShot = null;
		try {
			screenShot = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.BYTES);
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot take screenshot.");
		}
		return screenShot;
	}


	
}
