package hook.photon;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import context.photon.TestContext;
import im.ui.driver.DriverManager;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hook {

	private static final Logger LOG = LoggerFactory.getLogger(Hook.class);
	public TestContext tc;

	public Hook(TestContext tc) {
		this.tc = tc;
	}

	@BeforeMethod
	public void beforeHook() {
		LOG.info("Before hook Test 1");
	}

	@AfterMethod
	public void afterHook() {
		LOG.info("After hook Test 1");
		this.tc.po.getBasePage().quit();
		DriverManager.remove();
	}

	@AfterStep
	public void afterStep(Scenario scenario) throws IOException {
		if (scenario.isFailed())
			scenario.attach(this.tc.po.getBasePage().takeScreenShot(), "image/png", "image");
	}
}
