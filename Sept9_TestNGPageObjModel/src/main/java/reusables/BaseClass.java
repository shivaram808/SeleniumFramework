package reusables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import xls.ShineXlsReader;

public class BaseClass {
	
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties prop;
	public static SoftAssert st;
	public static ShineXlsReader xls;
	public static Hashtable<String,String> ht;
	public static ExtentReports report;
	public static ExtentTest logger ;
	public static void initializedriver() throws IOException 
	{
	
		report =new ExtentReports("C:\\Users\\Shiva Ram\\workspaceEXT\\Sept9_TestNGPageObjModel\\Test_IP_OP\\Report.html");
					
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\reusables\\Config.property");
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("Browser");
		st = new SoftAssert();
		
		logger = report.startTest("Browser Selection........");
		
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
			 
		logger.log(LogStatus.INFO, "Browser launched : "+browser);	 
		
		driver.get("http://automationtechno.weebly.com");
		driver.manage().window().maximize();
	
		logger.log(LogStatus.INFO, "website launched");	
		report.endTest(logger);
	}
	
	
	public static void moduledriver() 
	{
		
		ht = new Hashtable<String,String>();
		xls = new ShineXlsReader(System.getProperty("user.dir")+"\\Test_IP_OP\\ModuleDriver.xlsx");
		int Module_count = xls.getRowCount("Module");
		for (int i =2;i<=Module_count;i++) {
			String Module_name = xls.getCellData("Module", 0, i);
			String Module_Exests = xls.getCellData("Module", 1, i);
			
			if(Module_Exests.equalsIgnoreCase("Yes"))
			{
				int Testcases = xls.getRowCount(Module_name);
				for(int j=2;j<=Testcases;j++)
				{ 	 	 	 	
					String Testcase_name = xls.getCellData(Module_name, 0, j);
					String Testcase_Exests = xls.getCellData(Module_name, 1, j);
					
					ht.put(Testcase_name, Testcase_Exests);
					
				}
			}
			
		}
		System.out.println("Following Test cases are set to execution :"+ht);
	}
}
