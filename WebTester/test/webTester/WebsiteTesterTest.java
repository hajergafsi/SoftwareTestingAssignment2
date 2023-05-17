/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	WebsiteTester class test cases
* </p>
*/

package webTester;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class WebsiteTesterTest {
	
	private WebsiteTester WT;
	
	@BeforeAll
	static void configure() {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}
	
	@BeforeEach
	void setWebsiteUrl() throws InterruptedException {
		WT = new WebsiteTester("https://www.trendyol.com/");
		Thread.sleep(1000);
	}
	
	@Test
	@Tag("WebsiteAccessTest")
	@DisplayName("Accepts the cookies")
	void cookiesTest() throws InterruptedException {
		assertDoesNotThrow(() -> WT.closePopups());
		Thread.sleep(1500);
		WT.getDriver().quit();
	}
	
	@Test
	@Tag("WebsiteAccessTest")
	@DisplayName("Opens homepage with Trendyol Logo")
	void logoTest() throws InterruptedException {
		WebDriver driver = WT.getDriver();
		assertDoesNotThrow(() -> { driver.findElement(By.xpath("//img[@src=\"https://cdn.dsmcdn.com/web/logo/ty-web.svg\"]"));});
		Thread.sleep(500);
		driver.quit();
	}

}
