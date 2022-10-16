package im.ui.driver.mobile.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.Socket;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import im.ui.common.Config;
import im.ui.common.Constant;
import im.ui.exception.AppiumServerUsageException;

public class AppiumServer {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppiumServer.class);

	private static AppiumServiceBuilder builder;
	
	private static boolean isPortInUse(String host, String port) {
		boolean result = false;

		try {
			(new Socket(host, Integer.parseInt(port))).close();
			result = true;
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	
	private static Map<String, String> getSystemEnviroment () {
		Map<String, String> sysEnvironmentVar = new HashMap<>(System.getenv());
		sysEnvironmentVar.put("PATH", sysEnvironmentVar.get("PATH"));
		sysEnvironmentVar.put("ANDROID_HOME", (sysEnvironmentVar.get("ANDROID_HOME") == null ? Config.getProp(Constant.LOCAL_ANDROID_HOME).toString() : sysEnvironmentVar.get("ANDROID_HOME"))); // "/Users/Obuli/Library/Android/sdk");
		
		return sysEnvironmentVar;
	}
	
	public static AppiumDriverLocalService getServerInstance () {
		
		Boolean flag = AppiumServer.isPortInUse(Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString(),Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString());
		LOG.info("Port: "+ Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT)+" Host: "+ Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP)+" In use:"+flag);
		
		if (flag) 
			throw new AppiumServerUsageException("The port "+ Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT)+" in host "+ Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP)+ " is unavailble.");
		
		builder = new AppiumServiceBuilder()
				.withAppiumJS(new File (Config.getProp(Constant.LOCAL_APPIUM_JS).toString()))
				.usingDriverExecutable(new File (Config.getProp(Constant.LOCAL_NODE).toString()))
				.withEnvironment(AppiumServer.getSystemEnviroment())
				.withIPAddress(Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString())
				.withArgument(GeneralServerFlag.BASEPATH, Constant.LOCAL_APPIUM_SERVER_BASE_PATH)
				.usingPort(Integer.parseInt(Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString()))
				.withLogFile(new File(AppiumServer.getLogFileName(Config.getProp(Constant.LOCAL_APPIUM_SERVER_IP).toString(), Config.getProp(Constant.LOCAL_APPIUM_SERVER_PORT).toString())))
				;
		
			
		return AppiumDriverLocalService.buildService(builder);
		
	}
	
	private static String getLogFileName (String server, String port) {
		return Constant.APPIUM_SERVER_LOG_DIR+ Constant.APPIUM_SERVER_LOG_FILE_PREFIX+"_"+server+":"+port+"-"+Instant.now().toEpochMilli()+Constant.APPIUM_SERVER_LOG_FILE_SUFFIX;
	}

//
//    static AppiumDriverLocalService getInstance(){
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder
//                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                .usingDriverExecutable(new File("/usr/local/bin/node"))
//                .usingPort(4723)
//                .withLogFile(new File("AppiumLog.txt"))
//                .withArgument(()  -> "--base-path", "/wd/hub");
//
//        return AppiumDriverLocalService.buildService(builder);
//
//    }

}
