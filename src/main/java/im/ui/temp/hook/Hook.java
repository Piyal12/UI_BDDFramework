package im.ui.temp.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook  {

	private static final Logger LOG = LoggerFactory.getLogger(Hook.class);

	@Before(order = 0)
	public void beforeHook() {
		LOG.info("Before hook Main");
	}

	@After(order = 0)
	public void afterHook() {

		LOG.info("After hook Main");
		// System.out.println("After hook Main");
	}

	/*
	 * System.out.println("Before hook Main");
	 * 
	 * System.out.println("Env: " + Config.getProp("environment"));
	 * System.out.println("Exe: " + Config.getProp("execute"));
	 * 
	 * System.out.println("url: " + Config.getProp("url"));
	 * System.out.println("usn: " + Config.getProp("username"));
	 * System.out.println("otp: " + Config.getProp("otp"));
	 * 
	 * System.out.println("bro: " + Config.getProp("browser"));
	 */

}
