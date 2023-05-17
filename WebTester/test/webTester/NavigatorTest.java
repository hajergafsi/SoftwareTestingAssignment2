/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Navigator class test cases
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

class NavigatorTest {

	private WebsiteTester WT;
	private Navigator nav;
	
	@BeforeAll
	static void configure() {
		//System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	}
	
	@BeforeEach
	void setWebsiteUrl() throws InterruptedException {
		WT = new WebsiteTester("https://www.trendyol.com/");
		Thread.sleep(1000);
	}
	
	@Test
	@DisplayName("navigates to FAQ page")
	@Tag("NavigationTest")
	void navigateToFaq() throws InterruptedException {
		WebDriver dr = WT.getDriver();
		nav = new Navigator(dr,WT.getUrl());
		nav.navigateTo("yardim/sorular");
		Thread.sleep(500);
		assertDoesNotThrow(()-> {WebElement title = dr.findElement(By.xpath("//h2[text()='Yardım']"));});	
		dr.quit();
	}

}
