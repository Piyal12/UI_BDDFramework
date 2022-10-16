package im.ui.session.mobile;

import im.ui.collection.mobile.DriverAction;
import im.ui.collection.mobile.Element;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public class Session {
	
	private AppiumDriver driver;
	private DriverAction da;
	private Element e;


	public Session (AppiumDriver driver) throws MalformedURLException {
		this.driver = driver;
		this.da = new DriverAction (driver);
		this.e = new Element(driver);
	}
	
	public AppiumDriver getDriver () {
		return this.driver;
	}
	
	public DriverAction getDriverAction () {
		return this.da;
	}
	
	public Element getElement () {
		return this.e;
	}
	

}
