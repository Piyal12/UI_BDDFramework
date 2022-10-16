package stepdef.phoenixMobile;

import context.photon.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSD {
	
	private TestContext tc;
	
	public LoginSD (TestContext tc) {
		this.tc = tc;
	}
	
	@Given("^User launch the application$")
	public void launchApplication () throws InterruptedException {		
		this.tc.po.getBasePage().nagivate();
		Thread.sleep(1000);
	}
	
	@When("^User enter the mobile number default$")
	public void enterDefaultMobileNumber () throws InterruptedException {
		this.tc.po.getLoginPage().enterMobileNumber();
		this.tc.po.getLoginPage().enterLoginButton();
		Thread.sleep(1000);
	}
	@When("^User enter the (.+) number$")
	public void enterMobileNumber (String mobileNumber) throws InterruptedException {
		this.tc.po.getLoginPage().enterMobileNumber(mobileNumber);
		this.tc.po.getLoginPage().enterLoginButton();
		Thread.sleep(1000);
	}
	
	@And("^User enter the (.+) received$")
	public void enterOtp (String otp) throws InterruptedException {
		this.tc.po.getLoginPage().enterOtp();
		Thread.sleep(1000);

	}
	
	@And("^User select the sarjapura store$")
	public void selectStore () throws InterruptedException {
		this.tc.po.getLoginPage().selectStore();
	}
	
	@Then("^User logout of the application$")
	public void logoutOfApplication () throws InterruptedException {
		this.tc.po.getLoginPage().logoutOfApplication();
	}
	
}
