package im.ui.driver.web;

import org.openqa.selenium.WebDriver;
import im.ui.common.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RemoteWebDriver {

	public static WebDriver create(String browser, String hubURL) {

		WebDriver driver = null;

		switch (Browser.TYPE.valueOf(browser.toUpperCase())) {

		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().remoteAddress(hubURL).create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().remoteAddress(hubURL).create();
			break;
		case SAFARI:
			driver = WebDriverManager.safaridriver().remoteAddress(hubURL).create();
			break;
		default:
			driver = WebDriverManager.chromedriver().remoteAddress(hubURL).create();
			break;
		}

		return driver;
	}
}
