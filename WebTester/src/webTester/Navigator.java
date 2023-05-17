/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for navigating to sub-pages inside the website
* </p>
*/

package webTester;

import org.openqa.selenium.WebDriver;

public class Navigator {
	WebDriver driver;
	String url;
	
	public Navigator(WebDriver driver,String url) {
		this.driver = driver;
		this.url = url;
	}
	
	public void navigateTo(String page) {
		driver.get(url + page);
	}
}
