package context.photon;

import org.openqa.selenium.WebDriver;

import im.ui.driver.DriverManager;
import im.ui.session.web.Session;
import page.photon.PageObject;

public class TestContext {
	
	public PageObject po;
	public Session session;
	private WebDriver driver;
	public TestContext () {
		this.driver = DriverManager.initWebDriver();
		this.session = new Session(this.driver);
		this.po = new PageObject (this.session);
	}
	
	public WebDriver getWebDriver () {
		return this.driver;
	}
	
}
