/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class that holds the web driver and has an instance of each component responsible for testing a particular area of the website
* </p>
*/

package webTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebsiteTester {
	private String url;
	private ChromeOptions options;
	private WebDriver driver;
	private Navigator nav;
	private AuthService authService;
	private PopupsManager popupsManager;
	private ProductService productService;
	//WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker();
	
	public WebsiteTester( String url) throws InterruptedException {
		 this.url = url;
		 
		 this.options = new ChromeOptions();
		 options.addArguments("--disable-notifications");
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setBrowserName("firefox");
	     capabilities.setCapability("marionette", true);
		 this.driver = new FirefoxDriver();
		 //driver = wdm.create();
		 this.nav = new Navigator(driver,url);
		 this.authService = new AuthService(driver,nav);
		 this.popupsManager = new PopupsManager(driver);
		 this.productService = new ProductService(driver,nav);
		 
		 
		 driver.manage().window().maximize();
		 driver.get(url);
		 Thread.sleep(1500);
		 closePopups();
	}
	
	public void closePopups() {
		try {
			popupsManager.closeCookiesPopup();
			Thread.sleep(500);
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public Navigator getNavigator() {
		return this.nav;
	}
	
	public AuthService getAuth() {
		return this.authService;
	}
}
