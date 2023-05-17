/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Auth Service class test cases
* </p>
*/

package webTester;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.github.javafaker.Faker;

class AuthServiceTest {

	private WebsiteTester WT;
	private AuthService AS;
	static String email = "wifole6425@glumark.com"; 
	static String password = "a12345678.";
	
	
	
	@BeforeAll
	@Tag("AuthTests")
	static void configure() {
		
		//WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	}
	
	@BeforeEach
	@Tag("AuthTests")
	void setWebsiteUrl() throws InterruptedException {
		WT = new WebsiteTester("https://www.trendyol.com/");
		AS = new AuthService(WT.getDriver(),WT.getNavigator());
		Thread.sleep(1000);
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Login with Invalid Credentials attempt")
	void invalidLoginTest() throws InterruptedException {
		Faker faker = new Faker();

		String fakeEmail = faker.internet().emailAddress();
		String password = faker.internet().password();
		
		AS.signIn(fakeEmail, password);
		Thread.sleep(500);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[text()='E-posta adresiniz ve/veya şifreniz hatalı.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Login with valid Credentials attempt")
	void validLoginTest() throws InterruptedException {
		AS.signIn(email, password);
		Thread.sleep(1000);
		WebElement myProfileBtn = WT.getDriver().findElement(By.xpath("//div[contains(@class,'account-user')]"));
		myProfileBtn.click();
		Thread.sleep(1000);
		assertDoesNotThrow(() -> WT.getDriver().findElement(By.xpath("//section[@class='section-user_info']/p")));
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register, email verification test")
	void registerEmailVerificationTest() throws InterruptedException {
		Faker faker = new Faker();

		String fakeEmail = faker.internet().emailAddress();
		String password = faker.internet().password();
		
		AS.signUp(fakeEmail, password);
		Thread.sleep(1500);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[text()='E-POSTA DOĞRULAMA']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with used email test")
	void registerWithUsedEmailTest() throws InterruptedException {
		AS.signUp(email, password);
		Thread.sleep(1000);
		assertDoesNotThrow(() -> WT.getDriver().findElement(By.xpath("//div[@id='error-box-wrapper']/ span[text()='Bu e-posta adresi kullanılamaz. Lütfen başka bir e-posta adresi deneyiniz.']")));
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Email verification attempt with wrong verification code")
	void invalidEmailVerificationTest() throws InterruptedException {
		Faker faker = new Faker();

		String fakeEmail = faker.internet().emailAddress();
		String password = faker.internet().password();
		String fakeCode = faker.number().digits(6);
		AS.signUp(fakeEmail, password);
		Thread.sleep(1500);
		AS.sendVerificationCode(fakeCode);
		Thread.sleep(1500);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[text()='Doğrulama kodu bulunamadı.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Resend Email verification code attempt before time is elapsed")
	void resendVerificationInvalidTest() throws InterruptedException {
		Faker faker = new Faker();
		String fakeEmail = faker.internet().emailAddress();
		String password = faker.internet().password();
		AS.signUp(fakeEmail, password);
		Thread.sleep(500);
		WebElement resend = AS.resendVerificationCode();
		assertFalse(resend.isEnabled());	
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Resend Email verification code attempt after time is elapsed")
	void resendVerificationValidTest() throws InterruptedException {
		Faker faker = new Faker();
		String fakeEmail = faker.internet().emailAddress();
		String password = faker.internet().password();
		AS.signUp(fakeEmail, password);
		Thread.sleep(1500);
		WebElement counter = WT.getDriver().findElement(By.xpath("//div[@class='evm-form-counter']"));
		int waitingTime =  Integer.parseInt(counter.getText().substring(1, counter.getText().indexOf("Saniye")-1));
		Thread.sleep((waitingTime + 5)*1000);
		WebElement resend = AS.resendVerificationCode();
		assertTrue(resend.isEnabled());	
	}
	

	
	
	@AfterEach
	@Tag("AuthTests")
	void closeWindow() throws InterruptedException {
		Thread.sleep(1000);
		WT.getDriver().quit();
	}
	

}
