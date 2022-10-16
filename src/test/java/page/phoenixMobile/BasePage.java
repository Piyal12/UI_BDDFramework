package page.phoenixMobile;

import im.ui.collection.mobile.DriverAction;
import im.ui.collection.mobile.Element;
import im.ui.collection.wait.mobile.Wait;
import im.ui.session.mobile.Session;
import io.appium.java_client.AppiumDriver;

public class BasePage {

    protected AppiumDriver driver;
    protected DriverAction da;
    protected Wait wait;
    protected Element e;

    public BasePage(Session session) {
        this.driver = session.getDriver();
        this.da = session.getDriverAction();
        this.e = session.getElement();
    }

    public Element element(String locator) {
        this.e.element(locator);
        return this.e;
    }
}
