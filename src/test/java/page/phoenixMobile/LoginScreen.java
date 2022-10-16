package page.phoenixMobile;

import im.ui.session.mobile.Session;

import java.text.MessageFormat;

public class LoginScreen extends BasePage {

    public enum LoginScreenLocators {

        UPDATE_IGNORE_BUTTON("xpath=//android.widget.Button[@resource-id='android:id/button1']"),
        POP_UP_YES_BUTTON("xpath=//android.widget.Button[@resource-id='com.implatformmobile:id/primary_button']"),
        PHONE_NO_TEXT_BOX("xpath=//android.widget.EditText[@content-desc='Enter Phone Number']"),
        SIGN_IN_WITH_OTP_BUTTON("xpath=//android.view.ViewGroup[@content-desc='Sign in with OTP Button']/android.widget.TextView"),
        OTP_TEXT_BOX("xpath=//android.widget.EditText[@content-desc='Enter OTP']"),
        VERIFY_BUTTON("xpath=//android.view.ViewGroup[@content-desc='Verify Button']");

        private final String myLocator;

        LoginScreenLocators(String locator) {
            myLocator = locator;
        }

        public String get() {
            return myLocator;
        }

        public String get(Object... params) {
            return MessageFormat.format(myLocator, params);
        }
    }


    public LoginScreen(Session session) {
        super(session);
    }


    public void tapUpdateIgnoreButton() {
        this.element(LoginScreenLocators.UPDATE_IGNORE_BUTTON.get()).waitUntil().present().perform().tap();
    }

    public void tapPopUpYesButton() {
        this.element(LoginScreenLocators.POP_UP_YES_BUTTON.get()).waitUntil().present().perform().tap();
    }

    public void enterPhoneNumber(String phoneNumber) {
        this.element(LoginScreenLocators.PHONE_NO_TEXT_BOX.get()).waitUntil().present().perform().setText(phoneNumber);
    }

    public void tapSignInWithOtpButton() {
        this.element(LoginScreenLocators.SIGN_IN_WITH_OTP_BUTTON.get()).waitUntil().perform().tap();
    }

    public void enterOtp() {
        this.element(LoginScreenLocators.OTP_TEXT_BOX.get()).waitUntil().present().perform().setText("123456");
    }

    public void tapVerifyButton() {
        this.element(LoginScreenLocators.VERIFY_BUTTON.get()).waitUntil().perform().tap();
    }


}
