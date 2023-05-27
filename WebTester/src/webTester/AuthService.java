/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for testing auth operations
* </p>
*/

package webTester;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthService {
	
	WebDriver driver;
	Navigator navigator;
	
	public AuthService(WebDriver driver,Navigator navigator) {
		this.driver = driver;
		this.navigator = navigator;
	}
	
	public void signIn(String email,String password) throws InterruptedException {
		navigator.navigateTo("giris");
		Thread.sleep(2000);
		WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
		WebElement passField = driver.findElement(By.xpath("//input[@id='login-password-input']"));
		WebElement sumbitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		emailField.sendKeys(email);
		passField.sendKeys(password);
		Thread.sleep(1000);
		sumbitBtn.click();
	}
	
	public void signUp(String email,String password) throws InterruptedException {
		navigator.navigateTo("uyelik");
		WebElement emailField = driver.findElement(By.xpath("//input[@id='register-email']"));
		WebElement passField = driver.findElement(By.xpath("//input[@id='register-password-input']"));
		WebElement checkBox = driver.findElement(By.xpath("//div[span[div[span[text()='aydınlatma metnini']]]]/div[contains(@class,'ty-checkbox-container')]"));
		WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		emailField.sendKeys(email);
		passField.sendKeys(password);
		checkBox.click();
		Thread.sleep(1000);
		submitBtn.click();
	}
	
	
	public void fillSignUpFields(String email,String password) throws InterruptedException {
		navigator.navigateTo("uyelik");
		Thread.sleep(2500);
		WebElement emailField = driver.findElement(By.xpath("//input[@id='register-email']"));
		WebElement passField = driver.findElement(By.xpath("//input[@id='register-password-input']"));
		emailField.sendKeys(email);
		passField.sendKeys(password);
	}
	
	
	public void sendVerificationCode(String code) throws InterruptedException {
		WebElement codeField = driver.findElement(By.xpath("//input[@name='code']"));
		WebElement submitBtn = driver.findElement(By.xpath("//button[text()='Onayla']"));
		Thread.sleep(500);
		codeField.click();
		codeField.sendKeys(code);
		Thread.sleep(500);
		submitBtn.click();	
	}
	
	public WebElement resendVerificationCode() throws InterruptedException {
		WebElement resendBtn = driver.findElement(By.xpath("//button[text()='Kodu Tekrar Gönder']"));
		Thread.sleep(500);
		return resendBtn;
	}
	
	public void signInExitant() throws InterruptedException {
		signIn("wifole6425@glumark.com","a12345678.");
	}
	
	public void resetPassword(String oldPass, String newPass) throws InterruptedException {
		navigator.navigateTo("/Hesabim/KullaniciBilgileri");
		Thread.sleep(3000);
		WebElement oldPasswordField = driver.findElement(By.xpath("//input[@name='oldPassword']"));
		WebElement newPasswordField = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement newPasswordRepeatField = driver.findElement(By.xpath("//input[@name='passwordAgain']"));
		WebElement sumbit = driver.findElement(By.xpath("//div[@class='form-section'][2]/form/button[@type='submit']"));
		oldPasswordField.sendKeys(oldPass);
		Thread.sleep(1000);
		newPasswordField.sendKeys(newPass);
		Thread.sleep(1000);
		newPasswordRepeatField.sendKeys(newPass);
		Thread.sleep(1000);
		sumbit.click();
	}
	
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//div[@class='link account-user']"));
		actions.moveToElement(element).build().perform();
		Thread.sleep(3000);
		WebElement logoutBtn = driver.findElement(By.xpath("//a[@class='loggedin-account-item' and p[text()='Çıkış Yap']]"));
		logoutBtn.click();
		
	}
	
	public String getCurrentUserEmail() throws InterruptedException {
		WebElement myProfileBtn = driver.findElement(By.xpath("//div[contains(@class,'account-user')]"));
		myProfileBtn.click();
		Thread.sleep(4000);
		WebElement profileSection = driver.findElement(By.xpath("//section[@class='section-user_info']/p"));
		Thread.sleep(1500);
		return profileSection.getText();
	}
	
	public void updateUserInfo(String firstname,String lastname,String gender) throws InterruptedException {
		navigator.navigateTo("/Hesabim/KullaniciBilgileri");
		Thread.sleep(3000);
		String genderFiledParent = "//div[contains(@class,'ty-checkbox-wrapper')]/div[div[@name='"+gender+"']]";
		WebElement firstnameField = driver.findElement(By.xpath("//input[@name='firstname']"));
		WebElement lastnameField = driver.findElement(By.xpath("//input[@name='lastname']"));
		WebElement genderField	= driver.findElement(By.xpath(genderFiledParent));
		WebElement genderValue = driver.findElement(By.xpath(genderFiledParent+"/div[@name='"+gender+"']")); 
		WebElement sumbit = driver.findElement(By.xpath("//div[@class='form-section'][1]/form/button[@type='submit']"));
		firstnameField.clear();
		Thread.sleep(2000);
		firstnameField.sendKeys(firstname);
		Thread.sleep(1500);
		lastnameField.clear();
		Thread.sleep(1500);
		lastnameField.sendKeys(lastname);
		Thread.sleep(1500);
		if(genderValue.getAttribute("value") == "null") {
			genderField.click();
		}	
		Thread.sleep(1000);
		sumbit.click();
	}
	
	public String getFieldValue(String field) {
		WebElement fieldDom = driver.findElement(By.xpath("//input[@name='"+ field +"']"));
		return fieldDom.getAttribute("value");
	}
	public String VerifyGender(String gender) {
		String genderFiledParent = "//div[contains(@class,'ty-checkbox-wrapper')]/div[div[@name='"+gender+"']]";
		WebElement genderField	= driver.findElement(By.xpath(genderFiledParent+"/div[@name='"+gender+"']"));
		return genderField.getAttribute("value");
	}
	
	

}
