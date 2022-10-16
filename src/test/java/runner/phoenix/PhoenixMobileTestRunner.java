package runner.phoenix;


import common.TestConfig;
import im.ui.common.Constant;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.util.HashMap;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = { "src/test/resources/feature.phoenixMobile/phoenixMobileTest.feature" },
        glue = "stepdef/phoenixMobile",
        plugin = { "html:target/Cucumber-HtmlReport.html", "json:target/Cucumber-report.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        monochrome = true,
        dryRun = false

)

public class PhoenixMobileTestRunner  extends AbstractTestNGCucumberTests {

    @BeforeSuite(alwaysRun = true)
    @Parameters({ "environment", "webBrowser", "webExecute", "mobilePlatform", "mobileExecute" })
	public void beforeSuite(@Optional String environment, @Optional String webBrowser, @Optional String webExecute,  @Optional String mobilePlatform, @Optional String mobileExecute ) throws Exception {
		HashMap <String, String> parameter = new HashMap <String, String>();
		parameter.put(Constant.ENVIRONMENT, environment);
		parameter.put(Constant.WEB_BROWSER, webBrowser);
		parameter.put(Constant.WEB_EXECUTE, webExecute);
		parameter.put(Constant.MOBILE_PLATFORM, mobilePlatform);
		parameter.put(Constant.MOBILE_EXECUTE, mobileExecute);
		TestConfig.init(parameter);


	}
}
