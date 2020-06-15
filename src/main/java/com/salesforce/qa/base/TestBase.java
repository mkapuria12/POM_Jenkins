package com.salesforce.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.salesforce.qa.Listener.ExtentReportManager;
import com.salesforce.qa.Listener.WebEventListener;
import com.salesforce.qa.utilities.CommonUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

		public static WebDriver driver;
		public static Properties prop;
		public  static EventFiringWebDriver e_driver;
		public static WebEventListener eventListener;
		public static CommonUtilities cUtil=new CommonUtilities();
		public static ExtentReportManager eReport=new ExtentReportManager();
		
		
		public TestBase(){

			try {
				//creates property file
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/salesforce"
						+ "/qa/config/config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void initialization(){
			String browserName = prop.getProperty("browser");
			try {
				if(browserName.equals("chrome"))
				{
				WebDriverManager.chromedriver().setup();
				
				driver=new ChromeDriver();
				
				driver.get("http://google.com");
								
				driver.manage().deleteAllCookies();
						
				}
				else if(browserName.equals("FF"))
				{
					WebDriverManager.firefoxdriver().setup();
					
					driver=new FirefoxDriver();
					
					driver.get("http://google.com");
					
					driver.manage().deleteAllCookies();
					
				}
				}catch(Exception e) {
					System.out.println(e);
				}
			

			e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//	driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
			
		}

}

