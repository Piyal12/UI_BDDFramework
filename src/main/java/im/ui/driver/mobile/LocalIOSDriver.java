package im.ui.driver.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.options.BaseOptions;

import org.openqa.selenium.WebDriver;

import im.ui.common.Config;
import im.ui.common.Constant;
import im.ui.driver.DriverManager;
import im.ui.exception.FileException;
import im.ui.exception.NullException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalIOSDriver {

	private static WebDriver driver;

	public static WebDriver create() {

		if (Config.getProp(Constant.APPIUM_SERVER_AUTO_START).equalsIgnoreCase(Constant.YES))
			DriverManager.AppiumServerStart();

	
		return createIOSDriver();
		
	}

	private static WebDriver createIOSDriver() {
	
		File app=null;
		if (Config.getProp(Constant.IOS_APP_INSTALL_RUNTIME).toString().trim().equalsIgnoreCase(Constant.YES)) {
			app = new File(Config.getProp(Constant.IOS_APP_FILE_PATH).toString(), Config.getProp(Constant.IOS_APP_FILE_NAME).toString());

			if (!app.exists())
				throw new FileException("Invalid app file name : " + Config.getProp(Constant.IOS_APP_FILE_PATH) + Config.getProp(Constant.IOS_APP_FILE_NAME) + " for " + Config.getProp(Constant.ENVIRONMENT));
		}
		
		BaseOptions<?> bo = new BaseOptions<>();
		bo.setPlatformName(Constant.IOS);

		if (Config.getProp(Constant.LOCAL_IOS_DEVICE_NAME) == null)
			throw new NullException(Constant.LOCAL_IOS_DEVICE_NAME + " is null");

		bo.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProp(Constant.LOCAL_IOS_DEVICE_NAME));
		
		if (!(Config.getProp(Constant.LOCAL_IOS_PLATFORM_VERSION) == null)) 
			bo.setCapability("appActivity", Config.getProp(Constant.LOCAL_IOS_PLATFORM_VERSION).toString());

		if (!(Config.getProp(Constant.IOS_AUTOMATION_NAME) == null)) {
			if (Config.getProp(Constant.IOS_AUTOMATION_NAME).equals(AutomationName.IOS_XCUI_TEST))
				bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			else
				bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);

		} else
			bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);

		if (!(Config.getProp(Constant.IOS_APP_PACKAGE) == null))
			bo.setCapability("appPackage", Config.getProp(Constant.IOS_APP_PACKAGE).toString());

		if (!(Config.getProp(Constant.IOS_APP_ACTIVITY) == null))
			bo.setCapability("appActivity", Config.getProp(Constant.IOS_APP_ACTIVITY).toString());

		if (!(Config.getProp(Constant.IOS_LAUNCH_TIMEOUT) == null))
			bo.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, Config.getProp(Constant.IOS_LAUNCH_TIMEOUT).toString());

		if (!(Config.getProp(Constant.IOS_CONNECTION_TIMEOUT) == null))
			bo.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT, Config.getProp(Constant.IOS_CONNECTION_TIMEOUT).toString());

		
		if (Config.getProp(Constant.IOS_APP_INSTALL_RUNTIME).toString().trim().equalsIgnoreCase(Constant.YES))
			bo.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		try {
			
			if (! Config.getProp(Constant.APPIUM_SERVER_AUTO_START).equalsIgnoreCase(Constant.YES))
				driver = new AppiumDriver(new URL("http://"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString().trim()+":"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString().trim()+Config.getProp(Constant.LOCAL_APPIUM_SERVER_BASE_PATH).toString().trim()), bo);
			else
				driver = new AppiumDriver(DriverManager.getAppiumServerService().getUrl(), bo);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;

	}

}
