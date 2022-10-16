package stepdef.phoenixMobile;

import context.phoenixMobile.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginScreen {

    private TestContext tc;
    public LoginScreen(TestContext tc) {
        this.tc = tc;
    }

    @Given("^user opens the phoenix app and dismisses the pop ups$")
    public void user_opens_the_phoenix_app_and_dismisses_the_pop_ups() throws InterruptedException {
        Thread.sleep(5000);
        this.tc.po.getLoginScreen().tapUpdateIgnoreButton();
        this.tc.po.getLoginScreen().tapPopUpYesButton();

    }

    @When("^user enters the (.+) and password$")
    public void user_enters_the_and_password(String phoneNumber) {
        this.tc.po.getLoginScreen().enterPhoneNumber(phoneNumber);
        this.tc.po.getLoginScreen().tapSignInWithOtpButton();

    }

    @Then("^logs in$")
    public void logs_in() {
        this.tc.po.getLoginScreen().enterOtp();
        this.tc.po.getLoginScreen().tapVerifyButton();

    }

}
