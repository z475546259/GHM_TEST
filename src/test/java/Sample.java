

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import my.utils.BasePage;

public class Sample {
	private WebDriver driver;
	//@BeforeMethod
	public void init() {
		System.out.println("befor web autotest");
		System.setProperty("webdriver.firefox.marionette",
				"C:\\Users\\zhanzhiq\\selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
	}
	@Test
	public void testDHGG(){
		System.out.println("befor web autotest");
		System.setProperty("webdriver.firefox.marionette",
				"C:\\Users\\zhanzhiq\\selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		BasePage base = new BasePage(driver,"http://www.cqdehua.cn/mgr/login.html");
		driver =base.getDriver();
		driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("123465");
	    driver.findElement(By.cssSelector("div[name=\"dl\"] > img")).click();
	    driver.switchTo().frame("mainFrame").switchTo().frame("leftFrame");
	   // driver.findElement(By.cssSelector("span.l-btn-text")).click();
	   // driver.quit();
	}
}
