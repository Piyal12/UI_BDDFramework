package stepdef.phoenixMobile;

import context.photon.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


public class OrderListSD {
	
	private TestContext tc;
		
	public OrderListSD (TestContext tc) {
		this.tc = tc;
	}
	
	@Then("^Check if the user is on the order list page$")
	public void checkTheOrderListPage () throws InterruptedException {
		this.tc.po.getOrderListPage().checkTheOrderListPage();
	}
	
	
	@And("^User navigate to first order$")
	public void naviateToOrder () throws InterruptedException {
		this.tc.po.getOrderListPage().naviateToOrder();		
	}
	
}
