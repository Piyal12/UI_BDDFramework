package im.ui.collection.mobile;

import im.ui.exception.StaleOrNoSuchElementException;
import im.ui.locator.BY;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.net.MalformedURLException;


public class ElementAction extends Base {

	private String locator;

	public ElementAction(AppiumDriver driver) throws MalformedURLException {
		super(driver);
	}

	public ElementAction setLocator(String locator) {
		this.locator = locator;
		return this;
	}

	public void tap() {
		try {
			this.getDriver().findElement(BY.get(this.locator)).click();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public void setText(String text) {
		try {
			this.getDriver().findElement(BY.get(this.locator)).sendKeys(text);
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public String getText() {
		try {
			String text = this.getDriver().findElement(BY.get(this.locator)).getText();
			return text == null ? "" : text;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public void clear() {
		try {
			this.getDriver().findElement(BY.get(this.locator)).clear();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public String getTagName() {
		try {
			String text = this.getDriver().findElement(BY.get(this.locator)).getTagName();
			return text == null ? "" : text;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public Boolean isDisplayed() {
		try {
			return this.getDriver().findElement(BY.get(this.locator)).isDisplayed();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public Dimension elementSize(){
		try {
			Dimension dimension = this.getDriver().findElement(BY.get(this.locator)).getSize();
			return dimension;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public void longTap() {
		try {
			TouchActions actions = new TouchActions(this.getDriver());
			actions.longPress((WebElement) BY.get(this.locator));
			actions.perform();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	public void scrollToElement() {
		try {
			TouchActions actions = new TouchActions(this.getDriver());
			actions.scroll((WebElement) BY.get(this.locator), 10, 20);
			actions.perform();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

}
