package page.photon;

import java.text.MessageFormat;

import im.ui.session.web.Session;

public class LoginPage extends BasePage {
	
	public enum LoginPageLocator {

        PHONE_NO("xpath=//input[@name='phoneNumber']"),
        LOGIN("xpath=//button[text()='Login']"),
        OTP("xpath=//input[contains(@aria-label,\"Digit {0}\")]"),
		STORE_ICON("xpath=//*[@id=\"root\"]/header/div/button[1]"),
		STORE_SJR("xpath=/html/body/div[2]/div[3]/ul/div[3]/div/p[1]"),
		USER_ICON("xpath=//*[@id=\"root\"]/header/div/button[2]"),
		LOGOUT("xpath=/html/body/div[2]/div[3]/ul/div/div[2]/p[1]");
		
       private final String myLocator;

        LoginPageLocator(String locator) {
            myLocator = locator;
        }

        public String get() {
            return myLocator;
        }

        public String get(Object... params) {
            return MessageFormat.format(myLocator, params);
        }
    }
	
	public LoginPage(Session session) {
		super(session);
	}
	
	public void enterMobileNumber () throws InterruptedException {
		this.element(LoginPageLocator.PHONE_NO.get()).waitUntil().present().perform().setText("9945053050");
	}

	public void enterMobileNumber (String mobileNumber) throws InterruptedException {
		this.element(LoginPageLocator.PHONE_NO.get()).waitUntil().present().perform().setText("9945053050");
	}
	
	public void enterLoginButton () throws InterruptedException {
		this.element(LoginPageLocator.LOGIN.get()).waitUntil().clickable().perform().click();
	}
	
	public void enterOtp () throws InterruptedException {
		this.element(LoginPageLocator.OTP.get("1")).waitUntil().clickable().perform().setText("1");
		this.element(LoginPageLocator.OTP.get("2")).perform().setText("2");
		this.element(LoginPageLocator.OTP.get("3")).perform().setText("3");
		this.element(LoginPageLocator.OTP.get("4")).perform().setText("4");
		this.element(LoginPageLocator.OTP.get("5")).perform().setText("5");
		this.element(LoginPageLocator.OTP.get("6")).perform().setText("6");

	}
	
	public void selectStore () throws InterruptedException {
		this.element(LoginPageLocator.STORE_ICON.get()).waitUntil().clickable().perform().click();
		this.element(LoginPageLocator.STORE_SJR.get()).waitUntil().clickable().perform().click();
	}
	
	public void logoutOfApplication () throws InterruptedException {
		this.element(LoginPageLocator.USER_ICON.get()).perform().click();
		this.element(LoginPageLocator.LOGOUT.get()).waitUntil().clickable().perform().click();
	}

}
