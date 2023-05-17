/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	UpdateUser class test cases Suite
* </p>
*/

package webTester;

import static org.junit.Assert.assertEquals;
import com.github.javafaker.Faker;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class UpdateUserTest {
	
	private WebsiteTester WT;
	private static AuthService AS;
	static String email = "xepetir866@andorem.com"; 
	static String password = "password123.";
	
	@BeforeAll
	static void configure() {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver.exe");
	}
	
	@BeforeEach
	void setWebsiteUrl() throws InterruptedException {
		WT = new WebsiteTester("https://www.trendyol.com/");
		AS = new AuthService(WT.getDriver(),WT.getNavigator());
		Thread.sleep(1000);
	}


	@AfterEach
	void closeWindow() throws InterruptedException {
		Thread.sleep(1000);
		WT.getDriver().quit();
	}


	
	@Test
	@Order(1)
	@Tag("updateUserTests")
	@DisplayName("Reset password with wrong old password")
	void resetPasswordWrongTest() throws InterruptedException {
		AS.signIn(email,password);		
		Thread.sleep(2500);
		AS.resetPassword("wrong",password+'0');
		Thread.sleep(2500);
		assertEquals(WT.getDriver().getCurrentUrl(),"https://www.trendyol.com/Hesabim/KullaniciBilgileri");
	}
	
	@Test
	@Tag("updateUserTests")
	@DisplayName("update user info test")
	void userInfoUpdateTest() throws InterruptedException {
		AS.signIn(email,password);		
		Thread.sleep(2500);
		Faker faker= new Faker();
		String first = faker.name().firstName(), last =faker.name().lastName();
		AS.updateUserInfo(first,last ,"male");
		Thread.sleep(3000);
		WT.getNavigator().navigateTo("/Hesabim/KullaniciBilgileri");
		Thread.sleep(2500);
		assertEquals(AS.getFieldValue("firstname"),first);
		assertEquals(AS.getFieldValue("lastname"),last);
		assertEquals(AS.VerifyGender("male"),"true");
	}
	
	


}
