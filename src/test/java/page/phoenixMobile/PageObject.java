package page.phoenixMobile;

import im.ui.session.mobile.Session;

public class PageObject {

    private Session session;

    public PageObject (Session session) {
        this.session = session;
    }

    public BasePage getBasePage() {
        return new BasePage(session);
    }

    public LoginScreen getLoginScreen() {
        return new LoginScreen(session);
    }
}
