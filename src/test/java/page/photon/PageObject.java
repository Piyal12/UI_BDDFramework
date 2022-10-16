package page.photon;

import org.openqa.selenium.WebDriver;

import im.ui.driver.DriverManager;
import im.ui.session.web.Session;


@SuppressWarnings("unused")
public class PageObject  {
	
	private Session session;
	private LoginPage loginPage;
	private OrderListPage orderListPage;
	
	public PageObject (Session session) {
		this.session = session;
	}
	
	public BasePage getBasePage () {
		return new BasePage(session);
	}
	
	public LoginPage getLoginPage () {
		return new LoginPage(session);
	}
	
	public OrderListPage getOrderListPage () {
		return new OrderListPage(session);
	}
}
