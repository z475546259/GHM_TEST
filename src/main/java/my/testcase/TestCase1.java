package my.testcase;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
 * 最原始的主流程
 * 一步一步写
 * 
 * */
public class TestCase1 {
	public static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	public static void main(String[] args) {
		System.out.println("开始web自动化");
		System.setProperty("webdriver.firefox.marionette",
				"C:\\Users\\zhanzhiq\\selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://www.cqdehua.cn/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(baseUrl + "/mgr/login.html");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("123465");
	    driver.findElement(By.cssSelector("div[name=\"dl\"] > img")).click();
	    driver.switchTo().frame("mainFrame").switchTo().frame("leftFrame");
	    driver.findElement(By.linkText("自定义设置")).click();
	    driver.findElement(By.linkText("报价设置")).click();
	   // driver.findElement(By.linkText("报价设置")).click();
	    driver.findElement(By.id("sd37")).click();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.switchTo().defaultContent();  
	    driver.switchTo().frame("mainFrame").switchTo().frame("rightFrame");
	    driver.findElement(By.xpath("//input[@value='新增定价']")).click();
	   // driver.findElement(By.cssSelector("span.l-btn-text")).click();
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
	
	 @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

}
