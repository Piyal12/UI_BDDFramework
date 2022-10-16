package im.ui.session.web;

import org.openqa.selenium.WebDriver;

import im.ui.collection.web.DriverAction;
import im.ui.collection.web.Element;


public class Session {
	
	private WebDriver driver;
	private DriverAction da;
	private Element e;


	public Session (WebDriver driver) {
		this.driver = driver;
		this.da = new DriverAction (driver);
		this.e = new Element(driver);
	}
	
	public WebDriver getDriver () {
		return this.driver;
	}
	
	public DriverAction getDriverAction () {
		return this.da;
	}
	
	public Element getElement () {
		return this.e;
	}
	

}
