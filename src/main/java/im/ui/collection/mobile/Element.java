package im.ui.collection.mobile;


import im.ui.collection.wait.mobile.Wait;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public class Element {
	
	private AppiumDriver driver;
	private ElementAction ea;
	private Wait wait;
	private String locator;

	
	public Element (AppiumDriver driver) throws MalformedURLException {
		this.driver = driver;
		this.ea = new ElementAction(this.driver);
		this.wait = new Wait (this.driver, this.ea);
	}
	
	public Element element (String locator) {
		this.locator = locator;
		return this;
	}
	
	public Wait waitUntil () {
		return this.wait.setLocator(this.locator);
	}
}
