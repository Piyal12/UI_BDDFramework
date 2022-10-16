package im.ui.temp;

import org.openqa.selenium.WebDriver;

public class BaseCollection {

    private WebDriver webDriver;
    
    public BaseCollection(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

}
