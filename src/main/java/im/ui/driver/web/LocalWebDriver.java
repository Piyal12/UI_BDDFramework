package im.ui.driver.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import im.ui.common.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalWebDriver {

	public static WebDriver create() {
		return LocalWebDriver.createWebDriver(null);
	}

	public static WebDriver create(String browser) {
		return LocalWebDriver.createWebDriver(browser);
	}

	private static WebDriver createWebDriver(String browser) {

		WebDriver driver;

		switch (Browser.TYPE.valueOf(browser.toUpperCase())) {

		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}

		return driver;
	}

}
