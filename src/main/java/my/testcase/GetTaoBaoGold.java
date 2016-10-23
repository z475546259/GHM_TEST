package my.testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import my.utils.BasePage;
import my.utils.DriverFactory;
/*
 * 该案例封装了 webdriver 和 logger
 * 
 * */
public class GetTaoBaoGold {
	private WebDriver driver;
	@BeforeMethod
	public void init(){
		System.out.println("111");
		driver = DriverFactory.getFirefoxDriver();
	}
	
	@Test
	public void getgold(){
		//driver = DriverFactory.getFirefoxDriver();
		BasePage page =  new BasePage(driver, "http://www.taobao.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("亲，请登录")).click();
		System.out.println("找到元素J_Static2Quick了么"+page.isElementPressent("J_Static2Quick"));
		System.out.println("找到元素J_Quick2Static了么"+page.isElementPressent("J_Quick2Static"));
		if(page.isElementPressent("J_Quick2Static")){
			driver.findElement(By.id("J_Quick2Static")).click();
		}
		
		driver.findElement(By.id("TPL_username_1")).clear();
		driver.findElement(By.id("TPL_username_1")).sendKeys("一坨铁");
		driver.findElement(By.id("TPL_password_1")).clear();
	    driver.findElement(By.id("TPL_password_1")).sendKeys("zhang244768284");
	    driver.findElement(By.id("J_SubmitStatic")).click();
//	    WebDriverWait wait = new WebDriverWait(driver, 60);
//	    wait.until(new )
	    driver.findElement(By.cssSelector("img.J_MemberAvatar.member-avatar")).click();
	    driver.findElement(By.linkText("淘金币")).click();
	   
		
	   // driver.quit();
	}
	
}
