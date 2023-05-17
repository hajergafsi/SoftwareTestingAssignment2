/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for closing the popups inside the website like cookies popup
* </p>
*/

package webTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PopupsManager {
	WebDriver driver;
	
	public PopupsManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public void closeCookiesPopup () {
			WebElement btn = driver.findElement(By.id("onetrust-accept-btn-handler"));
			btn.click();	
			System.out.println("Cookies accepted !");
		
	}
}
