package im.ui.temp;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import im.ui.common.Browser;
import im.ui.common.Condition;
import im.ui.exception.StaleOrNoSuchElementException;
import im.ui.exception.WebDriverException;
import im.ui.locator.BY;

public class WebCollection extends BaseCollection {

	private WaitCollection wait;

	public WebCollection(WebDriver webDriver, WaitCollection waitCollection) {
		super(webDriver);
		this.wait = waitCollection;
	}

	// click
	public void click(String locator) {
		try {
			super.getWebDriver().findElement(BY.get(locator)).click();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public void click(String locator, Condition condition, Long timeOut) {
		this.wait.waitForElement(locator, condition, timeOut);
		this.click(locator);
	}

	// set text
	public void setText(String locator, String text) {
		try {
			super.getWebDriver().findElement(BY.get(locator)).sendKeys(text);
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}
	
	public void setText(String locator, String text, Condition condition, Long timeOut) {
		this.wait.waitForElement(locator, condition, timeOut);
		this.setText(locator, text);
	}

	// get text
	public String getText(String locator) {
		try {
			String text = super.getWebDriver().findElement(BY.get(locator)).getText();
			return text == null ? "" : text;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public String getText(String locator, Condition condition, Long timeOut) {
		this.wait.waitForElement(locator, condition, timeOut);
		return this.getText(locator);
	}

	// is exists
	public Boolean isExists(String locator) {
		try {
			return super.getWebDriver().findElement(BY.get(locator)) != null;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public Boolean isExists(String locator, Condition condition, Long timeOut) {
		this.wait.waitForElement(locator, condition, timeOut);
		return this.isExists(locator);
	}


	// navigate to
	public void navigateTo(String url) {
		try {
			super.getWebDriver().navigate().to(url);
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot navigate to URL:" + url);
		}
	}

	// window maximize
	public void windowMaximize() {
		try {
			super.getWebDriver().manage().window().maximize();
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot maximize the window.");
		}
	}

	// browser close
	public void closeBrowser(final Browser.CLOSE_TYPE closeType) {
		if (super.getWebDriver() == null)
			throw new WebDriverException("WebDriver not initialized.", closeType.toString());

		try {
			switch (closeType) {
			case QUIT:
				super.getWebDriver().quit();
				break;
			case CLOSE:
				super.getWebDriver().close();
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
			screenShot = ((TakesScreenshot) super.getWebDriver()).getScreenshotAs(OutputType.BYTES);
		} catch (StaleOrNoSuchElementException e) {
			throw new StaleOrNoSuchElementException("Cannot take screenshot.");
		}
		return screenShot;
	}

	

}
