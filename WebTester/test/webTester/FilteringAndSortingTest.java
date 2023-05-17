/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	FilteringAndSorting Service class test cases
* </p>
*/

package webTester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Random;

class FilteringAndSortingTest {
	
	private WebsiteTester WT;
	private FilteringAndSorting filteringAndSorting;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}
	
	@BeforeEach
	void setUp() throws Exception {
		WT = new WebsiteTester("https://www.trendyol.com/");
		filteringAndSorting = new FilteringAndSorting(WT.getDriver(),WT.getNavigator());
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
	@Tag("FilteringTests")
	@DisplayName("Test filtering by price buttons with a random product from page")
	void priceFilteringButtonTest() throws InterruptedException {
		filteringAndSorting.goTocategoryPage("ayakkabi");
		Thread.sleep(2500);
		filteringAndSorting.togglePriceFilterDropdown();
		Thread.sleep(2000);
		filteringAndSorting.selectPriceRangeWithRadioBtn(0, 150);
		Thread.sleep(3000);
		Random rnd = new Random();
		int order = rnd.nextInt(filteringAndSorting.getNumberOfProducts());
		Thread.sleep(2000);
		assertTrue(filteringAndSorting.getPriceByProductOrder(order) < 150) ;
	}
	
	@Test
	@Tag("FilteringTests")
	@DisplayName("Test filtering by price min max fields with a random product from page")
	void priceFilteringMinMaxfieldsTest() throws InterruptedException {
		filteringAndSorting.goTocategoryPage("ayakkabi");
		Thread.sleep(2500);
		filteringAndSorting.togglePriceFilterDropdown();
		Thread.sleep(2000);
		filteringAndSorting.enterMaxMin(150, 200);
		Thread.sleep(3000);
		Random rnd = new Random();
		int order = rnd.nextInt(filteringAndSorting.getNumberOfProducts());
		Thread.sleep(2000);
		assertTrue(filteringAndSorting.getPriceByProductOrder(order) < 200) ;
		assertTrue(filteringAndSorting.getPriceByProductOrder(order) > 150) ;
	}

	@Test
	@Tag("FilteringTests")
	@DisplayName("Test filtering by free shipping")
	void freeShippingFilterTest() throws InterruptedException {
		filteringAndSorting.goTocategoryPage("pantolon");
		Thread.sleep(2500);
		filteringAndSorting.setFreeShippingFilter();
		Thread.sleep(2000);
		assertEquals(filteringAndSorting.getNumberOfFreeShippingArticles(),filteringAndSorting.getNumberOfProducts());
	}
	
	/*@Test
	@Tag("SortingTests")
	@DisplayName("Test sorting by ascending price")
	void sortingByAscPriceTest() throws InterruptedException {
		filteringAndSorting.goTocategoryPage("elbise");
		Thread.sleep(2500);
		filteringAndSorting.setSortingOption("En düşük fiyat");
		Thread.sleep(2000);
		Random rnd = new Random();
		int orderProd1 = rnd.nextInt(filteringAndSorting.getNumberOfProducts() - 2);
		int orderProd2 = rnd.nextInt(orderProd1+1,filteringAndSorting.getNumberOfProducts());
		Thread.sleep(2000);
		assertTrue(filteringAndSorting.getPriceByProductOrder(orderProd1) <= filteringAndSorting.getPriceByProductOrder(orderProd2));
	}
	
	@Test
	@Tag("SortingTests")
	@DisplayName("Test sorting by descending price")
	void sortingByDescPriceTest() throws InterruptedException {
		filteringAndSorting.goTocategoryPage("canta");
		Thread.sleep(3500);
		filteringAndSorting.setSortingOption("En yüksek fiyat");
		Thread.sleep(2000);
		Random rnd = new Random();
		int orderProd1 = rnd.nextInt(filteringAndSorting.getNumberOfProducts() - 2);
		int orderProd2 = rnd.nextInt(orderProd1+1,filteringAndSorting.getNumberOfProducts());
		Thread.sleep(2000);
		assertTrue(filteringAndSorting.getPriceByProductOrder(orderProd1) >= filteringAndSorting.getPriceByProductOrder(orderProd2));
	}*/
	
	
}
