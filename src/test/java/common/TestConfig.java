package common;

import java.util.HashMap;

import im.ui.common.Config;
import im.ui.common.Environment;

public class TestConfig {
	
	private static String CONF_DIR = System.getProperty("user.dir")+"/src/test/resources/config/";

	public static void init (HashMap<String,String> parameter) throws Exception {
		
		Config.addConfig(Environment.DEFAULT, CONF_DIR+"common.properties"); 
		Config.addConfig(Environment.QA, CONF_DIR+"qa.properties"); 
		Config.addConfig(Environment.DEV, CONF_DIR+"dev.properties"); 

		Config.setConfig(parameter);
	}

}
