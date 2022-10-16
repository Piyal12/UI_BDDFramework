package context.phoenixMobile;

import im.ui.driver.DriverManager;
import im.ui.session.mobile.Session;
import io.appium.java_client.AppiumDriver;
import page.phoenixMobile.PageObject;

import java.net.MalformedURLException;

public class TestContext {
    public PageObject po;
    public Session session;
    private AppiumDriver driver;
    public TestContext() throws MalformedURLException {
    	this.driver = (AppiumDriver) DriverManager.initAndroidDriver();
        this.session = new Session(this.driver);
        this.po = new PageObject(this.session);

    }

    public AppiumDriver getAppiumDriver() {    	
        return this.driver;
    }


}
