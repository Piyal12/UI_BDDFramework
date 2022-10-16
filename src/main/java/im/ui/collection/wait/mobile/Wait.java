package im.ui.collection.wait.mobile;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import im.ui.collection.wait.Base;
import im.ui.collection.mobile.ElementAction;
import im.ui.common.Config;
import im.ui.common.Constant;
import im.ui.locator.BY;
import io.appium.java_client.AppiumDriver;

public class Wait extends Base {

	private String locator;
	private long timeOut;
	
	private ElementAction ea;

	public Wait (AppiumDriver driver) {
		super(driver);
	}
	
	public Wait (AppiumDriver driver, ElementAction ea) {
		super(driver);
		this.setTimeOut();
		this.ea = ea;
	}
	
	protected void setTimeOut () {
		timeOut = Long.parseLong(Config.getProp(Constant.WEB_EXPLICIT_TIMEOUT));
	}
	
	private long getTimeOut () {
		return timeOut;
	}
	
	public Wait setLocator(String locator) {
		this.locator = locator;	
		return this;
	}
	
	public Wait present () {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.getTimeOut())).until(ExpectedConditions.presenceOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait present (long timeOut) {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(timeOut)).until(ExpectedConditions.presenceOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait presents () {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.getTimeOut())).until(ExpectedConditions.presenceOfAllElementsLocatedBy(BY.get(this.locator)));
		return this;
	}
	
	public Wait presents (long timeOut) {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(timeOut)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(BY.get(this.locator)));
		return this;
	}
	
	public Wait invisible () {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.getTimeOut())).until(ExpectedConditions.invisibilityOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait invisible (long timeOut) {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(timeOut)).until(ExpectedConditions.invisibilityOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait visible () {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.getTimeOut())).until(ExpectedConditions.visibilityOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait visible (long timeOut) {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOfElementLocated(BY.get(this.locator)));
		return this;
	}
	
	public Wait clickable () {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(this.getTimeOut())).until(ExpectedConditions.elementToBeClickable(BY.get(this.locator)));
		return this;
	}
	
	public Wait clickable (long timeOut) {
		new WebDriverWait(this.getDriver(), Duration.ofSeconds(timeOut)).until(ExpectedConditions.elementToBeClickable(BY.get(this.locator)));
		return this;
	}
	
	public ElementAction perform() {
		return this.ea.setLocator(this.locator);
	}
}
