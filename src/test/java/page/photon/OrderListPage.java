package page.photon;

import org.openqa.selenium.By;
import org.testng.Assert;

import im.ui.session.web.Session;

public class OrderListPage extends BasePage {

	public OrderListPage(Session session) {
		super(session);
	}

	public void checkTheOrderListPage() throws InterruptedException {
		String aHeader = driver.findElement(By.xpath("//div[@id='root']/div/main/div[1]/div[1]/p")).getText();
		Assert.assertEquals(aHeader, "Order List");
		Thread.sleep(1000);
	}
	
	public void naviateToOrder () throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/div/a[1]/div/span")).click();
		Thread.sleep(1000);	
	}

}
