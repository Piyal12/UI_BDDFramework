package im.ui.collection.web;

import org.openqa.selenium.WebDriver;

public class Base {

    private WebDriver webDriver;
    
    public Base (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

}
