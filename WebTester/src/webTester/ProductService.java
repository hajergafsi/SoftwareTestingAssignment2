/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for testing some product-related features in the website like adding/removing from favourites
* </p>
*/

package webTester;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductService {
	WebDriver driver;
	Navigator navigator;
	
	

	
	public ProductService(WebDriver driver,Navigator navigator) {
		this.driver = driver;
		this.navigator = navigator;
	}
	
	public int getNumberOfFavs() {
		WebElement favourites = driver.findElement(By.xpath("//a[contains(@class,'account-favorites')]"));
		favourites.click();
		List<WebElement> closeButtons = driver.findElements(By.xpath("//div[i[@class='i-close']]"));
		return closeButtons.size();
	}
	
	public void navigateToAnyCategory() throws InterruptedException {
		WebElement category = driver.findElement(By.xpath("//*[contains(@class,'category-header')]"));
		category.click();
	}
	
	public void navigateToNthCategory(int n) throws InterruptedException {
		WebElement category = driver.findElement(By.xpath("(//*[contains(@class,'category-header')])["+String.valueOf(n)+"]"));
		category.click();
	}
	
	public WebElement findAnyProduct() throws InterruptedException {
		WebElement productWidget = driver.findElement(By.xpath("//div[@class='widget-product']"));
		return productWidget;
	}
	
	public String addToFavourites() throws InterruptedException {
		navigateToAnyCategory();
		Thread.sleep(3500);
		String productName = findAnyProduct().getAttribute("title");
		WebElement favBtn = driver.findElement(By.xpath("//div[@title='" + productName +"']/div[@class='fvrt-btn-wrppr']"));
		favBtn.click();
		Thread.sleep(3000);	
		return productName;
	}
	
	public String addToFavourites(int n) throws InterruptedException {
		navigateToNthCategory(n);
		Thread.sleep(3500);
		String productName = findAnyProduct().getAttribute("title");
		WebElement favBtn = driver.findElement(By.xpath("//div[@title='" + productName +"']/div[@class='fvrt-btn-wrppr']"));
		favBtn.click();
		Thread.sleep(3000);	
		return productName;
	}
	
	public void addToFavourites(String productName) throws InterruptedException {
		navigateToAnyCategory();
		Thread.sleep(3000);
		WebElement favBtn = driver.findElement(By.xpath("//div[@title='" + productName +"']/div[@class='fvrt-btn-wrppr']"));
		favBtn.click();
		Thread.sleep(3000);	
	}
	
	public boolean isFavourite(String productName) throws InterruptedException {
		getNumberOfFavs();
		WebElement favourites = driver.findElement(By.xpath("//a[contains(@class,'account-favorites')]"));
		favourites.click();
		Thread.sleep(3500);
		WebElement favouriteProduct = driver.findElement(By.xpath("//span[contains(@class,'prdct-desc-cntnr-name') and @title=\""+  productName + "\"]"));
		return favouriteProduct.isDisplayed();
	}
	
	public void emptyFavourites() throws InterruptedException {
		WebElement favourites = driver.findElement(By.xpath("//a[contains(@class,'account-favorites')]"));
		favourites.click();
		List<WebElement> closeButtons = driver.findElements(By.xpath("//div[i[@class='i-close']]"));
		closeButtons.forEach((item) -> {
			item.click();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("couldn't wait");
				e.printStackTrace();
			}

		});
		Thread.sleep(2500);
	}
	
	
}
