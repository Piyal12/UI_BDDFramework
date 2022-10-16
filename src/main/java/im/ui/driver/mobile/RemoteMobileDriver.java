package im.ui.driver.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.options.BaseOptions;

import org.openqa.selenium.WebDriver;

import im.ui.common.Config;
import im.ui.common.Constant;
import im.ui.exception.FileException;
import im.ui.exception.NullException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteMobileDriver {

	private static WebDriver driver;

	public static WebDriver create() {

		// AppiumServer.Start();

		if (Config.getProp(Constant.MOBILE_PLATFORM).toLowerCase().equalsIgnoreCase(Constant.IOS))
			return createIOSDriver();
		
		return createAndroidDriver();

	}

	private static WebDriver createAndroidDriver() {
		
		File app = new File(Config.getProp(Constant.ANDROID_APP_FILE_PATH).toString(), Config.getProp(Constant.ANDROID_APP_FILE_NAME).toString());

		if (!app.exists())
			throw new FileException("Invalid app file name : " + Config.getProp(Constant.ANDROID_APP_FILE_PATH)
					+ Config.getProp(Constant.ANDROID_APP_FILE_NAME) + " for " + Config.getProp(Constant.ENVIRONMENT));

		BaseOptions<?> bo = new BaseOptions<>();
		bo.setPlatformName(Constant.ANDROID);

		if (Config.getProp(Constant.LOCAL_ANDROID_DEVICE_NAME) == null)
			throw new NullException(Constant.LOCAL_ANDROID_DEVICE_NAME + " is null");

		bo.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProp(Constant.LOCAL_ANDROID_DEVICE_NAME));
		
		if (!(Config.getProp(Constant.LOCAL_ANDROID_PLATFORM_VERSION) == null)) 
			bo.setCapability("appActivity", Config.getProp(Constant.LOCAL_ANDROID_PLATFORM_VERSION).toString());

		if (!(Config.getProp(Constant.ANDROID_AUTOMATION_NAME) == null)) {
			if (Config.getProp(Constant.ANDROID_AUTOMATION_NAME).equals(AutomationName.ANDROID_UIAUTOMATOR2))
				bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			else
				bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		} else
			bo.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

		if (!(Config.getProp(Constant.ANDROID_APP_PACKAGE) == null))
			bo.setCapability("appPackage", Config.getProp(Constant.ANDROID_APP_PACKAGE).toString());

		if (!(Config.getProp(Constant.ANDROID_APP_ACTIVITY) == null))
			bo.setCapability("appActivity", Config.getProp(Constant.ANDROID_APP_ACTIVITY).toString());

	
		
		bo.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		try {
			driver = new AppiumDriver(new URL("http://"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString().trim()+":"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString().trim()+Config.getProp(Constant.LOCAL_APPIUM_SERVER_BASE_PATH).toString().trim()), bo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;

	}

	private static WebDriver createIOSDriver() {
	
		File app = new File(Config.getProp(Constant.IOS_APP_FILE_PATH).toString(), Config.getProp(Constant.IOS_APP_FILE_NAME).toString());

		if (!app.exists())
			throw new FileException("Invalid app file name : " + Config.getProp(Constant.IOS_APP_FILE_PATH)
					+ Config.getProp(Constant.IOS_APP_FILE_NAME) + " for " + Config.getProp(Constant.ENVIRONMENT));

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

		
		
		bo.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		try {
			driver = new AppiumDriver(new URL("http://"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString().trim()+":"+Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString().trim()+Config.getProp(Constant.LOCAL_APPIUM_SERVER_BASE_PATH).toString().trim()), bo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;

	}

}
