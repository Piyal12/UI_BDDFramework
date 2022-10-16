package im.ui.collection.wait;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class Base {

	private WebDriver driver;

	public Base(WebDriver driver) {
		this.driver = driver;
	}
	
	public Base(AppiumDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
