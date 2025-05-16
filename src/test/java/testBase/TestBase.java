package testBase;




import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import configProperties.Utilities;



public class TestBase {
	
	public  WebDriver driver;
	public  Properties pro;
	
	public TestBase () throws IOException {
		
	pro = new Properties();
	FileInputStream file = new FileInputStream(Utilities.pathForTestDataProperties);
	pro.load(file);
	
	}
	
public  WebDriver openWebsite() {
	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.impliciteTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadTime));
		driver.get(pro.getProperty("URL"));
		//driver.get("https://magento.softwaretestingboard.com/");
		return driver;
}
}