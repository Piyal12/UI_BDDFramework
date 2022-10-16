package im.ui.temp;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import im.ui.common.Condition;
import im.ui.exception.ElementException;
import im.ui.locator.BY;

public class WaitCollection extends BaseCollection {

	public WaitCollection(WebDriver webDriver) {
		super(webDriver);
	}

	public void waitForElement(String locator, Condition condition, Long timeOut) {

		try {

			switch (condition) {
			case PRESENT:
				new WebDriverWait(super.getWebDriver(), Duration.ofSeconds(timeOut))
						.until(ExpectedConditions.presenceOfElementLocated(BY.get(locator)));
				break;
			case PRESENTS:
				new WebDriverWait(super.getWebDriver(), Duration.ofSeconds(timeOut))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(BY.get(locator)));
				break;
			case NOT_VISIBLE:
				new WebDriverWait(super.getWebDriver(), Duration.ofSeconds(timeOut))
						.until(ExpectedConditions.invisibilityOfElementLocated(BY.get(locator)));
				break;
			case VISIBLE:
				new WebDriverWait(super.getWebDriver(), Duration.ofSeconds(timeOut))
						.until(ExpectedConditions.visibilityOfElementLocated(BY.get(locator)));
				break;
			case CLICKABLE:
				new WebDriverWait(super.getWebDriver(), Duration.ofSeconds(timeOut))
						.until(ExpectedConditions.elementToBeClickable(BY.get(locator)));
				break;
			default:
				break;
			}
		} catch (RuntimeException e) {
			throw new ElementException(e.getMessage(), locator, condition, timeOut);
		}
	}

	
}
