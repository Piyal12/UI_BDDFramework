package im.ui.common;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import im.ui.exception.FileException;
import im.ui.exception.NullException;

public class Config {

	private static Properties application = null;
	private static Properties common = null;
	private static HashMap<Environment, String> CONF_FILE_LIST = new HashMap<Environment, String>();

	private static Environment environment = null;
	private static Browser.TYPE webBrowser = null;
	private static Browser.EXECUTE webExecute = null;
	private static Mobile.PLATFORM mobilePlatform = null;
	private static Mobile.EXECUTE mobileExecute = null;

	public static void addConfig(Environment environment, String filename) {
		CONF_FILE_LIST.put(environment, filename);
	}

	public static void setConfig(HashMap<String, String> parameter) {

		Config.setCommon();
		Config.setPrerequisite(parameter);
		Config.setEnvironment();
	}

	private static void setCommon() {
		// Load Default Properties file
		try {
			if (!CONF_FILE_LIST.containsKey(Environment.DEFAULT))
				throw new NullException("Config File not found for :" + Environment.DEFAULT);
		} catch (Exception e) {
			throw new NullException(e.getMessage());
		}

		try {
			common = new Properties();
			common.load(new FileInputStream(CONF_FILE_LIST.get(Environment.DEFAULT)));
		} catch (Exception e) {
			throw new FileException("Invalid file name for environment or cannot load file: " + Environment.DEFAULT
					+ ", filename: " + CONF_FILE_LIST.get(Environment.DEFAULT) + "\n" + e.getMessage());
		}
	}

	private static void setPrerequisite(HashMap<String, String> parameter) {

		environment = (common.getProperty(Constant.ENVIRONMENT) == null) ? Environment.QA : getEnvironment(common.getProperty(Constant.ENVIRONMENT).trim());
		webBrowser = (common.getProperty(Constant.WEB_BROWSER) == null) ? Browser.TYPE.CHROME : getBrowser(common.getProperty(Constant.WEB_BROWSER).trim());
		webExecute = (common.getProperty(Constant.WEB_EXECUTE) == null) ? Browser.EXECUTE.LOCAL : getBrowserExecute(common.getProperty(Constant.WEB_EXECUTE).trim());
		mobilePlatform = (common.getProperty(Constant.MOBILE_PLATFORM) == null) ? Mobile.PLATFORM.ANDROID : getMobilePlatform(common.getProperty(Constant.MOBILE_PLATFORM).trim());
		mobileExecute = (common.getProperty(Constant.MOBILE_EXECUTE) == null) ? Mobile.EXECUTE.LOCAL : getMobileExecute(common.getProperty(Constant.MOBILE_EXECUTE).trim());

		if (parameter.get(Constant.ENVIRONMENT) != null)
			environment = getEnvironment(parameter.get(Constant.ENVIRONMENT).trim());
		if (parameter.get(Constant.WEB_BROWSER) != null)
			webBrowser = getBrowser(parameter.get(Constant.WEB_BROWSER).trim());
		if (parameter.get(Constant.WEB_EXECUTE) != null)
			webExecute = getBrowserExecute(parameter.get(Constant.WEB_EXECUTE).trim());
		if (parameter.get(Constant.MOBILE_PLATFORM) != null)
			mobilePlatform = getMobilePlatform(parameter.get(Constant.MOBILE_PLATFORM).trim());
		if (parameter.get(Constant.MOBILE_EXECUTE) != null)
			mobileExecute = getMobileExecute(parameter.get(Constant.MOBILE_EXECUTE).trim());
		
		
		common.remove(Constant.ENVIRONMENT);
		common.put(Constant.ENVIRONMENT, environment.toString().toLowerCase());
		
		common.remove(Constant.WEB_BROWSER);
		common.put(Constant.WEB_BROWSER, webBrowser.toString().toLowerCase());
		
		common.remove(Constant.WEB_EXECUTE);
		common.put(Constant.WEB_EXECUTE, webExecute.toString().toLowerCase());
		
		common.remove(Constant.MOBILE_PLATFORM);
		common.put(Constant.MOBILE_PLATFORM, mobilePlatform.toString().toLowerCase());
		
		common.remove(Constant.MOBILE_EXECUTE);
		common.put(Constant.MOBILE_EXECUTE, mobileExecute.toString().toLowerCase());
		
	}

	private static void setEnvironment() {
		
		// Load AUT Environment Properties file
		
		try {
			if (! CONF_FILE_LIST.containsKey(environment))
				throw new NullException("Config File not found for :" + environment.toString());
		} catch (Exception e) {
			throw new NullException("Config File not found for :" + environment.toString());
		}

		try {
			application = new Properties();
			application.load(new FileInputStream(CONF_FILE_LIST.get(environment)));
		} catch (Exception e) {
			throw new FileException("Invalid file name for environment or cannot load file: "
					+ environment.toString() + ", filename: "
					+ CONF_FILE_LIST.get(environment) + "\n" + e.getMessage());
		}

	}

	public static String getApplicationProp(String name) {
		return Config.application.getProperty(name);
	}

	public static String getDefaultProp(String name) {
		return Config.common.getProperty(name);
	}

	public static String getProp(String name) {
		if (Config.application.containsKey(name))
			return Config.application.getProperty(name);
		return Config.common.getProperty(name);
	}

	private static Environment getEnvironment(String env) {
		if (env.equalsIgnoreCase(Constant.DEV))
			return Environment.DEV;
		if (env.equalsIgnoreCase(Constant.STAGE))
			return Environment.STAGE;
		if (env.equalsIgnoreCase(Constant.PREPROD))
			return Environment.PREPROD;
		if (env.equalsIgnoreCase(Constant.PROD))
			return Environment.PROD;
		return Environment.QA;
	}

	private static Browser.TYPE getBrowser(String browser) {
		if (Browser.TYPE.EDGE.toString().equalsIgnoreCase(browser))
			return Browser.TYPE.EDGE;
		if (Browser.TYPE.FIREFOX.toString().equalsIgnoreCase(browser))
			return Browser.TYPE.FIREFOX;
		if (Browser.TYPE.SAFARI.toString().equalsIgnoreCase(browser))
			return Browser.TYPE.SAFARI;
		return Browser.TYPE.CHROME;
	}

	private static Browser.EXECUTE getBrowserExecute(String execute) {
		if (Browser.EXECUTE.REMOTE.toString().equalsIgnoreCase(execute))
			return Browser.EXECUTE.REMOTE;
		return Browser.EXECUTE.LOCAL;
	}

	private static Mobile.EXECUTE getMobileExecute(String execute) {
		if (Mobile.EXECUTE.REMOTE.toString().equalsIgnoreCase(execute))
			return Mobile.EXECUTE.REMOTE;
		return Mobile.EXECUTE.LOCAL;
	}

	private static Mobile.PLATFORM getMobilePlatform(String platform) {
		if (Mobile.PLATFORM.IOS.toString().equalsIgnoreCase(platform))
			return Mobile.PLATFORM.IOS;
		return Mobile.PLATFORM.ANDROID;
	}
	
	public static String getSettingsMessage () {
		return "[Environment:"+Config.environment+" WebBrowser:"+Config.webBrowser+" WebExecute:"+Config.webExecute+" MobilePlatform:"+Config.mobilePlatform+ " MobileExecute:"+Config.mobileExecute+"]";
	}

}
