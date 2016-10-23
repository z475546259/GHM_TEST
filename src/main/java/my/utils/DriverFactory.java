package my.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverFactory {
	private static String chromedriver;
	private static String fireBug;
	private static String firefoxdriver;
	private static String IEDriverServer;
	private static String EDGEDriver;
	private static String OSType = System.getProperty("os.name");
	private static Log log = new Log(DriverFactory.class);
	private static Properties p = null;
	private static String config = System.getProperty("user.dir")+ File.separator +"config.properties";;
	public static void main(String[] args) {
		System.out.println(OSType);
	}
	public DriverFactory(){
		readProperty();
	}
	public static void readProperty(){
		System.out.println("Get the config file: " + config);
		log.info("Get the config file: " + config);
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		log.info("Get the config file: " + config);
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(new File(config));
		
		try {
			p.load(inStream);
		} catch (Exception e) {
			log.error("can't find the config file ");
			log.error(e.getMessage());
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static WebDriver getChromeDriver() {
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (p != null) {
			if (!OSType.contains("Mac"))
			{
				chromedriver = p.getProperty("chromedriver");
			}
			else
			{
				chromedriver = p.getProperty("MAC_chromedriver");
			}
			
		}
		log.info("chrome driver path is "+chromedriver);
		System.setProperty("webdriver.chrome.driver", chromedriver);
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches",
				Arrays.asList("--start-maximized"));
		options.addArguments("--test-type", "--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		log.info("Create ChromeDrive ");
		return driver;
	}
	
	public static WebDriver getFirefoxDriver() {
		System.out.println("path:"+config);
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (p != null) {
			fireBug = p.getProperty("fireBug");
			if (!OSType.contains("Mac"))
			{
				firefoxdriver = p.getProperty("firefoxdriver");
			}
			else
			{
				firefoxdriver = p.getProperty("MAC_firefoxdriver");
			}
		}
		System.out.println("read the config webdriver:"+firefoxdriver);
		//System.setProperty("webdriver.gecko.driver", firefoxdriver);
		System.setProperty("webdriver.firefox.marionette",
				"C:\\Users\\zhanzhiq\\selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		File file = new File(fireBug);
		FirefoxProfile profile = new FirefoxProfile();
		try {
			profile.addExtension(file);
			profile.setPreference("extensions.firebug.currentVersion", "2.0.4");
			profile.setPreference("extensions.firebug.allPagesActivation",
					"off");
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		profile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream, application/vnd.ms-excel, text/csv, application/zip,application/exe");
		WebDriver driver = new FirefoxDriver(profile);
		log.info("Create FirefoxDriver ");
		return driver;
	}
	
	public static WebDriver getIEDriver() {
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (p != null) {
			IEDriverServer = p.getProperty("IEDriverServer");
		}
		System.setProperty("webdriver.ie.driver", IEDriverServer);
		String PROXY = "http://proxy:8083";
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);

		DesiredCapabilities ds = DesiredCapabilities.internetExplorer();
		ds.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		ds.setCapability("ignoreProtectedModeSettings", true);
		ds.setCapability(CapabilityType.PROXY, proxy);
		WebDriver driver = new InternetExplorerDriver(ds);
		return driver;
	}
	
	public static WebDriver getEDGEDriver() {
		try {
			p = new ConfigUtils().getProperties(config);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (p != null) {
			EDGEDriver = p.getProperty("EDGEDriver");
		}
		System.setProperty("webdriver.edge.driver", EDGEDriver);
		String PROXY = "https://raw.githubusercontent.com/seveniruby/gfwlist2pac/master/test/proxy.pac";
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("normal");
		capabilities.setCapability(EdgeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		WebDriver driver = new EdgeDriver(capabilities);
		return driver;
	}
}
