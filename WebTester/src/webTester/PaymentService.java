/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for testing some of the payment operations in the website
* </p>
*/

package webTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentService {
	WebDriver driver;
	Navigator navigator;
	Cart cart;
	
	public double formatPrice(String price) {
		double formatted = Double.parseDouble(price.replace(',','.').substring(0,price.indexOf("TL")-1));
		return formatted;
	}
	
	public PaymentService(WebDriver driver,Navigator navigator) {
		this.driver = driver;
		this.navigator = navigator;
		this.cart = new Cart();
	}
	
	public void goToPaymentPage() {
		navigator.navigateTo("/sepetim/odeme");
	}
	
	public double getNetTotal() {
		WebElement netTotal = driver.findElement(By.xpath("//p[span[text()='Ara Toplam']]/span[position()=2]"));
		return formatPrice(netTotal.getText());
	}
}
