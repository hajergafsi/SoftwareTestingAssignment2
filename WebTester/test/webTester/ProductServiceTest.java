/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Product Service class test cases
* </p>
*/



package webTester;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

class ProductServiceTest {

	private WebsiteTester WT;
	private ProductService productService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	void setUp() throws Exception {
		WT = new WebsiteTester("https://www.trendyol.com/");
		productService = new ProductService(WT.getDriver(),WT.getNavigator());
		WT.closePopups();
		Thread.sleep(1000);
	}

	@AfterEach
	void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		WT.getDriver().quit();
	}

	@Test
	@Tag("ProductTests")
	@DisplayName("Add a product to favourites without auth")
	void addToFavNoAuthtest() throws InterruptedException {
		Thread.sleep(1000);
		productService.addToFavourites();
		Thread.sleep(2000);
		assertTrue(WT.getDriver().getCurrentUrl().contains("https://www.trendyol.com/giris"));
	}

	@Test
	@Tag("ProductTests")
	@DisplayName("Add a product to favourites with auth")
	void addToFavWithAuthtest() throws InterruptedException {
		System.out.println("add a product to favourites");
		WT.getAuth().signInExitant();
		Thread.sleep(3500);
		productService.emptyFavourites();
		Thread.sleep(2000);
		String name = productService.addToFavourites();
		Thread.sleep(3500);
		assertTrue(productService.isFavourite(name));
	}
	
	@Test
	@Tag("ProductTests")
	@DisplayName("Remove a product from favourites")
	void removeFromFavstest() throws InterruptedException {
		System.out.println("Remove a product from favourites");
		WT.getAuth().signInExitant();
		Thread.sleep(3000);
		productService.emptyFavourites();
		Thread.sleep(3000);
		String name = productService.addToFavourites();
		Thread.sleep(3000);
		productService.addToFavourites(name);
		Thread.sleep(3000);
		assertThrows(NoSuchElementException.class,() -> productService.isFavourite(name));
	}

}
