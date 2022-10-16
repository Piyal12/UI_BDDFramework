package im.ui.driver;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.appium.wrapper.DriverWrapper;

import im.ui.common.Context;
import im.ui.driver.mobile.LocalAndroidDriver;
import im.ui.driver.mobile.LocalIOSDriver;
import im.ui.driver.mobile.RemoteMobileDriver;
import im.ui.driver.mobile.server.AppiumServer;
import im.ui.driver.web.LocalWebDriver;
import im.ui.driver.web.RemoteWebDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import im.ui.common.Config;
import im.ui.common.Constant;

public class DriverManager {

	private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

	private static ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<WebDriver>();
	private static ThreadLocal<WebDriver> ANDROID_DRIVER = new ThreadLocal<WebDriver>();
	private static ThreadLocal<WebDriver> IOS_DRIVER = new ThreadLocal<WebDriver>();
	private static ThreadLocal<Context> APP_CONTEXT = new ThreadLocal<Context>() {
        @Override 
        public Context initialValue() {
            return Context.WEB;
        }
    };
	private static ThreadLocal<AppiumDriverLocalService> APPIUM_SERVER_SERVICE = new ThreadLocal<AppiumDriverLocalService>();


	public static void setContext(Context app) {
		DriverManager.APP_CONTEXT.set(app);
	}
	
	public static Context getContext() {
		return DriverManager.APP_CONTEXT.get();
	}
	
	public static AppiumDriverLocalService getAppiumServerService() {
		return DriverManager.APPIUM_SERVER_SERVICE.get();
	}
	
	public static void setAppiumServerService(AppiumDriverLocalService service) {
		DriverManager.APPIUM_SERVER_SERVICE.set(service);
	}
	
	public static void removeAppiumServerService() {
		DriverManager.APPIUM_SERVER_SERVICE.remove();
	}
	
	public static void AppiumServerStart () {
		if (Objects.isNull(APPIUM_SERVER_SERVICE.get()))
				DriverManager.setAppiumServerService(AppiumServer.getServerInstance());
		DriverManager.getAppiumServerService().start();
	}
	
	public static void AppiumServerStop () {
		if (! Objects.isNull(DriverManager.getAppiumServerService())) {
			DriverManager.getAppiumServerService().stop();
			DriverManager.removeAppiumServerService();
		}
	}
	
	public static WebDriver getDriverByContext(Context context) {
		
		if (context.equals(Context.WEB)) {
			if (Objects.isNull(WEB_DRIVER.get())) 
				return null;
			return WEB_DRIVER.get();
			
		} else if (context.equals(Context.ANDROID)) {
			if (Objects.isNull(ANDROID_DRIVER.get()))
				return null;
			return ANDROID_DRIVER.get();
		}
		
		else if (context.equals(Context.IOS)) {
			if (Objects.isNull(IOS_DRIVER.get()))
				return null;
			return IOS_DRIVER.get();
		}
		
		return null;
		
	}
	
	
	public static WebDriver initWebDriver() {
		
		if (Objects.isNull(WEB_DRIVER.get())) 
			setWebDriver();
		
		DriverManager.setContext(Context.WEB);
		return WEB_DRIVER.get();
	}
	
	public static WebDriver initAndroidDriver() {
		
		if (Objects.isNull(ANDROID_DRIVER.get())) 
			setAndroidDriver();
		
		DriverManager.setContext(Context.ANDROID);
		return ANDROID_DRIVER.get();
	}

	public static WebDriver initIOSDriver() {
	
		if (Objects.isNull(IOS_DRIVER.get())) 
			setIOSDriver();
	
		DriverManager.setContext(Context.IOS);
		return IOS_DRIVER.get();
	}

	private static void setWebDriver() {
		
		WebDriver driver;
		LOG.info("Creating a new Web Driver");


		if (Config.getProp(Constant.WEB_EXECUTE).toString().equalsIgnoreCase(Constant.WEB_REMOTE)) 
			driver = RemoteWebDriver.create(Config.getProp(Constant.WEB_BROWSER).toString(), Config.getProp(Constant.WEB_HUB_URL).toString());
		else 
			driver = LocalWebDriver.create(Config.getProp(Constant.WEB_BROWSER).toString());
		
		if (Config.getProp(Constant.WEB_HEALENIUM_ENABLE).toString().equalsIgnoreCase(Constant.YES))
			driver = SelfHealingDriver.create(driver);

		DriverManager.WEB_DRIVER.set(driver);
		DriverManager.maximize();
		DriverManager.SetImplicitTimeout();
	}

	private static void setAndroidDriver () {
		
		WebDriver driver;
		LOG.info("Creating a new Android Driver");
		
		if (Config.getProp(Constant.MOBILE_EXECUTE).toString().equalsIgnoreCase(Constant.MOBILE_LOCAL))
			driver = LocalAndroidDriver.create();
		else 
			driver = RemoteMobileDriver.create();
		
		if (Config.getProp(Constant.MOBILE_HEALENIUM_ENABLE).toString().equalsIgnoreCase(Constant.YES))
			driver = DriverWrapper.wrap((AppiumDriver) driver);
		
		DriverManager.ANDROID_DRIVER.set(driver);

	}
	
	private static void setIOSDriver () {
		
		WebDriver driver;
		LOG.info("Creating a new IOS Driver");
		
		if (Config.getProp(Constant.MOBILE_EXECUTE).toString().equalsIgnoreCase(Constant.MOBILE_LOCAL))
			driver = LocalIOSDriver.create();
		else 
			driver = RemoteMobileDriver.create();
		
		if (Config.getProp(Constant.MOBILE_HEALENIUM_ENABLE).toString().equalsIgnoreCase(Constant.YES))
			driver = DriverWrapper.wrap((AppiumDriver) driver);
		
		DriverManager.IOS_DRIVER.set(driver);
		
	}
	
	private static void maximize() {
		LOG.info("Maximize the browser");
		DriverManager.getDriverByContext(Context.WEB).manage().window().maximize();
	}

	private static void SetImplicitTimeout() {
		LOG.info("Set Implicit Timeout");
		DriverManager.getDriverByContext(Context.WEB).manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Config.getProp(Constant.WEB_IMPLICIT_TIMEOUT).toString())));
	}

	public static void remove() {
		

		//if (APP_CONTEXT.get().equals(Context.WEB)) {
			if (! Objects.isNull(WEB_DRIVER.get())) {
				WEB_DRIVER.remove();
				APP_CONTEXT.remove();
			}
		//}
		
		//if (APP_CONTEXT.get().equals(Context.ANDROID)) {
			if (! Objects.isNull(ANDROID_DRIVER.get())) {
				ANDROID_DRIVER.remove();
				APP_CONTEXT.remove();
			}
		//}
		

		//if (APP_CONTEXT.get().equals(Context.IOS)) {
			if (! Objects.isNull(IOS_DRIVER.get())) {
				IOS_DRIVER.remove();
				APP_CONTEXT.remove();
			}
		//}
			DriverManager.AppiumServerStop();

	}

}
