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
		Thread.sleep(2500);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[text()='E-posta adresiniz ve/veya şifreniz hatalı.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Login with valid Credentials attempt")
	void validLoginTest() throws InterruptedException {
		AS.signIn(email, password);
		Thread.sleep(2500);
		WebElement myProfileBtn = WT.getDriver().findElement(By.xpath("//div[contains(@class,'account-user')]"));
		myProfileBtn.click();
		Thread.sleep(2500);
		assertDoesNotThrow(() -> WT.getDriver().findElement(By.xpath("//section[@class='section-user_info']/p")));
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with inadequate length password test")
	void shortPasswordTest() throws InterruptedException {
		Faker faker = new Faker();
		WT.getNavigator().navigateTo("uyelik");
		Thread.sleep(2500);
		String fakeEmail = faker.internet().emailAddress();
		String password = faker.lorem().characters(6);
		AS.signUp(fakeEmail, password);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[@class='message' and text()='Şifreniz 7 ile 64 karakter arasında olmalıdır.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with only-letters password warning message test")
	void noNumberPasswordTest() throws InterruptedException {
		Faker faker = new Faker();
		WT.getNavigator().navigateTo("uyelik");
		Thread.sleep(2500);
		String fakeEmail = faker.internet().emailAddress();
		String password = faker.lorem().fixedString(8);
		AS.signUp(fakeEmail, password);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[@class='message' and text()='Şifreniz en az 1 rakam içermelidir.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with only-numbers password warning message test")
	void noLetterPasswordTest() throws InterruptedException {
		Faker faker = new Faker();
		WT.getNavigator().navigateTo("uyelik");
		Thread.sleep(2500);
		String fakeEmail = faker.internet().emailAddress();
		String password = "12345678";
		AS.signUp(fakeEmail, password);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[@class='message' and text()='Şifreniz en az 1 harf içermelidir.']"));});
	}
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with weak password warning message test")
	void weakPasswordTest() throws InterruptedException {
		Faker faker = new Faker();
		WT.getNavigator().navigateTo("uyelik");
		Thread.sleep(2500);
		String fakeEmail = faker.internet().emailAddress();
		String password = "1234567";
		password += faker.regexify("[a-z]+[a-z0-9]*");
		AS.signUp(fakeEmail, password);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[contains(@class,'pw-title') and contains(@class,'weak')]"));});
	}
	
	
	@Test
	@Tag("AuthTests")
	@DisplayName("Register with strong password success message test")
	void strongPasswordTest() throws InterruptedException {
		Faker faker = new Faker();
		WT.getNavigator().navigateTo("uyelik");
		Thread.sleep(2500);
		String fakeEmail = faker.internet().emailAddress();
		String password = "1234567";
		password += faker.regexify("[._!&]+[a-z]+[a-zA-Z0-9]*");
		AS.signUp(fakeEmail, password);
		assertDoesNotThrow(() -> {WT.getDriver().findElement(By.xpath("//span[contains(@class,'pw-title') and contains(@class,'high')]"));});
	}

	
	
	@AfterEach
	@Tag("AuthTests")
	void closeWindow() throws InterruptedException {
		Thread.sleep(1000);
		WT.getDriver().quit();
	}
	

}
