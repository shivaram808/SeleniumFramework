package reusables;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class BaseClass {
	
	public static WebDriver driver;
	public static File src;
	public static FileInputStream fis;
	public static Properties prop;
	public static Hashtable<String,String> ht;
	public static ExtentReports report;
	public static ExtentTest logger ;
	public static void initializedriver() throws Exception 
	{
	
		report =new ExtentReports("C:\\Users\\Shiva Ram\\workspaceEXT\\Sept9_TestNGPageObjModel\\Test_IP_OP\\Report.html");
					
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\reusables\\Config.property");
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("Browser");
		
		
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
	
	
	public static void moduledriver() throws Exception 
	{
		ht = new Hashtable<String,String>();
		src = new File("./Test_IP_OP/ModuleDriver.xlsx");
		fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sh= wb.getSheet("Module");
		System.out.println("module sheet is : " +sh.getSheetName());
		int Module_count = sh.getLastRowNum();
		System.out.println("Total number of modules are : "+Module_count );
		
			for (int i=1;i<=Module_count;i++) {
				
				String Module_name = sh.getRow(i).getCell(0).getStringCellValue();
				String  Module_Exests = sh.getRow(i).getCell(1).getStringCellValue();
						
					
			if(Module_Exests.equalsIgnoreCase("Yes"))
			{
				XSSFSheet sh_curr= wb.getSheet(Module_name);
				System.out.println("Current sheet is : " + sh_curr.getSheetName());
				int Testcases = sh_curr.getLastRowNum();
				for(int j=1;j<=Testcases;j++)
				{ 	 	 	 	
					String Testcase_name = sh_curr.getRow(j).getCell(0).getStringCellValue();
					String Testcase_Exests = sh_curr.getRow(j).getCell(1).getStringCellValue();
					
					ht.put(Testcase_name, Testcase_Exests);
					
				}
			}
			
		}
		System.out.println("Following Test cases are set to execution :"+ht);
	}
}
