package reusables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CRbrowser_BaseClass {
	
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties prop;

	
	//@Test
	@Parameters("Browser")
	public void initializedriver_CB(String browser) throws IOException 
	{
				
		System.out.println(" Launching Browser : "+browser);
		
		 if(browser.equalsIgnoreCase("Chrome"))
			{
			 System.setProperty("webdriver.Chrome.driver",".//chromedriver.exe");
			driver = new ChromeDriver();
			}
		
		 else if(browser.equalsIgnoreCase("Firefox"))
			{
			System.setProperty("webdriver.gecko.driver",".//geckodriver.exe");
			driver = new FirefoxDriver();
			}
			
	
		
		else if(browser.equalsIgnoreCase("IE"))
			{
			System.setProperty("webdriver.ie.driver",".//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			}
			
		else 
		{
			System.out.println("Please give a proper browser name from one of these Chrome/Firefox/IE ");
		}
		
		driver.get("http://automationtechno.weebly.com");
		driver.manage().window().maximize();
		System.out.println("Website Launched.........");
		
	}

}
