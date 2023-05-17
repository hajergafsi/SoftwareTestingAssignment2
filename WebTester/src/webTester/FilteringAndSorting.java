/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for product sorting and filtering operations in the website
* </p>
*/

package webTester;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class FilteringAndSorting {
	WebDriver driver;
	Navigator navigator;
	
	public FilteringAndSorting(WebDriver driver,Navigator navigator) {
		this.driver = driver;
		this.navigator = navigator;
	}
	
	public void goTocategoryPage(String category) throws InterruptedException {
		navigator.navigateTo(category);
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page by 500 pixels
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
	
	public double formatPrice(String price) {
		double formatted = Double.parseDouble(price.replace(',','.').substring(0,price.indexOf("TL")-1));
		return formatted;
	}
	
	public void selectPriceRangeWithRadioBtn(int min, int max) {
		WebElement button = driver.findElement(By.xpath("//div[@class='fltrs']/a[@class='fltr-item-wrppr' and contains(@href,'"+min+"-"+max+"')]"));
		button.click();
	}
	
	public void enterMaxMin(int min, int max) throws InterruptedException {
		WebElement minInput = driver.findElement(By.xpath("//input[contains(@class,'fltr-srch-prc-rng-input') and contains(@class,'min')]"));
		minInput.sendKeys(String.valueOf(min));
		Thread.sleep(2500);
		WebElement maxInput = driver.findElement(By.xpath("//input[contains(@class,'fltr-srch-prc-rng-input') and contains(@class,'max')]"));
		maxInput.sendKeys(String.valueOf(max));	
		Thread.sleep(2500);
		WebElement searchBtn = driver.findElement(By.xpath("//div[@class='fltr-srch-prc-rng']/button[@class='fltr-srch-prc-rng-srch']"));
		searchBtn.click();
	}
	
	public int getNumberOfProducts() throws InterruptedException {
		return driver.findElements(By.xpath("//div[contains(@class,'p-card-wrppr')]")).size();
	}
	
	public void togglePriceFilterDropdown()
	{
		WebElement priceBtn = driver.findElement(By.xpath("//div[@class='fltrs-wrppr hide-fltrs' and div[div[@class='fltr-cntnr-ttl' and text()='Fiyat']]]"));
		priceBtn.click();
	}
	public double getPriceByProductOrder(int order) {
		String price = driver.findElement(By.xpath("//div[contains(@class,'p-card-wrppr') and position()=" + String.valueOf(order) + "] "
				+ "/div/a/div[@class='product-down']/div[@class='price-promotion-container']/div/div[@class='prc-box-dscntd']")).getText();
		return(formatPrice(price));
	}
	
	
	public void setFreeShippingFilter() throws InterruptedException {
		try {
			WebElement freeShippingBtn = driver.findElement(By.xpath("//button[@class='quick-filters-item']/span[text()='Kargo Bedava']"));
			freeShippingBtn.click();			
		}catch(Exception e) {
			WebElement freeShippingBtn = driver.findElement(By.xpath("//a[div[contains(@class,'fltr-item-text') and text()='Kargo Bedava']]"));
			freeShippingBtn.click();
		}

	}
	
	public int getNumberOfFreeShippingArticles() {
		List<WebElement> freeShipping = driver.findElements(By.xpath("//div[contains(@class,'p-card-wrppr')]/div/a/div[@class='image-container']/div[@class='image-overlay']/div/div[@class='product-stamps-container']/div[contains(@class,'freeCargo')]"));
		return freeShipping.size();
	}
	
	public void setSortingOption(String option) throws InterruptedException {
		try {
			WebElement sortingBtn = driver.findElement(By.xpath("//div[@class='search-sort-container']"));
			sortingBtn.click();
			Thread.sleep(2000);
			WebElement optionElem = driver.findElement(By.xpath("//ul[@class='search-dropdown']/li[text()='"+ option +"']"));
			optionElem.click();			
		}catch(Exception e) {
			WebElement sortingBtn = driver.findElement(By.xpath("//div[@class='sort-fltr-cntnr']"));
			sortingBtn.click();
			Thread.sleep(2000);
			WebElement optionElem = driver.findElement(By.xpath("//option[text()='"+ option +"']"));
			optionElem.click();
		}

	}
	
	
}
