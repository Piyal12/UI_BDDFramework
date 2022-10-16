package im.ui.collection.web;

import org.openqa.selenium.WebDriver;

import im.ui.collection.wait.web.Wait;


public class Element {
	
	private WebDriver driver;
	private ElementAction ea;
	private Wait wait;
	private String locator;

	
	public Element (WebDriver driver) {
		this.driver = driver;
		this.ea = new ElementAction (this.driver);
		this.wait = new Wait (this.driver, this.ea);
	}
	
	public Element element (String locator) {
		this.locator = locator;
		return this;
	}

	public ElementAction perform () {
		return this.ea.setLocator(this.locator);
	}
	
	public Wait waitUntil () {
		return this.wait.setLocator(this.locator);
	}
}
