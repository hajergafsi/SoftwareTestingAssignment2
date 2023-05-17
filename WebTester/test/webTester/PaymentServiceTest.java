/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Payment Service class test cases
* </p>
*/

package webTester;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class PaymentServiceTest {
	
	private WebsiteTester WT;
	private PaymentService paymentService;
	private CartService cartService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	void setUp() throws Exception {
		WT = new WebsiteTester("https://www.trendyol.com/");
		paymentService = new PaymentService(WT.getDriver(),WT.getNavigator());
		cartService = new CartService(WT.getDriver(),WT.getNavigator());
		Thread.sleep(1000);
		WT.closePopups();
		Thread.sleep(1000);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		Thread.sleep(1000);
		WT.getDriver().quit();
	}

	@Test
	@Tag("PaymentTests")
	@DisplayName("Go to payment page without authenticating")
	void paymentNoAuthTest() throws InterruptedException {
		paymentService.goToPaymentPage();
		Thread.sleep(5000);
		assertTrue(WT.getDriver().getCurrentUrl().contains(WT.getUrl()+"giris"));
	}
	
	@Test
	@Tag("PaymentTests")
	@DisplayName("Go to payment page after authenticating")
	void paymentWithAuthTest() throws InterruptedException {
		WT.getAuth().signInExitant();
		Thread.sleep(2000);
		paymentService.goToPaymentPage();
		Thread.sleep(3000);
		assertDoesNotThrow(()->WT.getDriver().findElement(By.xpath("//p[@class='p-tab-title' and text()='Ödeme Seçenekleri']")) );
	}
	
	@Test
	@Tag("PaymentTests")
	@DisplayName("Payment page total cost control")
	void paymentCostTest() throws Exception {
		WT.getAuth().signInExitant();
		Thread.sleep(2000);
		cartService.emptyCart();
		Thread.sleep(3000);
		Product pr = cartService.addElementToCart(2, 1, "Spor&Outdoor");
		Thread.sleep(3000);
		Product pr2 = cartService.addElementToCart(10, 2, "Spor&Outdoor");
		Thread.sleep(3000);
		paymentService.goToPaymentPage();
		Thread.sleep(5000);
		assertEquals(paymentService.getNetTotal(),cartService.getTotal());
	}
	
	@Test
	@Tag("PaymentTests")
	@DisplayName("Payment page with empty cart error message test")
	void paymentWithEmptyCartTest() throws Exception {
		WT.getAuth().signInExitant();
		Thread.sleep(2000);
		cartService.emptyCart();
		Thread.sleep(3000);
		paymentService.goToPaymentPage();
		Thread.sleep(3000);
		assertDoesNotThrow(()->WT.getDriver().findElement(By.xpath("//div[@class='p-warn-modal-primary']/div[@class='p-wm-primary-content' and text()='Ürün seçimi yapmalısınız ya da seçtiğiniz ürünler tükendi.']")));
	}



}
