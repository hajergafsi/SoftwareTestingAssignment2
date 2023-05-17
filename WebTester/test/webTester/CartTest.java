/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Cart Service class test cases
* </p>
*/

package webTester;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

class CartTest {
	
	private WebsiteTester WT;
	private CartService cartService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	void setUp() throws Exception {
		WT = new WebsiteTester("https://www.trendyol.com/");
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
	@Tag("CartTests")
	@DisplayName("Add a product to cart")
	void addSingleToCarttest() throws Exception {
		Product pr = cartService.addElementToCart(2, 1, "Erkek");
		Thread.sleep(2500);
		boolean isInCartPage = cartService.isElementInCart(pr);
		Thread.sleep(2500);
		assertTrue(isInCartPage);
	}
	
	@Test
	@Tag("CartTests")
	@DisplayName("Add a product many times to cart")
	void addMultipleToCarttest() throws Exception {
		Product pr = cartService.addElementToCart(9, 3, "Erkek");
		Thread.sleep(2500);
		boolean isInCartPage = cartService.isElementInCart(pr);
		Thread.sleep(2500);
		assertTrue(isInCartPage);
	}
	
	@Test
	@Tag("CartTests")
	@DisplayName("Increase item count inside cart")
	void increaseCountTest() throws Exception {
		Product pr = cartService.addElementToCart(11, 1, "Erkek");
		Thread.sleep(2500);
		Product temp = new Product(pr.getName(),pr.getPrice(),1);
		Thread.sleep(1500);
		cartService.increaseCount(temp);
		Thread.sleep(5000);
		boolean isInCartPage = cartService.isElementInCart(pr);
		Thread.sleep(2500);
		assertTrue(isInCartPage);
	}
	
	@Test
	@Tag("CartTests")
	@DisplayName("Decrease item count inside cart")
	void decreaseCountTest() throws Exception {
		Product pr = cartService.addElementToCart(30, 2, "Erkek");
		Thread.sleep(2500);
		Product temp = new Product(pr.getName(),pr.getPrice(),1);
		cartService.decreaseCount(temp);
		Thread.sleep(5000);
		boolean isInCartPage = cartService.isElementInCart(pr);
		assertTrue(isInCartPage);
	}
	
	@Test
	@Tag("CartTests")
	@DisplayName("Remove item from cart")
	void removeItemTest() throws Exception {
		Product pr = cartService.addElementToCart(30, 2, "Erkek");
		Thread.sleep(2500);
		assertThrows(NoSuchElementException.class,() -> cartService.removeProductFromCart(pr));
	}
	
	@Test
	@Tag("CartTests")
	@DisplayName("Calculate discounts and control final total cost")
	void totalAfterDiscountTest() throws Exception {
		Product pr = cartService.addElementToCart(30, 2, "Erkek");
		Thread.sleep(3500);
		Product pr1 = cartService.addElementToCart(2, 1, "Kadın");
		Thread.sleep(3000);
		Product pr2 = cartService.addElementToCart(10, 2, "Spor&Outdoor");
		Thread.sleep(3000);
		cartService.goToCart();
		Thread.sleep(2500);
		assertEquals(cartService.calculateTotal(),cartService.getDisplayedTotal());
	}
	
	

}
