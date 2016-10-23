package my.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	public static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	public BasePage(WebDriver driver) {
		System.out.println("开始web自动化");
		System.setProperty("webdriver.firefox.marionette",
				"C:\\Users\\zhanzhiq\\selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver = driver;
	}

	public BasePage(WebDriver driver, String url) {
		
		this.driver = driver;
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public Boolean isElementPressent(String idname){
		Boolean isPresent = false;
		WebElement e =  driver.findElement(By.id(idname));
		if (e != null) {
			isPresent = e.isDisplayed();
		}
		return isPresent;
	}
	
}
