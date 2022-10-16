package page.photon;

import org.openqa.selenium.WebDriver;

import im.ui.collection.wait.web.Wait;
import im.ui.collection.web.DriverAction;
import im.ui.collection.web.Element;
import im.ui.common.Browser;
import im.ui.common.Config;
import im.ui.common.Constant;
import im.ui.session.web.Session;

public class BasePage {

	protected WebDriver driver;
	protected DriverAction da;
	protected Wait wait;
	protected Element e;

	public BasePage(Session session) {
		this.driver = session.getDriver();
		this.da = session.getDriverAction();
		this.e = session.getElement();

	}
	
	public Element element (String locator) {
		this.e.element(locator);
		return this.e;
	}
	
	public void nagivate () {
		this.da.navigateTo(Config.getProp(Constant.WEB_APPLICATION_URL).toString());
	}
	
	public void quit () {
		this.da.closeBrowser(Browser.CLOSE_TYPE.QUIT);
	}
	

	public byte[] takeScreenShot () {
		return this.da.takeScreenshot();
	}

}
