package im.ui.collection.mobile;

import io.appium.java_client.AppiumDriver;

public class Base {

    private AppiumDriver driver;
    
    public Base(AppiumDriver driver) {
        this.driver = driver;
    }

    public AppiumDriver getDriver() {
        return this.driver;
    }

}
