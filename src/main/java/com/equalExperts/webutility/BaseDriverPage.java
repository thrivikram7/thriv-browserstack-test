package com.equalExperts.webutility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriverPage {
	
	public static WebDriver driver;

	static BaseDriverPage baseClass;

	/********
	 * Singleton
	 **********/
	private BaseDriverPage() {PropertiesUtil.loadPropertiesFiles();}
	public static BaseDriverPage getInstance() {
		if (baseClass == null) {
			baseClass = new BaseDriverPage();
		}
		return baseClass;
	}
	
	public WebDriver sharedDriver() {
		if (driver == null) {
			try {
				String webDriver=PropertiesUtil.getProperty("webDriver");
				if(webDriver == null || webDriver.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", "chrome/chromedriver");
					driver=new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				}
				else if(webDriver.equals("firefox"))
				{
					System.setProperty("webdriver.gecko.driver", "firefox/fireFoxDriver18");
					driver = new FirefoxDriver();
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return driver;
	}
}
