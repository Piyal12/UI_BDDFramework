package im.ui.collection.web;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import im.ui.exception.StaleOrNoSuchElementException;
import im.ui.locator.BY;

public class ElementAction extends Base {

	private String locator;

	public ElementAction(WebDriver driver) {
		super(driver);
	}

	public ElementAction setLocator(String locator) {
		this.locator = locator;
		return this;
	}

	// click
	public void click() {
		try {
			this.getDriver().findElement(BY.get(this.locator)).click();
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	// set text
	public void setText(String text) {
		try {
			this.getDriver().findElement(BY.get(this.locator)).sendKeys(text);
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	// get text
	public String getText() {
		try {
			String text = this.getDriver().findElement(BY.get(this.locator)).getText();
			return text == null ? "" : text;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}

	// is exists
	public Boolean isExists() {
		try {
			return this.getDriver().findElement(BY.get(this.locator)) != null;
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			throw new StaleOrNoSuchElementException(e.getMessage());
		}
	}
}
