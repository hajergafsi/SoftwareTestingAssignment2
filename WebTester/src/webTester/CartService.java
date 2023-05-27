/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for executing cart operations in the website
* </p>
*/


package webTester;

import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CartService {
	WebDriver driver;
	Navigator navigator;
	Cart cart;
	
	public CartService(WebDriver driver,Navigator navigator) {
		this.driver = driver;
		this.navigator = navigator;
		this.cart = new Cart();
	}
	
	public double formatPrice(String price) {
		//System.out.println(price);
		double formatted = Double.parseDouble(price.replace(',','.').substring(0,price.indexOf("TL")-1));
		return formatted;
	}
	
	
	public String formatProductName(String name) {
		int lastIndex = name.trim().lastIndexOf(" ");
		String formatted = name.substring(0, lastIndex);
		return formatted;

	}
	
	public void openCategoryPage(String category) {
		WebElement categoryElement = driver.findElement(By.xpath("//*[contains(@class,'category-header') and text()='"+ category + "']"));
		categoryElement.click();		
	}
	
	public void openAnyCategoryPage() {
		WebElement category = driver.findElement(By.xpath("//*[@class='category-header']"));
		category.click();		
	}
	
	public void goToNthProductPage(int n, String category) throws Exception {
		openCategoryPage(category);
		Thread.sleep(2500);
		List<WebElement> allProducts = driver.findElements(By.xpath("//*[@class='widget-product']"));
		if(n > allProducts.size()) {
			throw new IllegalArgumentException("Wrong index");	
		};
		allProducts.get(n).click();
	}
	
	public Product findProductDetails() {
		Product product = new Product();
		WebElement price = driver.findElement(By.xpath("//span[@class='prc-dsc']"));
		WebElement title = driver.findElement(By.xpath("//*[@class='product-container']/div[2]/div[@class='container-right-content']/div[@class='pr-in-w']/div/div/div/h1[@class='pr-new-br']"));
		product.setPrice(formatPrice(price.getText()));
		product.setName(formatProductName(title.getText()));
		return product;
	}
	
	public void goToCart() throws InterruptedException {
		navigator.navigateTo("sepet");
	}
	
	public String findDiscount(WebElement price) {
		String filtered, innerHTML = price.getAttribute("innerHTML");
		if(innerHTML.contains("</span>")) {
			filtered = innerHTML.substring(innerHTML.indexOf("</span>")+"</span>".length());
			return filtered;
		}
		return price.getText();
	}
	
	public boolean isElementInCart(Product product) throws InterruptedException {
		goToCart();
		Thread.sleep(2000);
		String parentElementXpath = "//div[@class='pb-basket-item' and div/a/p[@class='pb-item']]";
		String itemDetailXpath = parentElementXpath + "/div/div[@class='pb-basket-item-actions-info']";
		WebElement price = driver.findElement(By.xpath(itemDetailXpath + "/div/div/div[@class='pb-basket-item-price']"));
		WebElement quantity = driver.findElement(By.xpath(itemDetailXpath + "/div/div/div/input[@class='counter-content']"));
		if(formatPrice(findDiscount(price)) != product.getPrice()*product.getQty())return false;
		if(Integer.parseInt(quantity.getAttribute("value")) != product.getQty())return false;
		return true;
	}
	
	public void increaseCount(Product product) throws InterruptedException {
		goToCart();
		Thread.sleep(4000);
		String parentElementXpath = "//div[@class='pb-basket-item' and div/a/p[@class='pb-item']]";
		String itemDetailXpath = parentElementXpath + "/div/div[@class='pb-basket-item-actions-info']";
		WebElement increaseBtn = driver.findElement(By.xpath(itemDetailXpath + "/div/div/div/button[@class='ty-numeric-counter-button' and position()=2]"));
		increaseBtn.click();
		this.cart.addProduct(product);
	}
	
	public void decreaseCount(Product product) throws InterruptedException {
		goToCart();
		Thread.sleep(2000);
		String parentElementXpath = "//div[@class='pb-basket-item' and div/a/p[@class='pb-item']]";
		String itemDetailXpath = parentElementXpath + "/div/div[@class='pb-basket-item-actions-info']";
		WebElement decreaseBtn = driver.findElement(By.xpath(itemDetailXpath + "/div/div/div/button[@class='ty-numeric-counter-button' and position()=1]"));
		decreaseBtn.click();
		this.cart.removeProduct(product);
	}
	
	public void closePopup() {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'campaign-button')]")).click();
		}catch(Exception e) {
			//System.out.println("no campaign popup");
		}
	}
	
	public void removeProductFromCart(Product product) throws InterruptedException {
		goToCart();
		Thread.sleep(2000);
		String parentElementXpath = "//div[@class='pb-basket-item' and div/a/p[@class='pb-item' and contains(@title,\""+ product.getName() +"\")]]";
		String itemDetailXpath = parentElementXpath + "/div[@class='pb-basket-item-actions']";
		WebElement deleteBtn = driver.findElement(By.xpath(itemDetailXpath + "/button/i[@class='i-trash']"));
		deleteBtn.click();
		Thread.sleep(4000);
		WebElement prod = driver.findElement(By.xpath(parentElementXpath));
	}
	
	//Add nth product of X category to cart
	public Product addElementToCart(int n,int qty,String category) throws Exception {
		goToNthProductPage(n,category);
		Thread.sleep(3500);
		closePopup();
		driver.navigate().refresh();
		closePopup();
		Thread.sleep(3500);		
		Product product = findProductDetails();
		product.setQty(qty);
		WebElement addToCart = driver.findElement(By.xpath("//div[@class='add-to-basket-button-text' and text()='Sepete Ekle']"));
		for(int i=0; i<qty;i++) {
			addToCart.click();
			Thread.sleep(7000);
		}
		cart.addProduct(product);
		return product;
	}
	
	
	//Empty cart
	public void emptyCart() throws InterruptedException {
		goToCart();
		Thread.sleep(2000);
		List<WebElement> trashBtns = driver.findElements(By.xpath("//button[i[@class='i-trash']]"));
		trashBtns.forEach(item -> {
			item.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	public double getTotal() {
		return cart.getTotal();
	}
	
	public double calculateTotal() {
		double total = 0;
		List<WebElement> allPrices = driver.findElements(By.xpath("//div[@class='pb-summary-box']/ul/li/strong"));
		for(int i=0;i<allPrices.size();i++) {
			total += formatPrice(allPrices.get(i).getText());
		}
		return total;
	}
	
	public double getDisplayedTotal() {
		WebElement total = driver.findElement(By.xpath("//p[contains(@class,'pb-summary-total-price')]"));
		return formatPrice(total.getAttribute("title"));
	}
	
	
}
