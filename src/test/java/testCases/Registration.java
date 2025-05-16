package testCases;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import configProperties.Utilities;
import testBase.TestBase;


public class Registration extends TestBase {
	
	public Registration() throws IOException {
		super();
		
	}
	public WebDriver driver; 
	public SoftAssert soft = new SoftAssert();
	

	@BeforeMethod
	public void openEcommerceWebsite(){
		driver = openWebsite();
		}
	
	@Test  (priority =1) // Creating a new account
	public void registerAccount() {
		
		// 1. Creating an account
		driver.findElement(By.linkText("Create an Account")).click();
		
		// checking if the page is the correct page  
		String actualtitle = pro.getProperty("title");
	    String expectedtitle = driver.getTitle();
	    soft.assertEquals(actualtitle, expectedtitle);
	    soft.assertAll();
	    
	    // 2. Filling all the fields 
	    driver.findElement(By.id("firstname")).sendKeys(pro.getProperty("first_name"));
		driver.findElement(By.id("lastname")).sendKeys(pro.getProperty("last_name"));
		driver.findElement(By.id("email_address")).sendKeys(Utilities.dateForEmail());
		driver.findElement(By.id("password")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.id("password-confirmation")).sendKeys(pro.getProperty("re_password"));  
		driver.findElement(By.cssSelector("button.action.submit.primary")).click();
		
		// 3. Verify creating success
				soft.assertTrue(driver.getTitle().equals("My Account"));
			    soft.assertAll(); 
	    
	  
	}
	
	@Test (priority =2)  // Login using valid input
	public void loginValidInput() {
		
		// 1. Login
	    driver.findElement(By.linkText("Sign In")).click();
	    
	 // checking if the page is the correct page  
	    soft.assertTrue(driver.findElement(By.className("base")).isDisplayed());
	    
	 // 2. Enter credentials and submit
	    driver.findElement(By.id("email")).sendKeys(pro.getProperty("email"));
		driver.findElement(By.id("pass")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.cssSelector("#send2")).click();
		
		// 3. Verify login sucess
		soft.assertTrue(driver.getTitle().equals("My Account"));
	    soft.assertAll();  
	}
	

	@Test (priority =3)  // Login using unregisted email
	public void loginInvalidInput() {
		
		// 1. Login 
	    driver.findElement(By.linkText("Sign In")).click();
	    // checking if the page is the correct page  
	    soft.assertTrue(driver.findElement(By.className("base")).isDisplayed());
	    
	    // 2. Enter credentials and submit
	    driver.findElement(By.id("email")).sendKeys(Utilities.dateForEmail());
		driver.findElement(By.id("pass")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.cssSelector("#send2")).click();
		
		// 3. Verify login failure
		soft.assertTrue(driver.findElement(By.xpath("//div[@class = 'message-error error message']/child::div")).isDisplayed());
	    soft.assertAll();  
	}
	
@Test (priority =4)  // Add a product to Cart
public void addToCard()  {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	// 1. Login 
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();

    // 2. Enter credentials and submit
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
        .sendKeys(pro.getProperty("email"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")))
        .sendKeys(pro.getProperty("password"));
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#send2"))).click();

    // 3. Navigation with waits
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#ui-id-4"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Hoodies & Sweatshirts"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Circe Hooded Ice Fleece"))).click();

    // 4. Product selection with explicit waits
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#option-label-size-143-item-166"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#option-label-color-93-item-52"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#product-addtocart-button"))).click();

    // 5. Verify success message
    String expectedMessage = pro.getProperty("message");
    WebElement actualMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message-success success message']/div")));
    soft.assertEquals(actualMessageElement.getText(), expectedMessage, "Success message mismatch");

    // 6. Final action
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.action.showcart"))).click();
    soft.assertAll();  
}

@AfterMethod
public void closebrowser() {
	driver.quit();
	
}
}

