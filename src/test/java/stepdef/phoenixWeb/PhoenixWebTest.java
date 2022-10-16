package stepdef.phoenixWeb;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class PhoenixWebTest {
	
	public WebDriver driver;

	@Given("^User launches the web application$")
	public void user_launches_the_web_application() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://admin:1nfr4.m4rk3t@web.inframarket.cloud/");
	}


	@When("^User enters the mobile number$")
	public void user_enters_the_mobile_number() {
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("9972311465");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

	}


	@When("^User enters the otp$")
	public void user_enters_the_otp() {
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 1')]")).sendKeys("1");
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 2')]")).sendKeys("2");
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 3')]")).sendKeys("3");
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 4')]")).sendKeys("4");
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 5')]")).sendKeys("5");
		driver.findElement(By.xpath("//input[contains(@aria-label,'Digit 6')]")).sendKeys("6");

	}

	@Then("^Check if the user is on the credit list page$")
	public void check_if_the_user_is_on_the_credit_list_page() {

		@SuppressWarnings("unused")
		WebElement credit = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Credit']")));
		String creditOption = driver.findElement(By.xpath("//span[normalize-space()='Credit']")).getText();
		Assert.assertEquals(creditOption, "Credit");

	}

	@Then("^navigates to the Applications page$")
	public void navigates_to_the_applications_page() {
		driver.findElement(By.xpath("//span[normalize-space()='Credit']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Applications']")).click();

	}

	@Then("^user navigates to the first Credit Application$")
	public void user_navigates_to_the_first_credit_application() {
		driver.findElement(By.xpath("//p[normalize-space()='CRED2556']")).click();

	}

	@Then("^user logs out of the application$")
	public void user_logs_out_of_the_application() {
	driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M12 2C6.48')]")).click();
	driver.findElement(By.xpath("//p[normalize-space()='Logout']")).click();
	driver.quit();

	}

}
