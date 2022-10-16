package runner.phoenix;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {"src/test/resources/feature.phoenixWeb/phoenixWebTest.feature"},
		glue = {"stepdef/phoenixWeb"},
		plugin= {"pretty",
				"html:target/Cucumber-HtmlReport",
				"json:target/Cucumber-report.json"
		},
		
		monochrome=true,
		dryRun=false
		
		)

public class PhoenixWebTestRunner extends AbstractTestNGCucumberTests {
	
	

}
